package com.tq.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.tq.ad.mysql.dto.BinlogRowData;
import com.tq.ad.mysql.dto.JsonTable;
import com.tq.ad.mysql.dto.ParseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AggregationListener implements BinaryLogClient.EventListener {

    private String dbName;
    private String tableName;

    private Map<String,ILisener> lisenerMap=new HashMap<>();

    @Autowired
    ParseTemplate parseTemplate;

    private String genKey(String dbName,String tableName){
        return dbName+":"+tableName;
    }

    public void register(String dbName,String tableName,ILisener lisener){
        log.info("register:{}--{}",dbName,tableName);
        this.lisenerMap.put(genKey(dbName,tableName),lisener);
    }

    @Override
    public void onEvent(Event event) {

        EventType type=event.getHeader().getEventType();

        log.info("event type:{}",type);

        if(type==EventType.TABLE_MAP){
            TableMapEventData data=event.getData();
            this.tableName=data.getTable();
            this.dbName=data.getDatabase();

            return;
        }

        if(type!=EventType.EXT_UPDATE_ROWS && type!=EventType.EXT_WRITE_ROWS&&
        type!=EventType.EXT_DELETE_ROWS){
            return;
        }

        String key=genKey(this.dbName,this.tableName);
        ILisener lisener=this.lisenerMap.get(key);
        if(lisener==null){
            log.info("skip{}",key);
        }

        log.info("trigger listener:{}",key);

        try {
            BinlogRowData data=buildRowData(event.getData());
            if(data==null){
                return;
            }

            data.setEventType(type);
            lisener.onEvent(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.dbName="";
            this.tableName="";
        }
    }
    private List<Serializable[]> getAfterValues(EventData data){

        if(data instanceof WriteRowsEventData){
            return ((WriteRowsEventData) data).getRows();
        }
        if(data instanceof UpdateRowsEventData){
            return ((UpdateRowsEventData) data).getRows().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        }
        if(data instanceof DeleteRowsEventData){
            return ((DeleteRowsEventData) data).getRows();
        }
        return Collections.emptyList();
    }


    private BinlogRowData buildRowData(EventData data){

        List<Map<String,String>> afterMapList=new ArrayList<>();
        for(Serializable[] after:getAfterValues(data)){
            Map<String,String> afterMap=new HashMap<>();
            for(int i=0;i<after.length;i++){
                String colName=parseTemplate.getDatabaseColumns().get(tableName).get(i);///>>>?????????????????????????????????????

                String colValue=after[i].toString();
                afterMap.put(colName,colValue);
            }
            afterMapList.add(afterMap);
        }
        BinlogRowData binlogRowData=new BinlogRowData();
        binlogRowData.setAfter(afterMapList);
        List<JsonTable> tables= parseTemplate.getTemplate().getTableList().stream().filter(jsonTable -> jsonTable.getTableName().equals(tableName)).collect(Collectors.toList());
        binlogRowData.setJsonTable(tables.get(0));

        return binlogRowData;
    }
}

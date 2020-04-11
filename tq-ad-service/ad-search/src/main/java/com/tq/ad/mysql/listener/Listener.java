package com.tq.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.tq.ad.mysql.constant.OpType;
import com.tq.ad.mysql.dto.BinlogRowData;
import com.tq.ad.mysql.dto.JsonTable;
import com.tq.ad.mysql.dto.MysqlRowData;
import com.tq.ad.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Component
public class Listener implements ILisener {

    @Autowired
    AggregationListener aggregationListener;

    @Resource(name="IndexSender")
    ISender sender;

    @Override
    @PostConstruct
    public void register() {
        //此处就注册一个
        aggregationListener.register("ad","all",this);
    }

    @Override
    public void onEvent(BinlogRowData binlogRowData) {

        JsonTable jsonTable= binlogRowData.getJsonTable();
        EventType eventType=binlogRowData.getEventType();

        MysqlRowData rowData=new MysqlRowData();
        rowData.setTableName(jsonTable.getTableName());
        rowData.setLevel(String.valueOf(jsonTable.getLevel()));
        rowData.setType(OpType.toOptype(eventType));

        for(Map<String,String> afterMap:binlogRowData.getAfter()){

            Map<String,String> rowDataMap=new HashMap<>();
            for(Map.Entry<String,String> entry:afterMap.entrySet()){
                rowDataMap.put(entry.getKey(),entry.getValue());
            }
            rowData.getFieldVlaueMap().add(rowDataMap);
        }

        sender.sender(rowData);


    }
}

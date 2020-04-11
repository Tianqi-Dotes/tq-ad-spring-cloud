package com.tq.ad.sender.index;

import com.tq.ad.dump.table.AdPlanTable;
import com.tq.ad.handler.AdLevelDataHandler;
import com.tq.ad.mysql.dto.MysqlRowData;
import com.tq.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("IndexSender")
public class IndexSender implements ISender {


    @Override
    public void sender(MysqlRowData rowData) {

        String level=rowData.getLevel();
        if(level.equals("2")){
            level2RowData(rowData);
        }else if (level.equals("3")){

        }else if(level.equals(4)){

        }else {
            log.error("row data not 4 level");
        }
    }

    private void level2RowData(MysqlRowData rowData){

        if(rowData.getTableName().equals("ad_plan")){

            List<AdPlanTable> planTableList=new ArrayList<>();
            AdPlanTable planTable=new AdPlanTable();
            for(Map<String,String> data:rowData.getFieldVlaueMap()){
                planTable.setId(Long.valueOf(data.get("id")));
                planTable.setPlanStatus(Integer.valueOf(data.get("status")));
                planTable.setUserId(Long.valueOf(data.get("userId")));

                planTableList.add(planTable);
            }
            planTableList.forEach(p-> AdLevelDataHandler.handleLvl2(p,rowData.getType()));
        }
    }
}

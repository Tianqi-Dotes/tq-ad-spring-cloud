/*
package com.tq.ad.index;

import com.alibaba.fastjson.JSON;
import com.tq.ad.dump.SqlConstant;
import com.tq.ad.dump.table.AdCreativeTable;
import com.tq.ad.dump.table.AdPlanTable;
import com.tq.ad.dump.table.AdUnitDistrictTable;
import com.tq.ad.dump.table.AdUnitTable;
import com.tq.ad.handler.AdLevelDataHandler;
import com.tq.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    @PostConstruct
    public void init(){
        List<String> adPlanStrings =loadDumpData((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_PLAN)));
        adPlanStrings.forEach(s-> AdLevelDataHandler.handleLvl2(
                JSON.parseObject(s, AdPlanTable.class), OpType.ADD
        ));
        List<String> adCreativeStrings =loadDumpData((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_CREATIVE)));
        adCreativeStrings.forEach(s-> AdLevelDataHandler.handleLvl2(
                JSON.parseObject(s, AdCreativeTable.class), OpType.ADD
        ));
        List<String> adUnitStrings =loadDumpData((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_UNIT)));
        adUnitStrings.forEach(s-> AdLevelDataHandler.handleLvl3(
                JSON.parseObject(s, AdUnitTable.class), OpType.ADD
        ));

        List<String> adUnitDistrictStrings =loadDumpData((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_UNIT_DISTRICT)));
        adUnitDistrictStrings.forEach(s-> AdLevelDataHandler.handleLvl4(
                JSON.parseObject(s, AdUnitDistrictTable.class), OpType.ADD
        ));


    }

    private List<String> loadDumpData(String fileName){
        try {
            BufferedReader reader= Files.newBufferedReader(Paths.get(fileName));
            return reader.lines().collect(Collectors.toList());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
*/

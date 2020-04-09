package com.tq.ad.mysql.dto;


import com.alibaba.fastjson.JSON;
import com.tq.ad.schema_entity.MysqlSchema;
import com.tq.ad.schema_entity.MysqlSchemaMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Data
@Component
public class ParseTemplate {

    @Autowired
    MysqlSchemaMapper mysqlSchemaMapper;

    private String database;
    private List<JsonTable> tableList=new ArrayList<>();

    //private Map<String,TableTemplate> tableTemplateMap=new HashMap<>();


    private Map<String,Map<Integer,String>> databaseColumns=new HashMap<>();

    private ParseTemplate template;



    @PostConstruct
    void init() throws IOException {
        BufferedReader reader= Files.newBufferedReader(Paths.get("C:\\Users\\Administrator\\Desktop\\tq_ad\\tq-ad-service\\ad-search\\src\\main\\resources\\template.json"));
        StringBuilder sb=new StringBuilder();
        reader.lines().forEach(s -> sb.append(s));
        template= JSON.parseObject(sb.toString(),ParseTemplate.class);

        for(JsonTable table:template.getTableList()){

            List<MysqlSchema> list=mysqlSchemaMapper.queryMysqlSchemaColumn(table.getTableName());

            Map<Integer,String> tableColumnMap= createIfNotExist(databaseColumns,table.getTableName(),HashMap::new);
            for(MysqlSchema schema:list){
                tableColumnMap.put(schema.getOrdinalPosition()-1,schema.getColumnName());
            }

        }

    }
    private Map<Integer,String> createIfNotExist(Map databaseColumns,String databaseName,Supplier<Map<Integer,String>> map){
        if(databaseColumns.containsKey(databaseName)){
            return (Map<Integer, String>) databaseColumns.get(databaseName);
        }else{
            return map.get();
        }
    }

}

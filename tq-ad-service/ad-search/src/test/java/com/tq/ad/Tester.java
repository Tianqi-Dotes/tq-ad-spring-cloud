package com.tq.ad;

import com.tq.ad.schema_entity.MysqlSchema;
import com.tq.ad.schema_entity.MysqlSchemaMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Tester {

    @Autowired
    MysqlSchemaMapper mysqlSchemaMapper;


    @Test
    public void contextLoads() throws IOException, URISyntaxException, ParseException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        /*List<MysqlSchema> schema = mysqlSchemaMapper.queryMysqlSchemaColumn("ad_plan");


        System.out.println(schema.toString());*/
    }
}
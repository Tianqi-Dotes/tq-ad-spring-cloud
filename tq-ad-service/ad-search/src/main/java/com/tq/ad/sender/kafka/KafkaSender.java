package com.tq.ad.sender.kafka;

import com.alibaba.fastjson.JSON;
import com.tq.ad.mysql.dto.MysqlRowData;
import com.tq.ad.sender.ISender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("kafkaSender")

public class KafkaSender implements ISender {

    @Value("${adconf.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sender(MysqlRowData rowData) {
        kafkaTemplate.send(topic, JSON.toJSONString(rowData));
    }

    @KafkaListener(topics = {"${adconf.kafka.topic}"},groupId = "ad-search")
    public void processMysqlRowData(ConsumerRecord<?,?> record){

        Optional<?> kafkaMsg=Optional.ofNullable(record.value());
        if(kafkaMsg.isPresent()){
            Object msg=kafkaMsg.get();
            MysqlRowData mysqlRowData=JSON.parseObject(msg.toString(),MysqlRowData.class);
            System.out.println("kafka process mysql row data"+mysqlRowData.toString());
        }

    }
}

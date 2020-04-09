package com.tq.ad.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.tq.ad.mysql.listener.AggregationListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class BinlogClient {

    @Autowired
    BinlogConf binlogConf;
    @Autowired
    AggregationListener aggregationListener;

    private BinaryLogClient client;

    public void connect(){
        new Thread(()->{

            client=new BinaryLogClient(binlogConf.getHost(),binlogConf.getPort(),binlogConf.getUsername()
            ,binlogConf.getPassword());

            client.setBinlogFilename(binlogConf.getBinlogName());
            client.setBinlogPosition(binlogConf.getPosition());

            client.registerEventListener(aggregationListener);

            try {
                client.connect();
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void close(){
        try {
            client.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

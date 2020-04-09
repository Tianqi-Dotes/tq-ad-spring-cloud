package com.tq.ad.runner;

import com.tq.ad.mysql.BinlogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BinlogClientRunner implements CommandLineRunner {

    @Autowired
    BinlogClient client;

    @Override
    public void run(String... args) throws Exception {
        log.error("binlog client run...!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        client.connect();
    }
}

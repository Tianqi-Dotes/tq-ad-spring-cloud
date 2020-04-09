package com.tq.ad.mysql;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "adconf.mysql")
@Data
public class BinlogConf {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String binlogName;
    private Long position;
}

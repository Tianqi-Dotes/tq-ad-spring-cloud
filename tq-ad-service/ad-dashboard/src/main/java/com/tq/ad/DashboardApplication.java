package com.tq.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard//启动hytrix dashboard
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class,args);
    }
}

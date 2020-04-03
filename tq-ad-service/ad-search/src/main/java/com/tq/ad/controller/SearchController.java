package com.tq.ad.controller;

import com.alibaba.fastjson.JSON;
import com.tq.ad.annotation.IgnoreResponseAdvice;
import com.tq.ad.client.SponsorClient;
import com.tq.ad.client.vo.AdPlan;
import com.tq.ad.client.vo.AdPlanGetRequest;
import com.tq.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class SearchController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SponsorClient sponsorClient;

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRibbon(@RequestBody AdPlanGetRequest request){

        log.info("ad-search : get adPlansByRibbon -> {}", JSON.toJSONString(request));

        String url="http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan";
        return restTemplate.postForEntity(url,request,CommonResponse.class).getBody();
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByFeign")
    public CommonResponse<List<AdPlan>> getAdPlansByFeign(@RequestBody AdPlanGetRequest request){

        log.info("ad-search : get adPlansByFeign -> {}", JSON.toJSONString(request));

        return sponsorClient.getAdPlansByFeign(request);
    }
}

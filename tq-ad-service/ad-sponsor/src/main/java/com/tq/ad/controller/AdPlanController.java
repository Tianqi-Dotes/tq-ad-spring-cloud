package com.tq.ad.controller;


import com.alibaba.fastjson.JSON;
import com.tq.ad.entity.AdPlan;
import com.tq.ad.exception.AdException;
import com.tq.ad.service.IAdPlanService;
import com.tq.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 推广计划表 前端控制器
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@RestController
@Slf4j
public class AdPlanController {

    @Autowired
    IAdPlanService adPlanService;


    @PostMapping(value = "/create/plan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {

        log.info("ad-sponsor : createPlan -> {}", JSON.toJSONString(request));
        return adPlanService.createNewPlan(request);
    }

    @PostMapping(value = "/get/plan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {

        log.info("ad-sponsor : getPlan -> {}", JSON.toJSONString(request));
        return adPlanService.getPlansByIds(request);
    }

    @PutMapping(value = "/update/plan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {

        log.info("ad-sponsor : updatePlan -> {}", JSON.toJSONString(request));
        return adPlanService.updatePlan(request);
    }

    @DeleteMapping(value = "/delete/plan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {

        log.info("ad-sponsor : deletePlan -> {}", JSON.toJSONString(request));
        adPlanService.deletePlan(request);
    }
}


package com.tq.ad.controller;


import com.alibaba.fastjson.JSON;
import com.tq.ad.exception.AdException;
import com.tq.ad.service.IAdUnitService;
import com.tq.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 推广单元表 前端控制器
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@RestController
@Slf4j
public class AdUnitController {


    @Autowired
    IAdUnitService adUnitService;

    @PostMapping(value = "/create/unit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {

        log.info("ad-sponsor : createAdUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createAdUnit(request);
    }

    @PostMapping(value = "/create/unitKeyword")
    public AdUnitKeywordResponse createUnit(@RequestBody AdUnitKeywordRequest request) throws AdException {

        log.info("ad-sponsor : createAdUnitKeyword -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping(value = "/create/unitIt")
    public AdUnitItResponse createUnit(@RequestBody AdUnitItRequest request) throws AdException {

        log.info("ad-sponsor : createAdUnitIt -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    @PostMapping(value = "/create/unitDistrict")
    public AdUnitDistrictResponse createUnit(@RequestBody AdUnitDistrictRequest request) throws AdException {

        log.info("ad-sponsor : createAdUnitDistrict -> {}", JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    @PostMapping(value = "/create/creativeUnit")
    public CreativeUnitResponse createUnit(@RequestBody CreativeUnitRequest request) throws AdException {

        log.info("ad-sponsor : createCreativeUnit -> {}", JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }
}

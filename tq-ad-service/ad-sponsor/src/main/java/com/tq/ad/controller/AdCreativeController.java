package com.tq.ad.controller;


import com.alibaba.fastjson.JSON;
import com.tq.ad.exception.AdException;
import com.tq.ad.service.IAdCreativeService;
import com.tq.ad.service.ICreativeUnitService;
import com.tq.ad.vo.AdUnitRequest;
import com.tq.ad.vo.AdUnitResponse;
import com.tq.ad.vo.CreativeRequest;
import com.tq.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 创意表 前端控制器
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@RestController
@Slf4j
public class AdCreativeController {

    @Autowired
    IAdCreativeService creativeService;

    @PostMapping(value = "/create/creative")
    public CreativeResponse createUnit(@RequestBody CreativeRequest request) throws AdException {

        log.info("ad-sponsor : createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }

}


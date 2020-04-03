package com.tq.ad.controller;


import com.alibaba.fastjson.JSON;
import com.tq.ad.exception.AdException;
import com.tq.ad.service.IAdUserService;
import com.tq.ad.vo.CreateUserRequest;
import com.tq.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@RestController
@Slf4j
public class AdUserController {

    @Autowired
    IAdUserService adUserService;

    @PostMapping(value = "/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {

        log.info("ad-sponsor : createUser -> {}", JSON.toJSONString(request));
        return adUserService.createNewUser(request);
    }
}


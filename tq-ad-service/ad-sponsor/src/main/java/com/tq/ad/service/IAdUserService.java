package com.tq.ad.service;

import com.tq.ad.entity.AdUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tq.ad.exception.AdException;
import com.tq.ad.vo.CreateUserRequest;
import com.tq.ad.vo.CreateUserResponse;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
public interface IAdUserService extends IService<AdUser> {

    CreateUserResponse createNewUser(CreateUserRequest request) throws AdException;
}

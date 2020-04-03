package com.tq.ad.service;

import com.tq.ad.entity.AdCreative;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tq.ad.vo.CreateUserRequest;
import com.tq.ad.vo.CreativeRequest;
import com.tq.ad.vo.CreativeResponse;

/**
 * <p>
 * 创意表 服务类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
public interface IAdCreativeService extends IService<AdCreative> {


    CreativeResponse createCreative(CreativeRequest request);
}

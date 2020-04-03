package com.tq.ad.service;

import com.tq.ad.entity.AdUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tq.ad.exception.AdException;
import com.tq.ad.vo.*;

/**
 * <p>
 * 推广单元表 服务类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
public interface IAdUnitService extends IService<AdUnit> {

    AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}

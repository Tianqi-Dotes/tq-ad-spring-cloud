package com.tq.ad.service;

import com.tq.ad.entity.AdPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tq.ad.exception.AdException;
import com.tq.ad.vo.AdPlanGetRequest;
import com.tq.ad.vo.AdPlanRequest;
import com.tq.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * <p>
 * 推广计划表 服务类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
public interface IAdPlanService extends IService<AdPlan> {

    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createNewPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getPlansByIds(AdPlanGetRequest request) throws AdException;

    AdPlanResponse updatePlan(AdPlanRequest request) throws AdException;

    void deletePlan(AdPlanRequest request) throws AdException;
}

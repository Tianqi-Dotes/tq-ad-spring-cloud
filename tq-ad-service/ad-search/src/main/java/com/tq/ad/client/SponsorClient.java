package com.tq.ad.client;


import com.tq.ad.client.vo.AdPlan;
import com.tq.ad.client.vo.AdPlanGetRequest;
import com.tq.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClienHystrix.class)
/**
 * fallback服务降级
 */
public interface SponsorClient {

    @RequestMapping(value="/ad-sponsor/get/plan",method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlansByFeign(@RequestBody AdPlanGetRequest request);
}

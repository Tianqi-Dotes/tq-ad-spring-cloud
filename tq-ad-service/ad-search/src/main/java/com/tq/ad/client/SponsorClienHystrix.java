package com.tq.ad.client;

import com.tq.ad.client.vo.AdPlan;
import com.tq.ad.client.vo.AdPlanGetRequest;
import com.tq.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClienHystrix implements SponsorClient{

    @Override//服务降级代码
    public CommonResponse<List<AdPlan>> getAdPlansByFeign(AdPlanGetRequest request) {
        return new CommonResponse<>(-1,"eureka-client-ad-sponsor down");
    }
}

package com.tq.ad.index.adunit;

import com.tq.ad.client.vo.AdPlan;
import com.tq.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {



    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject planObject;

    void update(AdUnitObject object){
        if(object.getUnitId()!=null){
            this.planId=object.getUnitId();
        }
        if(object.getUnitStatus()!=null){
            this.unitStatus=object.getUnitStatus();
        }
        if(object.getPositionType()!=null){
            this.positionType=object.getPositionType();
        }
        if(object.getPlanId()!=null){
            this.planId=object.getPlanId();
        }
        if(object.getPlanObject()!=null){
            this.planObject=object.getPlanObject();
        }
    }
}

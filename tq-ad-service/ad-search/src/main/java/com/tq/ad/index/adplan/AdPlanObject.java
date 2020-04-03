package com.tq.ad.index.adplan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObject {


    private Long planId;
    private Long userId;
    private Integer planStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void update(AdPlanObject object){

        if(object.getPlanId()!=null){
            this.planId=object.getPlanId();
        }
        if(object.getUserId()!=null){
            this.userId=object.getUserId();
        }
        if(object.getPlanStatus()!=null){
            this.planStatus=object.getPlanStatus();
        }
        if(object.getStartDate()!=null){
            this.startDate=object.getStartDate();
        }
        if(object.getEndDate()!=null){
            this.endDate=object.getEndDate();
        }
    }
}

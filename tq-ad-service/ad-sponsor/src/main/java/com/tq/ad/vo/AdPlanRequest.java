package com.tq.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {

    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;

    public boolean saveValidation(){
        return userId!=null&&!StringUtils.isEmpty(planName)
                &&!StringUtils.isEmpty(startDate)&&!StringUtils.isEmpty(endDate);
    }

    public boolean updateValidation(){
        return id!=null&&userId!=null;
    }

    public boolean deleteValidation(){
        return id!=null&&userId!=null;
    }
}

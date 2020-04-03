package com.tq.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {

    @NotNull
    private Long userId;
    @NotNull
    private List<Long> ids;

    public boolean validation(){
        return ids.size()!=0;
    }
}

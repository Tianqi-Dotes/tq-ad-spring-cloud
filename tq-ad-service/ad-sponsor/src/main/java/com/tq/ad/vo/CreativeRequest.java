package com.tq.ad.vo;

import com.tq.ad.constant.CommonStatus;
import com.tq.ad.entity.AdCreative;
import com.tq.ad.entity.unit_condition.CreativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Long userId;
    private String url;

    public AdCreative convertToEntity(){
        AdCreative creative=new AdCreative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setCreateTime(LocalDateTime.now());
        creative.setUpdateTime(creative.getCreateTime());

        return creative;
    }
}

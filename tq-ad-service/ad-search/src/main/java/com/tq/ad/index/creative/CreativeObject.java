package com.tq.ad.index.creative;


import com.tq.ad.index.adunit.AdUnitObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;

    void update(CreativeObject object){
        if(object.getAdId()!=null){
            this.adId=object.getAdId();
        }
        if(object.getName()!=null){
            this.name=object.getName();
        }
        if(object.getType()!=null){
            this.type=object.getType();
        }
        if(object.getMaterialType()!=null){
            this.materialType=object.getMaterialType();
        }
        if(object.getHeight()!=null){
            this.height=object.getHeight();
        }
        if(object.getWidth()!=null){
            this.width=object.getWidth();
        }
        if(object.getAuditStatus()!=null){
            this.auditStatus=object.getAuditStatus();
        }if(object.getAdUrl()!=null){
            this.adUrl=object.getAdUrl();
        }

    }
}

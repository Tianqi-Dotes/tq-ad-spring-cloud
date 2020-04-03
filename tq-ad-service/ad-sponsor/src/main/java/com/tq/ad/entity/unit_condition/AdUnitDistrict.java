package com.tq.ad.entity.unit_condition;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推广单元地域 Feature
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_unit_district")
public class AdUnitDistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 推广单元 id
     */
    @TableField("unit_id")
    private Long unitId;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;

    public AdUnitDistrict(Long unitId, String province,String city){
        this.unitId=unitId;
        this.province=province;
        this.city=city;
    }
}

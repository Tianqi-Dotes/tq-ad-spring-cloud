package com.tq.ad.entity.unit_condition;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推广单元兴趣 Feature
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_unit_it")
public class AdUnitIt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 推广单元 id
     */
    @TableField("unit_id")
    private Long unitId;
    /**
     * 兴趣标签
     */
    @TableField("it_tag")
    private String itTag;


    public AdUnitIt(Long unitId,String itTag){
        this.unitId=unitId;
        this.itTag=itTag;
    }
}

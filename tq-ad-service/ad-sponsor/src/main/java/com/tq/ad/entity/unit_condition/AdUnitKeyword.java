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
 * 推广单元关键词 Feature
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_unit_keyword")
public class AdUnitKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 推广单元 id
     */
    @TableField("unit_id")
    private Long unitId;
    /**
     * 关键词
     */
    private String keyword;

    public AdUnitKeyword(Long unitId,String keyword){
        this.unitId=unitId;
        this.keyword=keyword;
    }
}

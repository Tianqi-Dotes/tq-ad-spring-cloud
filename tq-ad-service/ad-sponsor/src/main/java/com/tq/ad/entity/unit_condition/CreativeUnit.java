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
 * 创意和推广单元关联表
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("creative_unit")
public class CreativeUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创意 id
     */
    @TableField("creative_id")
    private Long creativeId;
    /**
     * 推广单元 id
     */
    @TableField("unit_id")
    private Long unitId;

    public CreativeUnit(Long creativeId,Long unitId){
        this.creativeId=creativeId;
        this.unitId=unitId;
    }
}

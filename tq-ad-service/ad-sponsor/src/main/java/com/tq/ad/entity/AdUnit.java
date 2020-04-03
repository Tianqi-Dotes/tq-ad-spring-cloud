package com.tq.ad.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.tq.ad.constant.CommonStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推广单元表
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_unit")
public class AdUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 关联推广计划 id
     */
    @TableField("plan_id")
    private Long planId;
    /**
     * 推广单元名称
     */
    @TableField("unit_name")
    private String unitName;
    /**
     * 推广单元状态
     */
    @TableField("unit_status")
    private Integer unitStatus;
    /**
     * 广告位类型(开屏, 贴片, 中贴, 暂停帖, 后贴)
     */
    @TableField("position_type")
    private Integer positionType;
    /**
     * 预算
     */
    private Long budget;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    public AdUnit(Long planId,String unitName,Integer positionType,Long budget){
        this.planId=planId;
        this.unitName=unitName;
        this.unitStatus= CommonStatus.VALID.getStatus();
        this.positionType=positionType;
        this.budget=budget;
        this.createTime=LocalDateTime.now();
        this.updateTime=this.createTime;
    }
}

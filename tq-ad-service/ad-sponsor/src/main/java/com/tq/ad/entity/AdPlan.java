package com.tq.ad.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tq.ad.constant.CommonStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推广计划表
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_plan")
@NoArgsConstructor
public class AdPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标记当前记录所属用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 推广计划名称
     */
    @TableField("plan_name")
    private String planName;
    /**
     * 推广计划状态
     */
    @TableField("plan_status")
    private Integer planStatus;
    /**
     * 推广计划开始时间；
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("start_date")
    private LocalDateTime startDate;
    /**
     * 推广计划结束时间；
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("end_date")
    private LocalDateTime endDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;

    public AdPlan(Long userId, String planName, LocalDateTime startDate,LocalDateTime endDate){
        this.userId=userId;
        this.planName=planName;
        this.planStatus= CommonStatus.VALID.getStatus();
        this.startDate=startDate;
        this.endDate=endDate;
        this.createTime=LocalDateTime.now();
        this.updateTime=this.createTime;
    }

}

package com.tq.ad.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 创意表
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_creative")
public class AdCreative implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创意名称
     */
    private String name;
    /**
     * 物料类型(图片, 视频)
     */
    private Integer type;
    /**
     * 物料子类型(图片: bmp, jpg 等等)
     */
    @TableField("material_type")
    private Integer materialType;
    /**
     * 高度
     */
    private Integer height;
    /**
     * 宽度
     */
    private Integer width;
    /**
     * 物料大小, 单位是 KB
     */
    private Long size;
    /**
     * 持续时长, 只有视频才不为 0
     */
    private Integer duration;
    /**
     * 审核状态
     */
    @TableField("audit_status")
    private Integer auditStatus;
    /**
     * 标记当前记录所属用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 物料地址
     */
    private String url;
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


}

package com.imooc.pan.server.modules.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户分享表
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("r_pan_share")
@ApiModel(value="RPanShare对象", description="用户分享表")
public class RPanShare implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分享id")
    @TableId(value = "share_id")
    private Long shareId;

    @ApiModelProperty(value = "分享名称")
    private String shareName;

    @ApiModelProperty(value = "分享类型（0 有提取码）")
    private Integer shareType;

    @ApiModelProperty(value = "分享类型（0 永久有效；1 7天有效；2 30天有效）")
    private Integer shareDayType;

    @ApiModelProperty(value = "分享有效天数（永久有效为0）")
    private Integer shareDay;

    @ApiModelProperty(value = "分享结束时间")
    private Date shareEndTime;

    @ApiModelProperty(value = "分享链接地址")
    private String shareUrl;

    @ApiModelProperty(value = "分享提取码")
    private String shareCode;

    @ApiModelProperty(value = "分享状态（0 正常；1 有文件被删除）")
    private Integer shareStatus;

    @ApiModelProperty(value = "分享创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

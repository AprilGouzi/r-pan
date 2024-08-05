package com.imooc.pan.server.modules.file.entity;

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
 * 用户文件信息表
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("r_pan_user_file")
@ApiModel(value="RPanUserFile对象", description="用户文件信息表")
public class RPanUserFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件记录ID")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "上级文件夹ID,顶级文件夹为0")
    private Long parentId;

    @ApiModelProperty(value = "真实文件id")
    private Long realFileId;

    @ApiModelProperty(value = "文件名")
    private String filename;

    @ApiModelProperty(value = "是否是文件夹 （0 否 1 是）")
    private Integer folderFlag;

    @ApiModelProperty(value = "文件大小展示字符")
    private String fileSizeDesc;

    @ApiModelProperty(value = "文件类型（1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv）")
    private Integer fileType;

    @ApiModelProperty(value = "删除标识（0 否 1 是）")
    private Integer delFlag;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}

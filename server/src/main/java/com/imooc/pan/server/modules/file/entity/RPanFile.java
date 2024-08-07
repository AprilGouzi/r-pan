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
 * 物理文件信息表
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("r_pan_file")
@ApiModel(value="RPanFile对象", description="物理文件信息表")
public class RPanFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件id")
    @TableId(value = "file_id")
    private Long fileId;

    @ApiModelProperty(value = "文件名称")
    private String filename;

    @ApiModelProperty(value = "文件物理路径")
    private String realPath;

    @ApiModelProperty(value = "文件实际大小")
    private String fileSize;

    @ApiModelProperty(value = "文件大小展示字符")
    private String fileSizeDesc;

    @ApiModelProperty(value = "文件后缀")
    private String fileSuffix;

    @ApiModelProperty(value = "文件预览的响应头Content-Type的值")
    private String filePreviewContentType;

    @ApiModelProperty(value = "文件唯一标识")
    private String identifier;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

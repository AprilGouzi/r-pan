package com.imooc.pan.server.modules.file.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 查询用户已上传的分片列表的上下文信息实体
 */
@Data
public class QueryUploadedChunksContext implements Serializable {


    private static final long serialVersionUID = -6668874144550869732L;

    /**
     * 文件的唯一标识
     */
    private String identifier;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

}

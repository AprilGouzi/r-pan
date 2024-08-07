package com.imooc.pan.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 * 秒传文件接口上下文对象实体
 */
@Data
public class SecUploadFileContext implements Serializable {


    private static final long serialVersionUID = -8041792706646696533L;

    /**
     * 文件的父ID
     */
    private Long parentId;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件的唯一标识
     */
    private String identifier;

    /**
     * 当前登录用的ID
     */
    private Long userId;

}

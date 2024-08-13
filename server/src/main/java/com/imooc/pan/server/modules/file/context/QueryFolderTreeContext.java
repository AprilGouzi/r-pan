package com.imooc.pan.server.modules.file.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 查询文件夹树的上文实体信息
 */
@Data
public class QueryFolderTreeContext implements Serializable {


    private static final long serialVersionUID = -4988460507492745858L;
    /**
     * 当前登录的用户ID
     */
    private Long userId;

}

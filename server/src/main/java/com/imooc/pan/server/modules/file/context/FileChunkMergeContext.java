package com.imooc.pan.server.modules.file.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import com.imooc.pan.server.modules.file.entity.RPanFile;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件分片合并的上下文实体对象
 */
@Data
public class FileChunkMergeContext implements Serializable {


    private static final long serialVersionUID = 9151462878163402123L;
    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件唯一标识
     */
    private String identifier;

    /**
     * 文件总大小
     */
    private Long totalSize;

    /**
     * 文件的父文件夹ID
     */
    private Long parentId;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 物理文件记录
     */
    private RPanFile record;

}

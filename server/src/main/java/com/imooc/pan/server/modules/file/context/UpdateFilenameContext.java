package com.imooc.pan.server.modules.file.context;

import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Data
public class UpdateFilenameContext implements Serializable {


    private static final long serialVersionUID = 63377615876651798L;

    /**
     * 要更新的文件ID
     */
    private Long fileId;

    /**
     * 新的文件名称
     */
    private String newFilename;

    /**
     * 当前的登录用户ID
     */
    private Long userId;

    /**
     * 要更新的文件记录实体
     */
    private RPanUserFile entity;
}

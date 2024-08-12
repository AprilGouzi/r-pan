package com.imooc.pan.storage.engine.core.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author AprilGouzi
 * version 1.0
 * 删除物理文件的上下文实体信息
 */
@Data
public class DeleteFileContext implements Serializable {

    private static final long serialVersionUID = -5303839556846261551L;

    /**
     * 要删除物理文件路径的集合
     */
    private List<String> realFilePathList;
}

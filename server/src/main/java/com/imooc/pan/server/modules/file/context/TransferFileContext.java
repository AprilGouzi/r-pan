package com.imooc.pan.server.modules.file.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文件转移操作上下文实体对象
 */
@Data
public class TransferFileContext implements Serializable {


    private static final long serialVersionUID = 6642209637023580020L;
    /**
     * 要转移的文件ID集合
     */
    private List<Long> fileIdList;

    /**
     * 目标文件夹ID
     */
    private Long targetParentId;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要转移的文件列表
     */
    private List<RPanUserFile> prepareRecords;

}


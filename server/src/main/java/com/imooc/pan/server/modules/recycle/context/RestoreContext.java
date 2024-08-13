package com.imooc.pan.server.modules.recycle.context;

import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Data
public class RestoreContext implements Serializable {

    private static final long serialVersionUID = -4156258098558033477L;
    /**
     * 要操作的文件ID的集合
     */
    private List<Long> fileIdList;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要被还原的文件记录列表
     */
    private List<RPanUserFile> records;
}

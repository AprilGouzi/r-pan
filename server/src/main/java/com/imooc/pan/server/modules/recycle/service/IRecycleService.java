package com.imooc.pan.server.modules.recycle.service;

import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import com.imooc.pan.server.modules.recycle.context.QueryRecycleFileListContext;
import com.imooc.pan.server.modules.recycle.context.RestoreContext;

import java.util.List;

/**
 * @author AprilGouzi
 * version 1.0
 */
public interface IRecycleService {

    /**
     * 查询用户的回收站文件列表
     *
     * @param context
     * @return
     */
    List<RPanUserFileVO> recycles(QueryRecycleFileListContext context);

    /**
     * 文件还原
     *
     * @param context
     */
    void restore(RestoreContext context);
}

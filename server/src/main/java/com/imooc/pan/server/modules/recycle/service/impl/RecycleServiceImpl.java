package com.imooc.pan.server.modules.recycle.service.impl;

import com.imooc.pan.server.modules.file.context.QueryFileListContext;
import com.imooc.pan.server.modules.file.enums.DelFlagEnum;
import com.imooc.pan.server.modules.file.service.IUserFileService;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import com.imooc.pan.server.modules.recycle.context.QueryRecycleFileListContext;
import com.imooc.pan.server.modules.recycle.service.IRecycleService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service
public class RecycleServiceImpl implements IRecycleService, ApplicationContextAware {

    @Autowired
    private IUserFileService iUserFileService;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    /**
     * 查询用户的回收站文件列表
     *
     * @param context
     * @return
     */
    @Override
    public List<RPanUserFileVO> recycles(QueryRecycleFileListContext context) {
        QueryFileListContext queryFileListContext = new QueryFileListContext();
        queryFileListContext.setUserId(context.getUserId());
        queryFileListContext.setDelFlag(DelFlagEnum.YES.getCode());
        return iUserFileService.getFileList(queryFileListContext);
    }
}

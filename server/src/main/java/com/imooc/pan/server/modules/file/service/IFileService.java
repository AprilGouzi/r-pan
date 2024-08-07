package com.imooc.pan.server.modules.file.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.file.context.QueryRealFileListContext;
import com.imooc.pan.server.modules.file.entity.RPanFile;

import java.util.List;

/**
 * <p>
 * 物理文件信息表 服务类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface IFileService extends IService<RPanFile> {

    List<RPanFile> getFileList(QueryRealFileListContext context);
}

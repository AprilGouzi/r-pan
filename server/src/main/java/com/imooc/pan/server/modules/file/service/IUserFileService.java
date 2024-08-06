package com.imooc.pan.server.modules.file.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.file.context.CreateFolderContext;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;

/**
 * <p>
 * 用户文件信息表 服务类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface IUserFileService extends IService<RPanUserFile> {

    Long createFolder(CreateFolderContext createFolderContext);

    RPanUserFile getUserRootFile(Long userId);
}

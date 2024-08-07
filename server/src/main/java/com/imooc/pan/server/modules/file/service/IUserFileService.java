package com.imooc.pan.server.modules.file.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.file.context.*;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;

import java.util.List;

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

    List<RPanUserFileVO> getFileList(QueryFileListContext context);

    void updateFilename(UpdateFilenameContext context);

    void deleteFile(DeleteFileContext context);

    boolean secUpload(SecUploadFileContext context);
}

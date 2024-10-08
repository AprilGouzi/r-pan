package com.imooc.pan.server.modules.share.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.share.context.SaveShareFilesContext;
import com.imooc.pan.server.modules.share.entity.RPanShareFile;

/**
 * <p>
 * 用户分享文件表 服务类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
public interface IShareFileService extends IService<RPanShareFile> {

    /**
     * 保存分享的文件的对应关系
     *
     * @param context
     */
    void saveShareFiles(SaveShareFilesContext saveShareFilesContext);

}

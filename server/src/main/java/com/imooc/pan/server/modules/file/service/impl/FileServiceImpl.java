package com.imooc.pan.server.modules.file.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.utils.FileUtils;
import com.imooc.pan.core.utils.IdUtil;
import com.imooc.pan.server.common.event.log.ErrorLogEvent;
import com.imooc.pan.server.modules.file.context.FileSaveContext;
import com.imooc.pan.server.modules.file.context.QueryRealFileListContext;
import com.imooc.pan.storage.engine.core.context.DeleteFileContext;
import com.imooc.pan.storage.engine.core.context.StoreFileContext;
import com.imooc.pan.server.modules.file.entity.RPanFile;
import com.imooc.pan.server.modules.file.mapper.RPanFileMapper;
import com.imooc.pan.server.modules.file.service.IFileService;
import com.imooc.pan.storage.engine.core.StorageEngine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 物理文件信息表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service
public class FileServiceImpl extends ServiceImpl<RPanFileMapper, RPanFile> implements IFileService, ApplicationContextAware {

    @Autowired
    private StorageEngine storageEngine;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * 根据条件查询用户的实际文件列表
     *
     * @param context
     * @return
     */
    @Override
    public List<RPanFile> getFileList(QueryRealFileListContext context) {
        Long userId = context.getUserId();
        String identifier = context.getIdentifier();
        LambdaQueryWrapper<RPanFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(userId), RPanFile::getCreateUser, userId);
        queryWrapper.eq(StringUtils.isNotBlank(identifier), RPanFile::getIdentifier, identifier);
        return list(queryWrapper);
    }

    /**
     * 上传单文件并保存实体记录
     * <p>
     * 1、上传单文件
     * 2、保存实体记录
     *
     * @param context
     */
    @Override
    public void saveFile(FileSaveContext context) {
        storeMultipartFile(context);
        RPanFile record = doSaveFile(context.getFilename(),
                context.getRealPath(),
                context.getTotalSize(),
                context.getIdentifier(),
                context.getUserId());
        context.setRecord(record);
    }

    /**
     * 保存实体文件记录
     *
     * @param filename
     * @param realPath
     * @param totalSize
     * @param identifier
     * @param userId
     * @return
     */
    private RPanFile doSaveFile(String filename, String realPath, Long totalSize, String identifier, Long userId) {
        RPanFile record = assembleRPanFile(filename, realPath, totalSize, identifier, userId);
        if (!save(record)) {
            //保存不成功就删除
            try {
                DeleteFileContext deleteFileContext = new DeleteFileContext();
                /**
                 * ArrayList列表和数组区别
                 * ArrayList是一个动态数组，可以动态增加和删除数据，动态设置空间大小，数组空间大小是固定的
                 * ArrayList只能包含引用类型数据，数组可以包含基本数据类型和引用数据类型
                 * ArrayList中有新增、删除等方法，数组没有
                 */
                //Lists.newArrayList()创建一个ArrayList
                deleteFileContext.setRealFilePathList(Lists.newArrayList(realPath));
                storageEngine.delete(deleteFileContext);
            } catch (Exception e) {
                e.printStackTrace();
                ErrorLogEvent errorLogEvent = new ErrorLogEvent(this, "文件物理删除失败，请执行手动删除！文件路径: " + realPath, userId);
                applicationContext.publishEvent(errorLogEvent);
            }
        }
        return record;
    }

    /**
     * 拼装文件实体对象
     *
     * @param filename
     * @param realPath
     * @param totalSize
     * @param identifier
     * @param userId
     * @return
     */
    private RPanFile assembleRPanFile(String filename, String realPath, Long totalSize, String identifier, Long userId) {
        RPanFile record = new RPanFile();

        record.setFileId(IdUtil.get());
        record.setFilename(filename);
        record.setRealPath(realPath);
        record.setFileSize(String.valueOf(totalSize));
        record.setFileSizeDesc(FileUtils.byteCountToDisplaySize(totalSize));
        record.setFileSuffix(FileUtils.getFileSuffix(filename));
        record.setIdentifier(identifier);
        record.setCreateUser(userId);
        record.setCreateTime(new Date());

        return record;
    }

    /**
     * 上传单文件
     * 该方法委托文件存储引擎实现
     *
     * @param context
     */
    private void storeMultipartFile(FileSaveContext context) {
        try {
            StoreFileContext storeFileContext = new StoreFileContext();
            storeFileContext.setInputStream(context.getFile().getInputStream());
            storeFileContext.setFilename(context.getFilename());
            storeFileContext.setTotalSize(context.getTotalSize());
            storageEngine.store(storeFileContext);
            context.setRealPath(storeFileContext.getRealPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RPanBusinessException("文件上传失败");
        }

    }


}

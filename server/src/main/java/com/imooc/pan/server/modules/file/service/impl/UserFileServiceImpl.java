package com.imooc.pan.server.modules.file.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.core.constants.RPanConstants;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.utils.IdUtil;
import com.imooc.pan.server.modules.file.constants.FileConstants;
import com.imooc.pan.server.modules.file.context.CreateFolderContext;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.imooc.pan.server.modules.file.enums.DelFlagEnum;
import com.imooc.pan.server.modules.file.enums.FolderFlagEnum;
import com.imooc.pan.server.modules.file.mapper.RPanUserFileMapper;
import com.imooc.pan.server.modules.file.service.IUserFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户文件信息表 服务实现类
 * </p>
 *
 * @author aprilgouzi
 * @since 2024-07-23
 */
@Service(value = "userFileService")
public class UserFileServiceImpl extends ServiceImpl<RPanUserFileMapper, RPanUserFile> implements IUserFileService {

    /**
     * 创建文件夹信息
     *
     * @param createFolderContext
     * @return
     */
    @Override
    public Long createFolder(CreateFolderContext createFolderContext) {
        return saveUserFile(createFolderContext.getParentId(),
                createFolderContext.getFolderName(),
                //1 文件夹
                FolderFlagEnum.YES,
                null,
                null,
                createFolderContext.getUserId(),
                null);
    }

    /**
     * 保存用户文件的映射记录
     *
     * @param parentId
     * @param filename
     * @param folderFlagEnum
     * @param fileType       文件类型（1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv）
     * @param realFileId
     * @param userId
     * @param fileSizeDesc
     * @return
     */
    private Long saveUserFile(Long parentId,
                              String filename,
                              FolderFlagEnum folderFlagEnum,
                              Integer fileType,
                              Long realFileId,
                              Long userId,
                              String fileSizeDesc) {
        RPanUserFile entity = assembleRPanFUserFile(parentId, userId, filename, folderFlagEnum, fileType, realFileId, fileSizeDesc);
        if (!save(entity)) {
            throw new RPanBusinessException("保存文件信息失败");
        }
        return entity.getFileId();
    }

    /**
     * 用户文件映射关系实体转化
     * 1、构建并填充实体
     * 2、处理文件命名一致的问题
     *
     * @param parentId
     * @param userId
     * @param filename
     * @param folderFlagEnum
     * @param fileType
     * @param realFileId
     * @param fileSizeDesc
     * @return
     */
    private RPanUserFile assembleRPanFUserFile(Long parentId, Long userId, String filename, FolderFlagEnum folderFlagEnum, Integer fileType, Long realFileId, String fileSizeDesc) {
        RPanUserFile entity = new RPanUserFile();

        //文件ID
        entity.setFileId(IdUtil.get());
        //用户ID
        entity.setUserId(userId);
        //父文件夹ID
        entity.setParentId(parentId);
        //真实文件夹ID
        entity.setRealFileId(realFileId);
        //文件名
        entity.setFilename(filename);
        //是否是文件夹
        entity.setFolderFlag(folderFlagEnum.getCode());
        //文件大小展现
        entity.setFileSizeDesc(fileSizeDesc);
        //文件类型（1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv）
        entity.setFileType(fileType);
        //删除标识
        entity.setDelFlag(DelFlagEnum.NO.getCode());
        //创建用户ID
        entity.setCreateUser(userId);
        //创建时间
        entity.setCreateTime(new Date());
        //更新用户ID
        entity.setUpdateUser(userId);
        //更新时间
        entity.setUpdateTime(new Date());

        //处理文件名重复
        handleDuplicateFilename(entity);

        return entity;
    }

    /**
     * 处理用户重复名称
     * 如果同一文件夹下面有文件名称重复
     * 按照系统级规则重命名文件
     *
     * @param entity
     */
    private void handleDuplicateFilename(RPanUserFile entity) {
        //获取文件名
        String filename = entity.getFilename();
        //新文件名（无后缀）
        String newFilenameWithoutSuffix;
        //新文件名（有后缀）
        String newFilenameSuffix;
        //.doc .txt 小数点位置
        int newFilenamePointPosition = filename.lastIndexOf(RPanConstants.POINT_STR);
        //没有 '.' 就是文件夹
        if (newFilenamePointPosition == RPanConstants.MINUS_ONE_INT) {
            //没有后缀，就是文件名
            newFilenameWithoutSuffix = filename;
            //后缀为空
            newFilenameSuffix = StringUtils.EMPTY;
        } else {
            //截取后缀
            newFilenameWithoutSuffix = filename.substring(RPanConstants.ZERO_INT, newFilenamePointPosition);
            //新文件名,将后缀替换为空
            newFilenameSuffix = filename.replace(newFilenameWithoutSuffix, StringUtils.EMPTY);
        }

        //判断是否有同名的文件
        int count = getDuplicateFilename(entity, newFilenameWithoutSuffix);

        //没有则直接返回
        if (count == 0) {
            return;
        }

        //有同名，则进行处理 a（1）a（2）格式
        String newFilename = assembleNewFilename(newFilenameWithoutSuffix, count, newFilenameSuffix);
        entity.setFilename(newFilename);
    }

    /**
     * 拼装新文件名称
     * 拼装规则参考操作系统重复文件名称的重命名规范
     *
     * @param newFilenameWithoutSuffix
     * @param count
     * @param newFilenameSuffix
     * @return
     */
    private String assembleNewFilename(String newFilenameWithoutSuffix, int count, String newFilenameSuffix) {
        // filename
        StringBuilder newFilename = new StringBuilder(newFilenameWithoutSuffix);
        //filename(
        newFilename.append(FileConstants.CN_LEFT_PARENTHESES_STR);
        //filename(1
        newFilename.append(count);
        //filename(1)
        newFilename.append(FileConstants.CN_RIGHT_PARENTHESES_STR);
        //filename(1).doc
        newFilename.append(newFilenameSuffix);
        return newFilename
                .toString();//转换字符串返回
    }

    /**
     * 查找同一父文件夹下面的同名文件数量
     *
     * @param entity
     * @param newFilenameWithoutSuffix
     * @return
     */
    private int getDuplicateFilename(RPanUserFile entity, String newFilenameWithoutSuffix) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", entity.getParentId());
        queryWrapper.eq("folder_flag", entity.getFolderFlag());
        queryWrapper.eq("user_id", entity.getUserId());
        queryWrapper.eq("del_flag", DelFlagEnum.NO.getCode());
        //左侧加上通配符 %filename
        queryWrapper.likeLeft("filename", newFilenameWithoutSuffix);
        //计算有多少条 （1）（2）（3）
        return count(queryWrapper);
    }
}

package com.imooc.pan.server.modules.file.controller;

import com.google.common.base.Splitter;
import com.imooc.pan.core.constants.RPanConstants;
import com.imooc.pan.core.response.R;
import com.imooc.pan.core.utils.IdUtil;
import com.imooc.pan.server.common.utils.UserIdUtil;
import com.imooc.pan.server.modules.file.constants.FileConstants;
import com.imooc.pan.server.modules.file.context.*;
import com.imooc.pan.server.modules.file.converter.FileConverter;
import com.imooc.pan.server.modules.file.enums.DelFlagEnum;
import com.imooc.pan.server.modules.file.po.CreateFolderPO;
import com.imooc.pan.server.modules.file.po.DeleteFilePO;
import com.imooc.pan.server.modules.file.po.SecUploadFilePO;
import com.imooc.pan.server.modules.file.po.UpdateFilenamePO;
import com.imooc.pan.server.modules.file.service.IUserFileService;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author AprilGouzi
 * version 1.0
 * 文件模块控制器
 */
@RestController
@Validated
@Api(tags = "文件模块")
public class FileController {


    @Autowired
    private IUserFileService iUserFileService;

    @Autowired
    private FileConverter fileConverter;

    @ApiOperation(
            value = "查询文件列表",
            notes = "该接口提供了用户插叙某文件夹下面某些文件类型的文件列表的功能",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @GetMapping("files")
    public R<List<RPanUserFileVO>> list(@NotBlank(message = "父文件夹ID不能为空") @RequestParam(value = "parentId", required = false) String parentId,
                                        @RequestParam(value = "fileTypes", required = false, defaultValue = FileConstants.ALL_FILE_TYPE) String fileTypes) {
        Long realParentId = IdUtil.decrypt(parentId);
        List<Integer> fileTypeArray = null;

        if (!Objects.equals(FileConstants.ALL_FILE_TYPE, fileTypes)) {
            fileTypeArray = Splitter.on(RPanConstants.COMMON_SEPARATOR).splitToList(fileTypes)
                    .stream().map(Integer::valueOf).collect(Collectors.toList());
        }

        QueryFileListContext context = new QueryFileListContext();
        context.setParentId(realParentId);
        context.setFileTypeArray(fileTypeArray);
        context.setUserId(UserIdUtil.get());
        context.setDelFlag(DelFlagEnum.NO.getCode());

        List<RPanUserFileVO> result = iUserFileService.getFileList(context);
        return R.data(result);
    }

    @ApiOperation(
            value = "创建文件夹",
            notes = "该接口提供了创建文件夹的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("/file/folder")
    public R<String> createFolder(@Validated @RequestBody CreateFolderPO createFolderPO) {
        CreateFolderContext context = fileConverter.createFolderPO2CreateFolderContext(createFolderPO);
        Long fileId = iUserFileService.createFolder(context);
        return R.data(IdUtil.encrypt(fileId));
    }

    @ApiOperation(
            value = "文件重命名",
            notes = "该接口提供了文件重命名的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PutMapping("/file")
    public R updateFilename(@Validated @RequestBody UpdateFilenamePO updateFilenamePO) {
        UpdateFilenameContext context = fileConverter.updateFilenamePO2UpdateFilenameContext(updateFilenamePO);
        iUserFileService.updateFilename(context);
        return R.success();
    }

    @ApiOperation(
            value = "批量删除文件",
            notes = "该接口提供了批量删除文件的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @DeleteMapping("/file")
    public R deleteFile(@Validated @RequestBody DeleteFilePO deleteFilePO) {
        DeleteFileContext context = fileConverter.deleteFilePO2DeleteFileContext(deleteFilePO);

        String fileIds = deleteFilePO.getFileIds();
        List<Long> fileIdList = Splitter.on(RPanConstants.COMMON_SEPARATOR).splitToList(fileIds)
                .stream().map(IdUtil::decrypt).collect(Collectors.toList());

        context.setFileIdList(fileIdList);
        iUserFileService.deleteFile(context);
        return R.success();
    }

    @ApiOperation(
            value = "文件秒传",
            notes = "该接口提供了文件秒传的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("/file/sec-upload")
    public R secUpload(@Validated @RequestBody SecUploadFilePO secUploadFilePO) {
        SecUploadFileContext context = fileConverter.secUploadFilePO2SecUploadFileContext(secUploadFilePO);
        boolean result = iUserFileService.secUpload(context);
        if (result) {
            return R.success();
        }
        return R.fail("文件唯一标识不存在，请动手执行文件上传");
    }
}

package com.imooc.pan.server.modules.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AprilGouzi
 * version 1.0
 * 文件夹表示枚举类
 */
@AllArgsConstructor
@Getter
public enum FolderFlagEnum {

    /**
     * 非文件夹
     */
    NO(0),
    /**
     * 文件夹
     */
    YES(1);

    private Integer code;

}

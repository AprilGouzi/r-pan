package com.imooc.pan.server.modules.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AprilGouzi
 * version 1.0
 * 文件删除标识枚举类
 */
@AllArgsConstructor
@Getter
public enum DelFlagEnum {

    /**
     * 未删除
     */
    NO(0),
    /**
     * 已删除
     */
    YES(1);

    private Integer code;

}

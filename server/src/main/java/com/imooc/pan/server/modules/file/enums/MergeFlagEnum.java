package com.imooc.pan.server.modules.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Getter
@AllArgsConstructor
public enum MergeFlagEnum {

    /**
     * 不需要合并
     */
    NOT_READY(0),

    /**
     * 需要合并
     */
    READY(1);

    private Integer code;
}

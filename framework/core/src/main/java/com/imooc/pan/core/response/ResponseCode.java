package com.imooc.pan.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 囍崽
 * version 1.0
 * 通用返回状态码
 */

/**
 * <body>
 *     <p>自定义枚举类</p><br>
 *     <p>1.对象属性，用private final修饰</p><br>
 *     <p>2.构造器私有化，private修饰</p><br>
 *     <p>3.对象在类中创建完成,public static final修饰</p><br>
 *     <p>4.可以存在其他的方法，如对象属性的getter方法，toString方法</p>
 * </body>
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),

    /**
     * 错误
     */
    ERROR(1, "ERROR"),

    /**
     * token过期
     */
    TOKEN_EXPIRE(2, "TOKEN_EXPIRE"),

    /**
     * 参数错误
     */
    ERROR_PARAM(3, "ERROR_PARAM"),

    /**
     * 无权限访问
     */
    ACCESS_DENIED(4, "ACCESS_DENIED"),

    /**
     * 分享的文件丢失
     */
    SHARE_FILE_MISS(5, "分享的文件丢失"),
    /**
     * 分享已经被取消
     */
    SHARE_CANCELLED(6, "分享已经被取消"),
    /**
     * 分享已过期
     */
    SHARE_EXPIRE(7, "分享已过期"),
    /**
     * 需要登录
     */
    NEED_LOGIN(10, "NEED_LOGIN");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String desc;
}

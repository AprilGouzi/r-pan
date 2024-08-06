package com.imooc.pan.server.modules.user.constants;

/**
 * @author AprilGouzi
 * version 1.0
 */
public interface UserConstants {

    /**
     * 登录用户的用户ID的key值
     */
    String LOGIN_USER_ID = "LOGIN_USER_ID";

    /**
     * 一天的毫秒值
     */
    Long ONE_DAY_LONG = 24L * 60L * 60L * 1000L;

    /**
     * 用户登录缓存前缀
     */
    String USER_LOGIN_PREFIX = "USER_LOGIN_";
}

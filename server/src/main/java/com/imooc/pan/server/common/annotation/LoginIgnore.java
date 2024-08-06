package com.imooc.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * @author AprilGouzi
 * version 1.0
 * 统一的登录拦截校验切面逻辑实现类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginIgnore {

}

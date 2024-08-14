package com.imooc.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NeedShareCode {
}

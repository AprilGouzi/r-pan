package com.imooc.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 * 重置用户密码上下文信息实体
 */
@Data
public class ResetPasswordContext implements Serializable {

    private static final long serialVersionUID = -3904832331163083745L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户新密码
     */
    private String password;

    /**
     * 重置密码的token信息
     */
    private String token;
}

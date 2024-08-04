package com.imooc.pan.server.modules.user.context;

import com.imooc.pan.server.modules.user.entity.RPanUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 * 用户注册业务的上下文实体对象
 */
@Data
public class UserRegisterContext implements Serializable {
    private static final long serialVersionUID = -4835860208501507531L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;

    /**
     * 用户实体对象
     */
    private RPanUser entity;
}

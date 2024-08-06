package com.imooc.pan.server.modules.user.context;

import com.imooc.pan.server.modules.user.entity.RPanUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Data
public class UserLoginContext implements Serializable {

    private static final long serialVersionUID = -7235108651137977946L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户实体对象
     */
    private RPanUser entity;

    /**
     * 登陆成功之后的凭证信息
     */
    private String accessToken;
}

package com.imooc.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 * 校验用户名称PO对象
 */
@Data
public class CheckUsernameContext implements Serializable {

    private static final long serialVersionUID = -8461463417727383663L;

    /**
     * 用户名称
     */
    private String username;
}

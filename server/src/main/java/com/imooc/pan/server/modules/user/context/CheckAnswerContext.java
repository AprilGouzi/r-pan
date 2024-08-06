package com.imooc.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AprilGouzi
 * version 1.0
 * 校验密保答案PO对象
 */
@Data
public class CheckAnswerContext implements Serializable {

    private static final long serialVersionUID = 3999256925566938587L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;

}

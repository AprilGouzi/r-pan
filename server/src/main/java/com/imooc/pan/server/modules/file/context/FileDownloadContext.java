package com.imooc.pan.server.modules.file.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 文件下载的上下文实体对象
 */
@Data
public class FileDownloadContext implements Serializable {


    private static final long serialVersionUID = 4195633574997691186L;
    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 请求响应对象
     */
    private HttpServletResponse response;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

}

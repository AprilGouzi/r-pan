package com.imooc.pan.storage.engine.core.context;

/**
 * @author AprilGouzi
 * version 1.0
 */

import lombok.Data;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * 文件读取的上下文实体信息
 */
@Data
public class ReadFileContext implements Serializable {


    private static final long serialVersionUID = -8766755523348400748L;
    /**
     * 文件的真实存储路径
     */
    private String realPath;

    /**
     * 文件的输出流
     */
    private OutputStream outputStream;

}

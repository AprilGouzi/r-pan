package com.imooc.pan.storage.engine.local.config;

import com.imooc.pan.core.utils.FileUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Component
@ConfigurationProperties(prefix = "com.imooc.pan.storage.engine.local")
@Data
public class LocalStorageEngineConfig {

    /**
     * 实际存放路径的前缀
     */
    private String rootFilePath = FileUtils.generateDefaultStoreFileRealPath();

    /**
     * 实际存放文件分片的路径的前缀
     */
    private String rootFileChunkPath = FileUtils.generateDefaultStoreFileChunkRealPath();
}

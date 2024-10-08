package com.imooc.pan.cache.caffeine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author AprilGouzi
 * version 1.0
 * Caffeine Cache自定义配置属性类
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.imooc.pan.cache.caffeine")
public class CaffeineCacheProperties {

    /**
     * 缓存初始容量
     * com.imooc.pan.cache.caffeine.init-cache-capacity
     */
    private Integer initCacheCapacity = 256;

    /**
     * 缓存最大容量，超过之后会按照recently or very often（最近最少）策略进行缓存剔除
     * com.imooc.pan.cache.caffeine.max-cache-capacity
     */
    private Long maxCacheCapacity = 10000L;

    /**
     * 是否允许空值null作为缓存的value
     * com.imooc.pan.cache.caffeine.allow-null-value
     */
    private Boolean allowNUllValue = Boolean.TRUE;
}

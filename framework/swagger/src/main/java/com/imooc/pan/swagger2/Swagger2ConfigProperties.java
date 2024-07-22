package com.imooc.pan.swagger2;

import com.imooc.pan.core.constants.RPanConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 囍崽
 * version 1.0
 * swagger2配置属性实体
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2ConfigProperties {

    private boolean show = true;

    private String groupName = "r-pan";

    private String basePackage = RPanConstants.BASE_COMPONENT_SCAN_PATH;

    private String title = "r-pan-server";

    private String description = "r-pan-server";

    private String termOfServiceUrl = "http://127.0.0.1:${server.port}";

    private String contactName = "aprilgouzi";

    private String concatUrl = "https://blog.rubinchu.com";

    private String concatEmail = "aprilgouzi@163.com";

    private String version = "1.0";
}

package com.imooc.pan.server;

import com.imooc.pan.core.constants.RPanConstants;
import com.imooc.pan.core.response.R;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 囍崽
 * version 1.0
 */

/**
 * <body>
 *     <p>
 * 在SpringBootApplication上使用@ServletComponentScan注解后，
 * Servlet（控制器）、Filter（过滤器）、Listener（监听器）
 * 可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册到Spring容器中，无需其他代码。
 *     </p>
 * </body>
 */
@SpringBootApplication(scanBasePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
@ServletComponentScan(basePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
@RestController
public class RPanServerLauncher {
    public static void main(String[] args) {
        SpringApplication.run(RPanServerLauncher.class, args);
    }

    @GetMapping("/hello")
    public R<String> hello(String name){
        return R.success("hello " + name + "change!");
    }
}

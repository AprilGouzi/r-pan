package com.imooc.pan.web.cors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 囍崽
 * version 1.0
 * 支持跨域全局过滤器
 */

/**
 * <body>
 * <h3>Filter接口中的三个方法</h3>
 * <p>
 * init(): 该方法在容器启动初始化过滤器时被调用，它在Filter的整个生命周期只会被调用一次
 * 注意：这个方法必须执行成功，否则过滤器会不起作用
 * </p><br>
 * <p>
 * doFilter(): 容器中每一次请求都会调用该方法，从而实现过滤功能。doFilter()方法中
 * ，chain.doFilter()前的一般是对request执行的过滤操作，chain.doFilter后面的代码
 * 一般是对response执行的操作
 * </p><br>
 * <p>
 * destroy()：当容器销毁，过滤器实例时调用该方法，
 * 一般在方法中销毁或关闭资源，在过滤器 Filter的整个生命周期也只会被调用一次。
 * 该方法的使用是为了完成某些资源的回收。
 * </p>
 * </body>
 */
@WebFilter(filterName = "corsFilter")
@Order(1)
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        addCorsResponseHeader(response);
        // 将请求转发给过滤器链下一个filter,如果没有filter那就是你请求的资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 添加跨域的响应头
     *
     * @param response
     */
    private void addCorsResponseHeader(HttpServletResponse response) {
        response.setHeader(CorsConfigEnum.CORS_ORIGIN.getKey(), CorsConfigEnum.CORS_ORIGIN.getValue());
        response.setHeader(CorsConfigEnum.CORS_CREDENTIALS.getKey(), CorsConfigEnum.CORS_CREDENTIALS.getValue());
        response.setHeader(CorsConfigEnum.CORS_METHODS.getKey(), CorsConfigEnum.CORS_METHODS.getValue());
        response.setHeader(CorsConfigEnum.CORS_MAX_AGE.getKey(), CorsConfigEnum.CORS_MAX_AGE.getValue());
        response.setHeader(CorsConfigEnum.CORS_HEADERS.getKey(), CorsConfigEnum.CORS_HEADERS.getValue());
    }

    @AllArgsConstructor
    @Getter
    public enum CorsConfigEnum {

        /**
         * 允许所有远程访问
         */
        CORS_ORIGIN("Access-Control-Allow-Origin", "*"),
        /**
         * 允许认证
         */
        CORS_CREDENTIALS("Access-Control-Allow-Credentials", "true"),
        /**
         * 允许远程调用请求类型
         */
        CORS_METHODS("Access-Control-Allow-Methods", "POST,GET,PATCH,DELETE,PUT"),
        /**
         * 指定本次预检请求的有效期，单位是秒
         */
        CORS_MAX_AGE("Access-Control-Max-Age", "3600"),
        /**
         * 允许所有请求头
         */
        CORS_HEADERS("Access-Control-Allow-Headers", "*");

        private String key;
        private String value;
    }
}

package com.imooc.pan.web.log;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * HttpLogEntity构造器
 */
public class HttpLogEntityBuilder {

    /**
     * <body>
     *     构建HTTP日志对象<br>
     *     <p>ContentCachingRequestWrapper:<br>
     *     把一个HTTP的请求，响应信息完整的纪录到日志。是一种常见有效的问题排查，BUG重现的手段。
     *      但是流这种东西，有一个特点就是只能读取/写入一次，不能重复。下一次读写，就是一个空的流，
     *      为了实现流的重用，就很有必要，把读取和写入的数据缓存起来， 可以在某个地方，再一次的读取。
     *      ContentCachingRequestWrapper可以把请求体缓存到内存中。
     *     </p>
     * </body>
     *
     *
     * @param requestWrapper
     * @param responseWrapper
     * @param stopWatch
     * @return
     */
    public static HttpLogEntity build(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, StopWatch stopWatch) {
        HttpLogEntity httpLogEntity = new HttpLogEntity();
        httpLogEntity.setRequestUri(requestWrapper.getRequestURI())
                .setMethod(requestWrapper.getMethod())
                .setRemoteAddr(requestWrapper.getRemoteAddr())
                .setIp(getIpAddress(requestWrapper))
                .setRequestHeaders(getRequestHeaderMap(requestWrapper));
        if (requestWrapper.getMethod().equals(RequestMethod.GET.name())) {
            httpLogEntity.setRequestParams(JSON.toJSONString(requestWrapper.getParameterMap()));
        } else {
            httpLogEntity.setRequestParams(new String(requestWrapper.getContentAsByteArray()));
        }
        String responseContentType = responseWrapper.getContentType();
        if (StringUtils.equals("application/json;charset=UTF-8", responseContentType)) {
            httpLogEntity.setResponseData(new String(responseWrapper.getContentAsByteArray()));
        } else {
            httpLogEntity.setResponseData("Stream Body...");
        }
        httpLogEntity.setStatus(responseWrapper.getStatusCode())
                .setResponseHeaders(getResponseHeaderMap(responseWrapper))
                .setResolveTime(stopWatch.toString());
        return httpLogEntity;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取请求头MAP
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, String> result = Maps.newHashMap();
        if (Objects.nonNull(request)) {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (Objects.nonNull(request)) {
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
                    result.put(headerName, headerValue);
                }
            }
        }
        return result;
    }

    /**
     * 获取响应头MAP
     *
     * @param response
     * @return
     */
    public static Map<String, String> getResponseHeaderMap(HttpServletResponse response) {
        Map<String, String> result = Maps.newHashMap();
        if (Objects.nonNull(response)) {
            String contentType = response.getContentType();
            result.put("contentType", contentType);
        }
        return result;
    }

}

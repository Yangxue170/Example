package org.example.http;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jdx
 * @version 1.0
 * @description 拦截器，对http请求进行拦截
 * @date 2021/4/14 15:59
 */

public class RestTemplateLogInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateLogInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        //请求路径
        String query = request.getURI().getQuery();
        //请求处理的方法名称
        String method = request.getMethod().name();
        //过滤掉请求头中不关心的参数
        String headers = JSON.toJSONString(filterHeader(request.getHeaders()));
        // 只保留不包含参数的url
        String url = StringUtils.replace(request.getURI().toString(), "?" + query, "");
        //处理get请求中的参数问题
        String param = appendParam(query, body);
        // 处理请求开始时间
        long start = System.currentTimeMillis();
        //返回结果数据
        ClientHttpResponse response;
        try {
            //执行具体的请求，拿到返回值
            response = execution.execute(request, body);
            //请求执行完成
            long end = System.currentTimeMillis();
            //执行耗时
            Long cost = end - start;
            //http返回值的状态
            HttpStatus httpStatus = response.getStatusCode();
            if (httpStatus.is2xxSuccessful()) {
                //处理成功
                log.info("[call-http-success] url:{}, method:{}, param:{}, header:{}, cost:{}ms",
                        url, method, param, headers, cost);
            } else if (httpStatus.value() == 408 || httpStatus.value() == 504) {
                //状态为408/504超时记录
                log.error("[call-http-timeout] url:{}, method:{}, param:{}, header:{}, status:{}, cost:{}ms",
                        url, method, param, headers, httpStatus.value(), cost);
            } else {
                //其余的都是error
                log.error("[call-http-status-error-{}] url:{}, method:{}, param:{}, header:{}, cost:{}ms",
                        httpStatus.value(), url, method, param, headers, cost);
            }
            return response;
        } catch (Exception e) {
            //调用异常处理
            //耗时
            Long cost = System.currentTimeMillis() - start;
            //按照格式组装log的信息
            String errorMsg = String.format("url:%s, method:%s, param:%s, header:%s, cost:%sms",
                    url, method, param, headers, cost);
            if (e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException) {
                //网络链接超时
                log.warn("[call-http-timeout] " + errorMsg, e);
            } else {
                log.error("[call-http-exception] " + errorMsg, e);
            }
            throw e;
        }
    }

    private String appendParam(String query, byte[] body) {
        String params = StringUtils.trim(query);
        if (ArrayUtils.isNotEmpty(body)) {
            params = StringUtils.isNotEmpty(params) ? (params + "|" + new String(body)) : new String(body);
        }
        return params;
    }

    /**
     * 过滤不需要记录的header
     *
     * @param header header
     * @return map
     */
    private Map<String, Object> filterHeader(HttpHeaders header) {
        Map<String, Object> map = new HashMap<String, Object>(header);
        map.remove(HttpHeaders.ACCEPT_CHARSET);
        return map;
    }
}

package org.example.http;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/14 16:16
 */
@Configuration
public class ClientConfig {

    @Value("${http.socket-timeout:1000}")
    private Integer socketTimeout;
    @Value("${http.connection-timeout:1000}")
    private Integer connectionTimeout;
    @Value("${http.connection-request-timeout:1000}")
    private Integer connectionRequestTimeout;
    @Value("${http.max-connection:400}")
    private Integer maxConnection;
    @Value("${http.max-connection-perRoute:50}")
    private Integer mxConnectionPerRoute;

    /**
     * 注入拦截器
     * 声明为Bean，方便应用内使用同一实例
     * RestTemplate 是调用外部接口使用的实例
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 把自定义的ClientHttpRequestInterceptor添加到RestTemplate，可添加多个
        ClientHttpRequestInterceptor interceptor = new RestTemplateLogInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(interceptor));

        //消息转换器
        StringHttpMessageConverter stringHttpMc = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        FastJsonHttpMessageConverter fastJsonHttpMc = new FastJsonHttpMessageConverter();
        fastJsonHttpMc.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_PLAIN));
        FormHttpMessageConverter formHttpMc = new FormHttpMessageConverter();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(stringHttpMc);
        list.add(fastJsonHttpMc);
        list.add(formHttpMc);
        restTemplate.setMessageConverters(list);

        //
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        //http连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                registry);
        //设置整个连接池最大连接数 根据自己的场景决定
        connectionManager.setMaxTotal(maxConnection);
        //路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(mxConnectionPerRoute);
        RequestConfig requestConfig = RequestConfig.custom()
                //服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setSocketTimeout(socketTimeout)
                //连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectTimeout(connectionTimeout)
                //从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException:
                //Timeout waiting for connection from pool
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
        //设置连接和读取超时
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}

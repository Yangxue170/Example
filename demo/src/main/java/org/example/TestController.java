package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/14 16:58
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("hello")
    public String hello() {

        return getRestTemplateInfo();
    }
    /*
     * 测试测试测试一：
     */


    /*
     * 测试测试测试二： 打印interceptor中的log信息
     * 测试测试测试二： 打印interceptor中的log信息
     * 测试测试测试二： 打印interceptor中的log信息
     */
    /**
     * 自定义的RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 对外部的http请求，进行测试，打印interceptor中的log信息
     *
     * @return 结果
     */
    public String getRestTemplateInfo() {
        String url = "https://www.baidu.com/";
        //get 请求
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.toString();
    }
}

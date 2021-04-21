package org.example.dubbo.rpc;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 18:23
 */
public class AobingServiceImpl implements AobingService{
    @Override
    public String hello(String name) {
        return "Yo man Hello，I am" + name;
    }
}

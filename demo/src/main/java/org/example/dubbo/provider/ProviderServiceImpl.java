package org.example.dubbo.provider;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/27 16:40
 */
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String key) {
        return key;
    }
}

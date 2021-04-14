package org.example.design.patterns.proxy.aop;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:41
 */
public interface IUserService {
    /**
     * 保存
     * @param username
     * @param password
     * @throws Exception
     */
    void saveUser(String username, String password) throws Exception;
}

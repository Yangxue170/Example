package org.example.design.patterns.proxy.aop;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:41
 */
public class UserService implements  IUserService {
    @Override
    public void saveUser(String username, String password) throws Exception {
        System.out.println("save user[username=" + username + ",password=" + password + "]");
    }
}

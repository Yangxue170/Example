package org.example.dubbo.rpc;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 18:34
 */
public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        //服务调用者只需要设置依赖
        AobingService aobingService = AobingRpcFramework.refer(AobingService.class, "127.0.0.1", 8888);
        String hello = aobingService.hello("张家");
        System.out.println(hello);
    }


}

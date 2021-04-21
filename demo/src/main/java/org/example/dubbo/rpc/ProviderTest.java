package org.example.dubbo.rpc;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 18:32
 */
public class ProviderTest {

    public static void main(String[] args) throws Exception {
        //服务提供者只需要暴露出接口
        AobingService service = new AobingServiceImpl ();
        AobingRpcFramework.export(service, 8888);
        System.out.println();
    }
}

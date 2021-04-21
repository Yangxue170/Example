package org.example.dubbo.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 18:24
 */
public class AobingRpcFramework {
    private static final Logger log = LoggerFactory.getLogger(AobingRpcFramework.class);

    public static void export(Object service, int port) throws Exception {
        Socket socket;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("启动服务器....");
            socket = ss.accept();
            System.out.println("客户端:" + socket.getInetAddress().getHostAddress() + "已连接到服务器");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Thread(() -> {
            try {
                //反序列化
                final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                //读取方法名
                String methodName = String.valueOf(input.read());
                //参数类型
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                //参数
                Object[] arguments = (Object[]) input.readObject();
                //找到方法
                Method method = service.getClass().getMethod(methodName, parameterTypes);
                //调用方法
                Object result = method.invoke(service, arguments);
                // 返回结果
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }).start();
    }


    public static <T> T refer(Class<T> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                (proxy, method, arguments) -> {
                    //指定 provider 的 ip 和端口
                    Socket socket = new Socket(host, port);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    //传方法名
                    output.write(method.getName().getBytes(StandardCharsets.UTF_8));  //传方法名
                    //传参数类型
                    output.writeObject(method.getParameterTypes());
                    //传参数值
                    output.writeObject(arguments);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    //读取结果
                    Object result = input.readObject();
                    return result;
                });
    }

}

package org.example.logback;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/20 20:26
 */
public class MlsProviderFilter implements Filter, EnvironmentAware {

    /**
     * 方法的getLogger(String name)参数name对应logback.xml文件中的name属性<-logger name ="dubboProviderLogger"><-/logger>
     */
    private static final Logger PROVIDER_LOGGER = LoggerFactory.getLogger("dubboProviderLogger");

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        PROVIDER_LOGGER.info("测试log数据");
        return null;
    }

    @Override
    public void setEnvironment(Environment environment) {
        PROVIDER_LOGGER.warn("测试log数据");
    }
}


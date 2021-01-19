package org.example.design.patterns.proxy.aop;

import java.util.Objects;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:38
 */
public class CustomAspect implements IAspect {
    @Override
    public boolean startTransaction(Object... args) {
        if (Objects.isNull(args)) {
            return false;
        }
        boolean result = true;
        for (Object temp : args) {
            if (Objects.isNull(temp)) {
                result = false;
                break;
            }
        }
        System.out.println(" start transaction");
        return result;
    }

    @Override
    public void endTransaction() {
        System.out.println("I get datasource here and end transaction");

    }
}

package org.example.design.patterns.proxy.aop;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:37
 */
public interface IAspect {
    /**
     * 在切点接口方法执行之前执行
     *
     * @param args
     * @return
     */
    boolean startTransaction(Object... args);

    /**
     * 在切点接口方法执行之后执行
     */
    void endTransaction();
}

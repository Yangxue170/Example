package org.example.logback;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/20 20:31
 */
public class Slf4jTest {
    /**
     * LoggerFactory.getLogger(Slf4jTest.class)，首先找到name="org.example"这个<logger>
     */
    private static final Logger logger = LoggerFactory.getLogger(Object.class);

    @Test
    public void testLogBack() {
        logger.error("123");
    }


}

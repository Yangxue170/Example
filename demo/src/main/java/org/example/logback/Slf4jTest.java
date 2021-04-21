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
    private static Logger Log = LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    public void testLogBack(){

        Log.debug("Test the MessageFormat for {} to {} endTo {}", 1,2,3);
        Log.info("Test the MessageFormat for {} to {} endTo {}", 1,2,3);
        Log.error("Test the MessageFormat for {} to {} endTo {}", 1,2,3);

        try{
            throw new IllegalStateException("try to throw an Exception");
        }catch(Exception e){
            Log.error(e.getMessage(),e);
        }
    }

}

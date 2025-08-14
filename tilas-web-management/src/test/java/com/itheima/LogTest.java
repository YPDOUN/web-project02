package com.itheima;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger((LogTest.class));

    @Test
    public void testLog() {
        logger.debug("开始计算...");

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        logger.info("计算结果： " + sum);
        logger.debug("结束计算...");
    }
}

package com.liuyihui.common;

import ch.qos.logback.classic.LoggerContext;
import com.liuyihui.common.Enum.EResumeWay;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;

import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_AUTO;
import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_BY_EXTERNAL;


/**
 * 测试类
 */
public class AppTest {
    Logger logger;

    /*@Before
    public void bef() {
        logger = LoggerFactory.getLogger(AppTest.class);
//        logger = LoggerFactory.getLogger("com.liuyihuis");

    }*/


    @Test
    public void testLogbackLogger() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        logger.debug("日志:debug");
        logger.info("日志:info");
        logger.warn("警告:warning");
    }

    @Test
    public void testStringContain() {
        String matFileName = "FJDSKJFDLSFLAG.pdf";
        System.out.println(matFileName.contains("."));
    }

}

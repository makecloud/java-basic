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

    @Before
    public void bef() {
//        logger = LoggerFactory.getLogger(AppTest.class);
        logger = LoggerFactory.getLogger("com.liuyihuis");

    }

    /**
     * 测试类名
     */
    @Test
    public void testClassName() {
        System.out.println(AppTest.class.getPackage());
        System.out.println(AppTest.class.getName());
    }

    /**
     * 测试枚举
     */
    @Test
    public void testSwithEnum() {
        EResumeWay parm = WILL_RESUME_AUTO;
        switch (parm) {
            case WILL_RESUME_AUTO:
                break;
            default:
                break;
        }

//        parm = null;
        switch (parm) {
            case WILL_RESUME_AUTO:
                System.out.println(WILL_RESUME_AUTO);
            case WILL_RESUME_BY_EXTERNAL:
                System.out.println(WILL_RESUME_BY_EXTERNAL);
            default:
                System.out.println("null");
        }

        System.out.println(WILL_RESUME_AUTO);
        System.out.println(WILL_RESUME_AUTO.name());
        System.out.println(EResumeWay.valueOf("WILL_RESUME_AUTO"));
//        System.out.println(Enum.valueOf(EResumeWay.class, ""));//报错
    }

    @Test
    public void testLogbackLogger() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        logger.debug("日志:debug");
        logger.info("日志:info");
        logger.warn("警告:warning");
    }

    /**
     * 测试用整型变量接收除法结果
     */
    @Test
    public void testIntDivision() {
        int num = 1 / 2;
        System.out.println(num);// 结果打印0。
        num = 1 % 2;
        System.out.println(num);// 结果打印1。记住1%2等于1
        num = 5 / 2;
        System.out.println(num);// 结果打印2. 说明结果下取整
        num = 5 % 2;
        System.out.println(num);// 结果打印1. 实际余数
        num = 4 % 2;
        System.out.println(num);// 结果打印0。实际余数

        System.out.println(~1);
        System.out.println(~0);

        System.out.println(52 / 25);
    }


    /**
     * float型除法
     */
    @Test
    public void testFloatDivision() {
        float a = 78f;
        float r = a / 100;
        System.out.println(r);

        System.out.println((int) (0.78f / 0.25f));

    }

    /**
     * 测试split能否分离出""空串
     */
    @Test
    public void testEmpty() {
        String s = "a" + "," + "";
        System.out.println(s.split(",").length);//长度是1
        System.out.println(s.split(",")[0]);//打印a
        System.out.println(s.split(",")[1]);//抛越界异常
    }

    /**
     * 测试substring Index
     */
    @Test
    public void testSubstringIndex() {
        String str = "18:19";
        System.out.println(str.substring(3, 5));
        System.out.println(str.split(":")[0]);
        System.out.println(str.split(":")[1]);
    }

    /**
     * 数字长度不够前面补0
     */
    @Test
    public void numberAdd0() {
        int closeHour = 9;
        System.out.println(String.format("%02d", closeHour));//输出09
        closeHour = 19;
        System.out.println(String.format("%02d", closeHour));//输出19
        closeHour = 199;
        System.out.println(String.format("%02d", closeHour));//输出199
    }

}

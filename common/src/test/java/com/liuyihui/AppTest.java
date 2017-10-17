package com.liuyihui;

import com.liuyihui.common.Enum.EResumeWay;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_AUTO;
import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_BY_EXTERNAL;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void test1() {
        System.out.println(AppTest.class.getPackage());
        System.out.println(AppTest.class.getName());
    }

    public void test2() {
        EResumeWay parm = WILL_RESUME_AUTO;
        switch (parm) {
            case WILL_RESUME_AUTO:
                break;
            default:
                break;
        }

        parm = null;
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

    public void test3() {
        Action1<String> action1 = new Action1<>();
    }
}

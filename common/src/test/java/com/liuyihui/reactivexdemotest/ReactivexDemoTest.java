package com.liuyihui.reactivexdemotest;

import com.liuyihui.common.reactivexdemo.FirstDemo;
import org.junit.Test;

public class ReactivexDemoTest {

    @Test
    public void testHello() {
        new FirstDemo().hello("ben", "george");
    }

    @Test
    public void testHello1() {
        new FirstDemo().hello1();
    }

    @Test
    public void testHello2() {
        new FirstDemo().hello2();
    }
}

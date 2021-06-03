package com.liuyihui.common.dataType;

import org.junit.Test;

public class CommonTest {

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
     *
     */
    @Test
    public void testPrint() {
        // | / - \ | / - \
        char[] c = new char[]{'|', '/', '-', '\\', '|', '/', '-', '\\'};
        int i = 0;
        System.out.println(c[i]);
        while (true) {
            System.out.print(c[i]);
            System.out.print("\r");
            i++;
            if (i >= c.length) {
                i = 0;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

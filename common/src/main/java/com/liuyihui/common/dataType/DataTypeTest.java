package com.liuyihui.common.dataType;

import org.junit.Test;

public class DataTypeTest {

    /**
     * 想用8bit,往左位移,高位(最左位)移出,来计算业务.
     * 遇到的问题是:byte类型位移前会将8位的字节,转为int类型后位移, 位移后拿到的是一个int了. 不能实现高位(最左位)移出去的效果.
     */
    @Test
    public void bitMove() {

        /*
         * 增
         */
        //定义哪个分辨率存在,按位拼字符串
        String str = "10001";
        //转换为10进制存,然后可存数据库.
        Integer data = Integer.valueOf(str, 2);
        // dao.create();


        /*
         * 查
         */
        //从数据库取出10进制数据
        Integer fetchData = 16;//
        //Integer fetchData = dao.get();
        int bit1 = fetchData & 1;// 按位与1,则获得最后一位bit值.
        int bit2 = (fetchData >> 1) & 1;//向右移一位,让原本第二位到最右,然后与1,获得原本第二位的值.
        int bit3 = (fetchData >> 2) & 1;//向右移二位,让原本第三位到最右,然后与1,获得原本第三位的值.
        int bit4 = (fetchData >> 3) & 1;//向右移三位,让原本第四位到最右,然后与1,获得原本第四位的值.
        int bit5 = (fetchData >> 4) & 1;//向右移四位,让原本第五位到最右,然后与1,获得原本第五位的值.

        System.out.println("" + bit5 + bit4 + bit3 + bit2 + bit1);

        /*
         * 改
         */
        String futureData = "" + bit5 + bit4 + bit3 + bit2 + bit1;
        // dao.modify(futureData);

    }

    @Test
    public void testInteger() {
        String a = null;
        System.out.println(Integer.parseInt(a));
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
     * 测试long除法，取整型结果的情况
     */
    @Test
    public void testLongDivLong() {
        long a = 7531667456L, b = 52567826432L;//a10位 b11位
        System.out.println(a / b);// 0
        System.out.println(((int) (a / b)));// 0

        System.out.println(b / a);// 6
        System.out.println(((int) (b / a)));// 6

        //long 除 long 结果转为整型，则为0或者正整数
    }

    /**
     * 测试long除法，取小数结果的情况
     */
    @Test
    public void testLongDivLong2() {
        long a = 7531667456L, b = 52567826432L;//a10位 b11位
        float resut1 = a / b;
        float resut2 = b / a;

        System.out.println(resut1);
        System.out.println(resut2);

        //long 除 long 结果转为float型，只是再整数结果的基础上加了个 .0

    }

    /**
     * 测试long除法，取小数结果的情况
     */
    @Test
    public void testLongDivLong3() {
        long a = 7531667456L, b = 52567826432L;//a10位 b11位
        float resut1 = (float) a / b;
        float resut2 = (float) b / a;

        System.out.println(resut1);//0.14327523
        System.out.println(resut2);//6.9795732

        //long 除 long , 先将被除数转float再除，得到了很长的小数
    }

    /**
     * 测试long型 除法 下，四舍五入情况
     */
    @Test
    public void testLongDivision() {
        long a = 7531667456L, b = 52567826432L;//a10位 b11位

        //转成float再除
        int result = (int) (((float) a / (float) b) * 100);
        System.out.println(result);


        long duration1 = 20999L;
        long duration2 = 5000L;

        float times = duration1 / duration2;
        int timesInt = (int) (duration1 / duration2);


        int timesAfterCeil = (int) Math.ceil(times);

        System.out.println(times);
        System.out.println(timesInt);
        System.out.println(timesAfterCeil);

        //
        long nodeActualDuration = 29966L;
        int c = 15;
        long nodeRemainIdleDuration = nodeActualDuration / 1000 - c;
        System.out.println(Math.round(nodeActualDuration / 1000F));
        System.out.println(nodeRemainIdleDuration);
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

    /**
     * 测试float小数上下取整
     */
    @Test
    public void testGetIntegerOfFloat() {
        float a = 0.234f;
        float b = 5.819f;

        //round 是四舍五入
        System.out.println(Math.round(a));
        System.out.println(Math.round(b));

        //floor下取整
        System.out.println(Math.floor(a));
        System.out.println(Math.floor(b));

        //转出的int也是下取整
        System.out.println((int) a);
        System.out.println((int) b);

        //上取整
        System.out.println(Math.ceil(a));
        System.out.println(Math.ceil(b));

    }
}

package com.liuyihui.common.commonTest.JavaDataType;

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
        int bit1 = fetchData & 1;// 按位与1,则获得最后一位值.
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
}

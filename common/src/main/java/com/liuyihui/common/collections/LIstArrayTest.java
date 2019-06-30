package com.liuyihui.common.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIstArrayTest {

    @Test
    public void testListArray() {
        //创建list
        List<Long> positionIds = new ArrayList<>();
        positionIds.add(3234L);
        positionIds.add(1L);
        positionIds.add(78L);

        //list.toString 实验结果:[3234, 1, 78] 带方括号
        System.out.println(positionIds);
        //数组.toString 实验结果:对象地址
        System.out.println(positionIds.toArray().toString());
        //Arrays.toString工具类 实验结果:[3234,1,78]   带方括号
        System.out.println(Arrays.toString(positionIds.toArray()));

        //替换掉中括号,空格的方法 实验结果:成功替换左右中括号和空格
        System.out.println(Arrays.toString(positionIds.toArray())
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", "")
        );
    }
}

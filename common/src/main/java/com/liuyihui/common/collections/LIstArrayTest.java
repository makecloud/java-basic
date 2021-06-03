package com.liuyihui.common.collections;

import org.junit.Test;
import sun.jvmstat.perfdata.monitor.AliasFileParser;

import java.nio.file.Watchable;
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

    private void pramPass(final List<String> param) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(param);

            }
        }).start();
    }

    @Test
    public void testParamPass() {

        List<String> oneList = new ArrayList<>();
        oneList.add("fdsf");
        oneList.add("klj");
        oneList.add("134s0");

        pramPass(oneList);

        oneList.clear();


        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试两个list怎样才能包含
     */
    @Test
    public void testListContains() {
        List<BannerEntity> aList = new ArrayList<>();
        List<BannerEntity> bList = new ArrayList<>();


        aList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));
        aList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));
        aList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));

//        bList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));
//        bList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));
//        bList.add(new BannerEntity(1L, (short) 2, "fjdkaf322", 234));


        boolean equal = aList.containsAll(bList);
        System.out.println(equal);
        //结果；重写实体对象的equal，每个对象equal了，list就contains了
        
    }
}

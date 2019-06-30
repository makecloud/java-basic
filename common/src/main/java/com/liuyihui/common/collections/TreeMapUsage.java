package com.liuyihui.common.collections;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.*;

/**
 * TreeMap用法
 */
public class TreeMapUsage {
    public static void main(String[] args) {
        testTreeMapUsage();
        treeMapOther();
    }

    /**
     * treeMap排序原理
     */
    public static TreeMap testTreeMapUsage() {
        //创建带比较器的TreeMap. 在填入数据时,按比较器规则,将数据排序
        TreeMap<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if (t1 > t2) {
                    return 1;
                } else if (t1.intValue() == t2.intValue()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        //填入
        treeMap.put(1, "a");
        treeMap.put(3, "a");
        treeMap.put(34, "a");
        treeMap.put(7, "a");
        treeMap.put(12, "a");

        //打印结果
        System.out.println(treeMap.toString());
        // 实验结果: {1=a, 3=a, 7=a, 12=a, 34=a}
        return treeMap;

    }

    public static void treeMapOther() {
        TreeMap<Integer,String> treeMap = testTreeMapUsage();
        Collection<String> values = treeMap.values();
        System.out.println(treeMap.values());

        System.out.println(treeMap.values().toString()
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", ""));
    }
}

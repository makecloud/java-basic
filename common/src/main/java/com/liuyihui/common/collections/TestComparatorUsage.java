package com.liuyihui.common.collections;

import java.util.Comparator;

/**
 * java中Comparator用法
 *
 * @author liuyi
 */
public class TestComparatorUsage {

    public static void main(String[] args) {
        comparatorUsage();
    }

    public static void comparatorUsage() {

        //这是按小到大排序规则
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if (t1 > t2) {
                    //前者大于后者返回正数. 返回正数代表参数t1要排在t2后面
                    return 1;
                } else if (t1.intValue() == t2.intValue()) {
                    //相等返回0
                    return 0;
                } else {
                    //前者小于后者返回负数. 返回负数代表参数t1要排在t2前面
                    return -1;
                }
            }
        };

        //将 return 1; 和 return -1; 对调,既可实现从大到小排序规则.
    }
}

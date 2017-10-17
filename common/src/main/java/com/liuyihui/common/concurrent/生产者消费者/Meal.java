package com.liuyihui.common.concurrent.生产者消费者;


/**
 * 食物
 */
public class Meal {
    /** 食物的序列号 */
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

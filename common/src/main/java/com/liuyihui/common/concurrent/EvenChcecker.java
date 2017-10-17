package com.liuyihui.common.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费者<br>
 * 整数检查器
 */
public class EvenChcecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    /**
     * 构造方法
     *
     * @param generator
     * @param id
     */
    public EvenChcecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }


    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.nextEven();
            if (val % 2 != 0) {
                System.out.println(val + " is not even!");
                generator.cancel();
            }
        }
    }


}

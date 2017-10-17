package com.liuyihui.common.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *测试偶数生成器
 */
public class TestEven {
    public static void main(String[] args) {
        System.out.println("press control-c to exit...");
        IntGenerator intGenerator = new EvenGenerator();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new EvenChcecker(intGenerator, 10));
        }
        executorService.shutdown();
    }
}

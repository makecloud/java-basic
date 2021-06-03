package com.liuyihui.common.concurrent.daemondemo;

import org.junit.Test;

/**
 * 测试守护线程是怎么个意思
 */
public class DaemonDemo {
    public static void main(String[] args) {
        new DaemonDemo().testDaemonFeature();
    }

    /**
     * daemon thread 的作用
     */
    @Test
    public void testDaemonFeature() {
        //daemon feature demo
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("this is  testThread out put.");
                }
            }
        });
        testThread.setDaemon(false);//if is not daemon, will continue running even main thread was finished;
//        thread.setDaemon(true);//if is daemon, will finish follow main thread finish;
        //注意：这些特性用junit执行体现不了，junit执行完会强制停止任何线程。只能运行main方法看到特征

        testThread.start();
    }
}

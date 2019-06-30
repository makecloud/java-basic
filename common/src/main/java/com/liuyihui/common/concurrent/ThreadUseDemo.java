package com.liuyihui.common.concurrent;

import org.junit.Test;

/**
 * java中以原生代码形式使用线程
 */
public class ThreadUseDemo {
    public static void main(String[] args) {


    }

    /**
     * daemon thread feature
     */
    @Test
    public void daemonThreadFeature() {
        //daemon feature demo
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("this is daemon thread out put.");
                }
            }
        });
        thread.setDaemon(false);//if is not daemon, will continue running even main thread was finished;
//        thread.setDaemon(true);//if is daemon, will finish follow main thread finish;
        thread.start();
    }
}

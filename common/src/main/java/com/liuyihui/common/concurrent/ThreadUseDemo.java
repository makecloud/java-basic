package com.liuyihui.common.concurrent;

import org.junit.Test;

/**
 * java中以原生代码形式使用线程
 */
public class ThreadUseDemo {
    public static void main(String[] args) {


//        new ThreadUseDemo().testDaemonFeature();
        new ThreadUseDemo().testAlive();

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

    /**
     * 测试线程的alive状态何时改变
     */
    @Test
    public void testAlive() {
        String testStr = null;
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA in run alive:" + Thread.currentThread().isAlive());
                System.out.println("dfjs");
                System.out.println("threadA in run alive:" + Thread.currentThread().isAlive());
                System.out.println(testStr.length());
                System.out.println("after thrown an excepiton");
            }
        });
        threadA.start();

        System.out.println("threadA isAlive:" + threadA.isAlive());

        System.out.println("mainThread isAlive:" + Thread.currentThread().isAlive());

        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after 3s ");
            System.out.println("mainThread isAlive:" + Thread.currentThread().isAlive());
            System.out.println("threadA isAlive:" + threadA.isAlive());
            /*
            此时打印 false。说线程的run执行完毕后，就死了，alive为false
             */


            //重新启动死了的线程，抛异常 IllegalThreadStateException。死了的线程只能重新new线程对象启动。
            //threadA.start();
        }
    }
}

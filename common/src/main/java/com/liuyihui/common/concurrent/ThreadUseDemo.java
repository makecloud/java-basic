package com.liuyihui.common.concurrent;

import org.junit.Test;

/**
 * java中以原生代码形式使用线程
 */
public class ThreadUseDemo {
    public static void main(String[] args) {


    }

    /**
     * daemon thread 的作用
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

    /**
     * 测试线程的alive状态何时改变
     */
    @Test
    public void testAlive() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA alive:" + Thread.currentThread().isAlive());
                System.out.println("dfjs");
                System.out.println("threadA alive:" + Thread.currentThread().isAlive());
            }
        });
        threadA.start();

        System.out.println("threadA alive:" + threadA.isAlive());

        System.out.println("mainThread:" + Thread.currentThread().isAlive());
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after 3s ");
            System.out.println("mainThread:" + Thread.currentThread().isAlive());
            System.out.println("threadA alive:" + threadA.isAlive());
            /*
            此时打印 false。说线程的run执行完毕后，就死了，alive为false
             */


            //重新启动死了的线程，抛异常 IllegalThreadStateException
            threadA.start();
        }
    }
}

package com.liuyihui.common.concurrent.alivedemo;

import org.junit.Test;

/**
 * 测试线程 "活" 的demo
 */
public class ThreadAliveDemo {
    public static void main(String[] args) {
        new ThreadAliveDemo().testAlive();
    }

    /**
     * 测试线程的alive状态何时改变
     */
    private void testAlive() {
        String testStr = null;
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA in run. alive:" + Thread.currentThread().isAlive());

                System.out.println("dfjs");

                System.out.println("threadA in run. alive:" + Thread.currentThread().isAlive());

                //抛个空指针异常. 结果线程不会再执行，并且 alive为false，线程死亡
                //System.out.println(testStr.length());


                System.out.println("after thrown an exception");

                //线程执行完后，isAlive为false，死亡。
            }
        });
        threadA.start();

        System.out.println("threadA isAlive:" + threadA.isAlive());//此处线程A刚启动正在执行，isAlive为true，线程活着呢。
        //主线程不退出一直在while循环，就一直是活的。
        System.out.println("mainThread isAlive:" + Thread.currentThread().isAlive());

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("after 3s... mainThread isAlive:%s. threadA isAlive:%s.", Thread.currentThread().isAlive(), threadA.isAlive()));
            /*
            此时打印 false。说线程的run执行完毕后，就死了，alive为false
             */


            //重新启动死了的线程，抛异常 IllegalThreadStateException。死了的线程只能重新new线程对象启动。
            //threadA.start();
        }
    }
}

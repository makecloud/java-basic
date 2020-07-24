package com.liuyihui.common.concurrent.interruptdemo;

import org.junit.Test;

/**
 * 测试interrupt作用
 */
public class InterruptDemo {


    @Test
    public void test() throws InterruptedException {
        int i = 0;
        while (true) {
            System.out.println(i++);
            if (i == 5) {
                Thread.currentThread().interrupt();
                //如果写try catch， 被interrupt时，则走一次catch，然后继续循环，相当于continue
                //不写try catch，在方法名将异常抛出，此线程停止
                //这只是sleep和wait期间被interrupt，在之外的代码执行期间也一样被interrupt发生在try内肯定相当于continue，线程继续走。
            }

            synchronized (this) {
                this.wait(1000);
            }
            System.out.println("go on\n");

        }
    }


    @Test
    public void test2() {
        Thread a;
        a = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("a begin sleep 3s...");
                        System.out.println("isInterrupted:" + Thread.currentThread().isInterrupted());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("isInterrupted:" + Thread.currentThread().isInterrupted());
                        //看打印结果，这里isInterrupted还是false
                        //猜测：线程在sleep、await等等待中被调用interrupt，只会抛个异常，而不会不会设置interrupt标识，isInterrupt
                        // 方法也仍为false。 只在某些IO操作，和正常的计算中被调用interrupt，才会设置interrupt标识
                    }

                    //测试线程有没有终止，还是继续执行到这
                    System.out.println("jfkdn");
                }
            }
        });


        a.start();

        try {
            Thread.sleep(5000);
            a.interrupt();

            System.out.println("isInterrupted:" + a.isInterrupted());

//            a.join(0);//让a线程一直等待?
            //a.join();//同上传0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //main wait to look
        synchronized (this) {
            try {
                System.out.println("wait...");
                wait(100 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

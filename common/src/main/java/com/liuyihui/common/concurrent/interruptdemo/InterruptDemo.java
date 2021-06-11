package com.liuyihui.common.concurrent.interruptdemo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试interrupt
 */
public class InterruptDemo {
    Thread testThread = new Thread() {
        @Override
        public void run() {

        }
    };


    @Test
    public void test() throws InterruptedException {
        int i = 0;
        while (true) {
            System.out.println(i++);

            //在第5秒，执行interrupt, 线程自己调自己interrupt
            if (i == 5) {
                Thread.currentThread().interrupt();
                //如果写try catch， 被interrupt时，则走一次catch，然后继续循环，相当于continue
                //不写try catch，在方法名将异常抛出，此线程停止

                // 上述 只是sleep和wait阻塞期间被interrupt会抛异常，你用try catch捕获了就过一下catch然后继续走。当我把这个异常抛到test方法外面，线程停了

                // try内sleep之后写了其他逻辑，则正在sleep的时候被interrupt，直接从sleep位置跳过其他逻辑进入catch！

                // 其他计算型逻辑代码被interrupt是不会抛interrupt异常的，也不会直接让线程停止，只会设置线程的interrupt标志。

            }

            synchronized (this) {
                this.wait(1000);
            }
            System.out.println("go on\n");

        }
    }


    @Test
    public void test2() {
        Thread threadA = new Thread() {
            ReentrantLock lock = new ReentrantLock();
            Condition condition = lock.newCondition();

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    System.out.println("while begin");
                    for (int i = 0; i < 1000000000; i++) {//interrupt 不会打断线程？只是设置一个标志？
                        int b = (int) (i / 3.84938294);
                        double cosR = Math.sqrt(Math.acos(b));
                    }

                    try {
                        System.out.println("threadA begin sleep 3s... isInterrupted:" + isInterrupted());
                        //Thread.sleep(3000);
                        condition.await(3000, TimeUnit.MILLISECONDS);//和sleep一样的规则, 只不过需要额外控制锁

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("in InterruptedException catch clause, has always cleared the interrupt status. isInterrupted:" + isInterrupted());
                        //打印结果：被interrupt然后抛异常走到这里 查看isInterrupted还是false
                        //线程在sleep、await等等待中被调用interrupt或者发现设置了interrupt标志，会抛个异常，然后将interrupt标识清除掉（置为false）。
                        // 在某些IO操作，和正常的计算中被调用interrupt，会仅设置一下interrupt标识为true

                        // 看sleep()方法文档：

                        //IllegalArgumentException – if the value of millis is negative
                        //InterruptedException – if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.

                        //也说明了，如果这个异常抛出，将会把线程的interrupt状态删除掉。
                        //意思就是,在被打断后还没抛异常的时候,你去查询线程的interrupt状态才是true的，一旦抛了异常就清楚标志了
                    }

                    //测试线程有没有终止，还是继续执行到这
                    System.out.println("run to thread end");
                    lock.unlock();
                }
            }
        };


        threadA.start();

        try {
            Thread.sleep(2000);
            System.out.println("threadA isInterrupted? :" + threadA.isInterrupted());

            threadA.interrupt();

            System.out.println("threadA isInterrupted? :" + threadA.isInterrupted());

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

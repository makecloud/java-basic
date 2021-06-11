package com.liuyihui.common.concurrent.interruptdemo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 Condition 概念、作用分析理解
 */
public class InterruptAFromB {
    private final ReentrantLock lock1 = new ReentrantLock();

    // 取代在object上wait
    // condition 可以由一个lock对象创建多个，
    // 可以实现不同的线程wait在不同的condition上。
    // 从而可以只执行某个condition的signal，达到只唤醒等待在这个condition上的若干线程
    private final Condition available = lock1.newCondition();


    public static void main(String[] args) {
        //fixme 学习点：多个线程同时拿一个lock进入执行的时候，拿不到的会怎样?

    }

    private ThreadA threadA = new ThreadA();
    private ThreadB threadB = new ThreadB();

    @Test
    public void testTowThread() {


        threadA.start();


        //wait 1s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        threadB.start();


        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     *
     */
    private class ThreadA extends Thread {
        @Override
        public void run() {

            try {
                lock1.lock();
                System.out.println("ThreadA start await 5s");

                //await 阻塞并释放了所有锁
                available.await(5, TimeUnit.SECONDS);

                //在await期间被interrupt但是获取不到锁,回不来,会等获取到锁回来后抛interrupt异常

                //在await过时后因获取不到锁,回不来的期间被interrupt但是获取不到锁,回不来,会等获取到锁回来后抛interrupt异常

                System.out.println("ThreadA end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }


        }
    }

    /**
     * 获取锁的状态下 interrupt threadA
     */
    private class ThreadB extends Thread {
        int i = 0;

        @Override
        public void run() {
            lock1.lock();
            System.out.println("ThreadB lock the lock1");

            //sleep 不会释放锁
            while (i < 10) {
                try {
                    if (i == 8) {
                        System.out.println("interrupt threadA in threadB");
                        threadA.interrupt();
                    }
                    System.out.println("ThreadB sleep 1s");
                    Thread.sleep(1 * 1000);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lock1.unlock();
            System.out.println("ThreadB unlock lock1");

        }
    }
}

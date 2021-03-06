package com.liuyihui.common.concurrent.lockdemo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 await释放锁后，到时间了，获不回锁会怎样
 * <p>
 * 结论：await继续阻塞直到能获取到锁再继续执行
 */
public class LockDemo {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final Condition available = lock1.newCondition();


    public static void main(String[] args) {
        //fixme 学习点：多个线程同时拿一个lock进入执行的时候，拿不到的会怎样?

    }

    /**
     * 测试 await释放锁后，到时间了，获不回锁会怎样
     * <p>
     * 结论：await继续阻塞直到能获取到锁再继续执行
     */
    @Test
    public void testTowThread() {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();


        //wait 1s
        try {
            Thread.sleep(1000);
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


    private class ThreadA extends Thread {
        @Override
        public void run() {

            try {

                lock1.lock();
                System.out.println("ThreadA start await");

                //await 阻塞并释放了所有锁
                available.await(5, TimeUnit.SECONDS);

                System.out.println("ThreadA end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }


        }
    }

    private class ThreadB extends Thread {
        @Override
        public void run() {
            lock1.lock();
            System.out.println("ThreadB lock the lock1");

            //sleep 不会释放锁 lock1, 所以ThreadA await到时后也不会恢复执行
            try {
                System.out.println("ThreadB lock the lock1");
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock1.unlock();
            System.out.println("ThreadB unlock lock1");

        }
    }
}

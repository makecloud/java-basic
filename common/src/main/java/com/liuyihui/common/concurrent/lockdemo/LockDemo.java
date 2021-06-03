package com.liuyihui.common.concurrent.lockdemo;

import org.junit.Test;
import sun.awt.AWTAccessor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 Condition 概念、作用分析理解
 */
public class LockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    // 取代在object上wait
    // condition 可以由一个lock对象创建多个，
    // 可以实现不同的线程wait在不同的condition上。
    // 从而可以只执行某个condition的signal，达到只唤醒等待在这个condition上的若干线程
    private final Condition available = lock.newCondition();


    public static void main(String[] args) {
        //fixme 学习点：多个线程同时拿一个lock进入执行的时候，拿不到的会怎样?

    }

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


    class ThreadA extends Thread {
        @Override
        public void run() {

            try {
                lock.lock();
                System.out.println("ThreadA start await");


                new ThreadB().start();

                available.await(5, TimeUnit.SECONDS);
//                Thread.currentThread().sleep(5000);

                System.out.println("ThreadA end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }
    }

    class ThreadB extends Thread {

        @Override
        public void run() {
            lock.lock();
            System.out.println("ThreadB locked");
            lock.unlock();
            System.out.println("ThreadB unlock");

        }
    }
}

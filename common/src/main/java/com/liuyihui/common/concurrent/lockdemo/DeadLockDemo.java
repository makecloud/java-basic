package com.liuyihui.common.concurrent.lockdemo;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();


    public static void main(String[] args) {
        //fixme 学习点：多个线程同时拿一个lock进入执行的时候，拿不到的会怎样?

    }

    @Test
    public void testTowThread() {
        ThreadA threadA = new ThreadA();

        threadA.start();


        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 交叉锁，两个线程双方互等对方的锁。 建立在有2个锁的情况下
     */
    class ThreadA extends Thread {
        @Override
        public void run() {

            try {
                lock1.lock();
                System.out.println("ThreadA get Lock1");


                new ThreadB().start();

                //耗时 以在释放锁之前让 线程B 获取 lock2
                for (int i = 0; i < 10000000; i++) {
                    int b = (int) (i / 3.84938294);
                    double cosR = Math.acos(b);
                }

                //获取锁2
                System.out.println("ThreadA get lock2");
                lock2.lock();


                lock2.unlock();
                System.out.println("ThreadA unlock2");


                System.out.println("ThreadA unlock1");
            } finally {
                lock1.unlock();
            }


        }
    }

    class ThreadB extends Thread {

        @Override
        public void run() {

            System.out.println("ThreadB get lock2");
            lock2.lock();

            lock1.lock();
            System.out.println("ThreadB get lock1");


            lock1.unlock();
            System.out.println("ThreadB unlock1");


            lock2.unlock();
            System.out.println("ThreadB unlock2");

        }
    }
}

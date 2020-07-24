package com.liuyihui.common.concurrent.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 Condition 概念、作用分析理解
 */
public class LockDemo extends Thread {
    private final ReentrantLock mainLock = new ReentrantLock();

    // 取代在object上wait
    // condition 可以由一个lock对象创建多个，
    // 可以实现不同的线程wait在不同的condition上。
    // 从而可以只执行某个condition的signal，达到只唤醒等待在这个condition上的若干线程
    private final Condition available = mainLock.newCondition();


    @Override
    public void run() {

        mainLock.lock();




        available.signalAll();

    }
}

package com.liuyihui.common.concurrent.useExecutorService;

import java.util.concurrent.*;

/**
 * @author liuyi 2017年11月29日00:08:57
 */
public class UseExecutorService {

    /**
     * java 线程池的基本使用
     */
    public static void useExecutorServiceBasically() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        //是推荐使用自定义线程工厂, 但是不知道怎么自定义工厂了? --
        ThreadFactory threadFactory;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 2,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));

        MyRunner thread1 = new MyRunner();
        MyRunner thread2 = new MyRunner();
        MyRunner thread3 = new MyRunner();
        MyRunner thread4 = new MyRunner();
        MyRunner thread5 = new MyRunner();
        MyRunner thread6 = new MyRunner();
        MyRunner thread7 = new MyRunner();

        poolExecutor.execute(thread1);
        poolExecutor.execute(thread2);
        poolExecutor.execute(thread3);
        poolExecutor.execute(thread4);
        poolExecutor.execute(thread5);
        poolExecutor.execute(thread6);
        poolExecutor.execute(thread7);

        System.out.println("task count: " + poolExecutor.getTaskCount());
        System.out.println("completed task count: " + poolExecutor.getCompletedTaskCount());
        System.out.println("isShutdown: " + poolExecutor.isShutdown());
        System.out.println("isTerminating: " + poolExecutor.isTerminating());
        System.out.println("isTerminated: " + poolExecutor.isTerminated());

        //1.不调shutdown, 线程不会到达terminated 2.调shutdown后再提交任务抛reject(拒绝)异常
        // 3.调shutdown不会停止正在exe的任务而调shutdownNow会.
        poolExecutor.shutdown();
        System.out.println("shutdown !");

        /*
        try {
            poolExecutor.awaitTermination(3, TimeUnit.SECONDS);//等待x时间后,终结了所有线程 -_-
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        while (!poolExecutor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("task count: " + poolExecutor.getTaskCount());
        System.out.println("completed task count: " + poolExecutor.getCompletedTaskCount());
        System.out.println("isShutdown: " + poolExecutor.isShutdown());
        System.out.println("isTerminating: " + poolExecutor.isTerminating());
        System.out.println("isTerminated: " + poolExecutor.isTerminated());

    }
}

class MyRunner implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run.");
    }
}

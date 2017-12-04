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

        //是推荐使用自定义线程工厂,可以自定义线程名,线程是否守护, 但是不知道怎么自定义工厂了? --
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
        poolExecutor.execute(thread4);//不受任务抛异常,随时继续提交任务
        poolExecutor.execute(thread5);
        poolExecutor.execute(thread6);
        poolExecutor.execute(thread7);

        System.out.println("task count: " + poolExecutor.getTaskCount());
        System.out.println("completed task count: " + poolExecutor.getCompletedTaskCount());
        System.out.println("isShutdown: " + poolExecutor.isShutdown());
        System.out.println("isTerminating: " + poolExecutor.isTerminating());
        System.out.println("isTerminated: " + poolExecutor.isTerminated());

        // 1.不调shutdown, 线程不会到达terminated
        // 2.调shutdown后再提交任务抛reject(拒绝)异常
        // 3.调shutdown不会停止正在exe的任务而调shutdownNow会.
        // 4.如果线程池中的线程是守护线程,调用主线程退出了,线程池中正在执行的线程也会终结
        poolExecutor.shutdown();
        System.out.println("call shutdown !");

        /*
        try {
            poolExecutor.awaitTermination(3, TimeUnit.SECONDS);//会导致等待x时间后
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {

            while (!poolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("false,没执行完");
            }
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("submit task count: " + poolExecutor.getTaskCount());
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
            Thread.sleep(1000);//等一秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run.");
    }
}

package com.liuyihui.common.concurrent.useExecutorService;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author liuyi 2017年11月29日00:08:57
 */
public class UseExecutorService {

    /**
     * java 线程池的基本使用
     */
    @Test
    public void useExecutorServiceBasically() {

        //阿里推荐使用自定义线程工厂,可以自定义线程名,线程是否守护, 但是不知道怎么自定义工厂了? --
        ThreadFactory threadFactory;

        //create executor
        ExecutorService poolExecutor1 = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50));

        //create task
        MyRunner task1 = new MyRunner("task1");
        MyRunner task2 = new MyRunner("task2");
        MyRunner task3 = new MyRunner("task3");
        MyRunner task4 = new MyRunner("task4");
        MyRunner task5 = new MyRunner("task5");
        MyRunner task6 = new MyRunner("task6");
        MyRunner task7 = new MyRunner("task7");

        //push task in
        poolExecutor.execute(task1);
        poolExecutor.execute(task2);
        poolExecutor.execute(task3);
        poolExecutor.execute(task4);//不受任务抛异常影响,随时继续提交任务
        poolExecutor.execute(task5);
        poolExecutor.execute(task6);
        poolExecutor.execute(task7);


        System.out.println("task count: " + poolExecutor.getTaskCount());
        System.out.println("completed task count: " + poolExecutor.getCompletedTaskCount());
        System.out.println("isShutdown: " + poolExecutor.isShutdown());
        System.out.println("isTerminating: " + poolExecutor.isTerminating());
        System.out.println("isTerminated: " + poolExecutor.isTerminated());


        //do close pool
        //poolExecutor.shutdown();
        //System.out.println("call shutdown !");

        // 1.不调shutdown, 线程不会到达terminated
        // 2.调shutdown后再提交任务抛reject(拒绝)异常
        // 3.调shutdown不会停止正在exe的任务而调shutdownNow会.
        // 4.如果线程池中的线程是守护线程,调用主线程退出了,线程池中正在执行的线程也会终结

        /*
        try {
            poolExecutor.awaitTermination(3, TimeUnit.SECONDS);//会导致等待x时间后
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //cycle checking the pool
        try {
            while (!poolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("executor未执行完提交的task");
            }
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //after view
        System.out.println("submit task count: " + poolExecutor.getTaskCount());
        System.out.println("completed task count: " + poolExecutor.getCompletedTaskCount());
        System.out.println("isShutdown: " + poolExecutor.isShutdown());
        System.out.println("isTerminating: " + poolExecutor.isTerminating());
        System.out.println("isTerminated: " + poolExecutor.isTerminated());

    }
}

class MyRunner implements Runnable {
    private String taskName;

    public MyRunner(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(String.format("task:%s useThread:%s start.", taskName, Thread.currentThread().getName()));
        try {
            Thread.sleep(5000);//等x秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.liuyihui.common.reactivexdemo;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;


public class FirstDemo {

    public void hello(String... names) {

        //rxjava 创建一个被观察者. 被观察者拥有的事件(数据)在此时固定死了.
        Observable<String> observable = Observable.from(names);

        //添加一个观察者
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hello " + s);
            }
        });
        //添加一个观察者
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hi " + s);
            }
        });
        //添加一个观察者
        observable.subscribe(new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("called observer's onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("called observer's onError");

            }

            @Override
            public void onNext(Object o) {
                System.out.println("called observer's onNext");

            }
        });

        //添加一个观察者
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("called subscriber's onNext " + s);
            }
        });


    }

    public void hello1() {

        //创建一个被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe ");
                subscriber.onNext("");
            }
        });
        //添加动作
        observable = observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnSubscribe");
            }
        });
        //添加动作
        observable = observable.doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("doOnNext1");
            }
        });
        //添加动作
        observable = observable.doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("doOnNext2");
            }
        });

        //加入一个监听者
        observable.subscribe(new Action1<String>() {//在调用subscribe的时候,执行OnSubscribe周期, onNext周期
            @Override
            public void call(String s) {
                System.out.println("Action1's call" + s);
            }
        });
        //加入一个监听者
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber's onNext");
            }
        });

    }

    public void hello2() {
        //按间隔事件持续调用监听者.但是需要程序sleep或者wait住以不退出.不然看不到效果.
        Observable observable1 = Observable.interval(2, TimeUnit.SECONDS);
        observable1.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aaaa" + aLong);
            }
        });

        //主进程等待一段时间在停止
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试map
     */
    public void hello3() {
        Observable<Integer> observable = Observable.just(new Entity1()).map(new Func1<Entity1, Integer>() {
            @Override
            public Integer call(Entity1 entity1) {
                return null;
            }
        });

        Observable<Long> observable1 = Observable.just(new Long(2));
        observable1.map(new Func1<Long, Entity1>() {
            @Override
            public Entity1 call(Long aLong) {
                return null;
            }
        });
        Observable<Double> observable2 = observable1.map(new Func1<Long, Double>() {
            @Override
            public Double call(Long aLong) {
                return null;
            }
        });
    }


}

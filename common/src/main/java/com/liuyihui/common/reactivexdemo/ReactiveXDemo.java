package com.liuyihui.common.reactivexdemo;

import org.junit.Test;
import rx.*;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.functions.Func1;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import java.util.concurrent.TimeUnit;


/**
 * 使用RxJava 1 案例<br>
 * 实际项目中可按此标准运用
 */

public class ReactiveXDemo {

    /**
     * 使用from创建Observable<br>
     * 添加四个观察者测试<br>
     * 当执行一个subscribe动作时, 此时事件对象被发送给订阅者.<br>
     * 疑问:
     *
     * @param names
     */
    public void rxJavaObsrervableFrom(String... names) {

        //用from方式创建一个被观察者. 被观察者拥有的事件(数据)在此时固定死了.
        Observable<String> observable = Observable.from(names);

        //添加观察者1
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hello " + s);
            }
        });
        //添加观察者2
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hi " + s);
            }
        });
        //添加观察者3
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("called observer3's onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("called observer3's onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("called observer3's onNext");
            }

        });
        //添加观察者4
        Subscription subscription4 = observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("called subscriber4's onNext " + s);
            }
        });

        //?

    }

    /**
     * 用create创建被观察者<br>
     * 测试doOnSubscribe  <br>
     * 测试doOnNext<br>
     * 测试运用不同线程
     * <p>
     * 此时,我将这种方式类比于微信公众号:
     * Observable相当于一个微信公众号
     * 创建observable时传入OnSubscribe对象,相当于对微信公众号设置订阅(关注)响应.例:我一关注A公众号,A公众号立马给我发来一句话"欢迎订阅".
     * OnSubScribe的call方法逻辑就相当于: 公众号被订阅时立即发欢迎消息.
     */
    @Test
    public void rxJavaCreateUsage() {

        //用create方法创建一个被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe.call" + ". run on threadName:" + Thread.currentThread().getName());
                //使onSubscribe的线程等待
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("ttt");//这里可以触发去另一个线程执行observer的逻辑
            }
        });

        //配置observable
        observable = observable.subscribeOn(Schedulers.newThread());//指定onSubscribe逻辑在..线程
        observable = observable.observeOn(Schedulers.io());//指定Observer(subscriber)的逻辑在..线程

        //添加doOnSubscribe动作,执行在onSubscribe call逻辑之前,doOnSubscribe动作执行在什么线程还未搞明白
        observable = observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnSubscribe" + ". run on threadName:" + Thread.currentThread().getName());
            }
        });


        //添加doOnNext动作1, 执行在observer逻辑之前
        //doOnNext的线程跟observeOn设置时机有关系：在设置doOnNext之前设置observeOn就在observeOn的线程,否则就在subscribeOn的
        observable = observable.doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("doOnNext1" + ". run on threadName:" + Thread.currentThread().getName());
            }
        });
        //添加doOnNext动作2
        observable = observable.doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("doOnNext2" + ". run on threadName:" + Thread.currentThread().getName());
            }
        });

        //订阅1
        observable.subscribe(new Action1<String>() {//在调用subscribe的时候,执行OnSubscribe周期, 调用ubscriber.onNext
            @Override
            public void call(String s) {
                System.out.println("Action1's call " + s + ". run on threadName:" + Thread.currentThread().getName());
            }
        });

        //订阅2
        /*observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber's onNext" + ". run on threadName:" + Thread.currentThread().getName());
            }
        });*/


        //主线程等待,以观察打印
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试运用observable按时间间隔发送时间
     */
    public void rxJavaObservableInterval() {
        //创建被观察者．按间隔事件持续调用监听者.但是需要程序sleep或者wait住以不退出.不然看不到效果.
        Observable observable1 = Observable.interval(2, TimeUnit.SECONDS);
        observable1.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aaaa" + aLong);
            }
        });


        //主进程等待一段时间,以测试
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试RxJava的 map概念及运用
     */
    public void rxJavaMap() {

        //usage 1.
        Observable<Integer> observable = Observable.just(new Entity1()).map(new Func1<Entity1, Integer>() {
            @Override
            public Integer call(Entity1 entity1) {
                return null;
            }
        });

        //usage 2.
        Observable<Long> observable1 = Observable.just(2L);
        Observable<Entity1> observableOfEntity1 = observable1.map(new Func1<Long, Entity1>() {
            @Override
            public Entity1 call(Long aLong) {
                return null;
            }
        });

        //usage 3.
        Observable<Double> observableOfDouble = observable1.map(new Func1<Long, Double>() {
            @Override
            public Double call(Long aLong) {
                return null;
            }
        });

        //就是经过map方法后, 可以将创建出来的Observable转换为另一种数据类型的. 所以map方法还算好理解

    }

    /**
     * 一种实际应用模式: 让普通方法用上Observable, 可以实现这个方法被执行在不同的线程<p>
     * 返回一个定义好OnSubscribe的Observable<br>
     * 这个observable的OnSubscribe 为作为此方法的逻辑
     *
     * @return
     */
    public Observable<String> methodUseObservable() {

        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                //执行本方法本该做的逻辑,比如请求网络接口获取数据.
                //产生数据后,可以将数据作为参数调用订阅者


                //调用订阅者
                subscriber.onNext("...");
            }


        });
    }

    /**
     * 摸索使用emitter的作用
     * todo
     */
    public void useEmitter() {
        //创建emitter
        final Emitter<String> emitter = new Emitter<String>() {
            @Override
            public void setSubscription(Subscription subscription) {

            }

            @Override
            public void setCancellation(Cancellable cancellable) {

            }

            @Override
            public long requested() {
                return 0;
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        //创建SubScription
        final Subscription subscription = Observable.create(new Action1<Emitter<String>>() {
            @Override
            public void call(Emitter<String> emitter) {
                System.out.println("Action1.call");
                emitter.requested();
                emitter.setSubscription(null);
            }
        }, Emitter.BackpressureMode.DROP)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("subscriber.onNext");
                    }
                });

        //创建Observable
        Observable.create(new Action1<Emitter<Object>>() {
            @Override
            public void call(Emitter<Object> objectEmitter) {
                System.out.println("anction1.call");
                emitter.setSubscription(subscription);
                emitter.requested();
            }
        }, Emitter.BackpressureMode.DROP).subscribe();

        //
    }

    /**
     * 摸索使用AsyncOnSubscribe的作用
     */
    public void useAsyncOnSubscribe() {
        Observable.create(new AsyncOnSubscribe<Object, Object>() {
            @Override
            protected Object generateState() {
                System.out.println("AsyncOnSubscribe.generateState");
                return null;
            }

            @Override
            protected Object next(Object o, long l, Observer<Observable<?>> observer) {
                System.out.println("AsyncOnSubscribe.next");
                System.out.println(Thread.currentThread().getName());
                return null;
            }

        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber.oncompleted");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("subscriber.onNext");
            }
        });
    }

    /**
     * 使用Subject类
     * Subject类即是订阅者也是被观察者.
     * <p>
     * 用subject可以随时随地的发射事件,即对订阅者onNext的调用
     * <p>
     * 这才是纯粹的事件驱动
     */
    public void useSubject() {
        Subject subject = new SerializedSubject(PublishSubject.create());

        //添加订阅者
        subject.observeOn(Schedulers.newThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o + "==:" + Thread.currentThread().getName());
                    }

                });


        //这里产生事件,发送给订阅者,订阅者可以在任何线程消费此事件
        subject.onNext("ssss");

    }

    @Test
    public void timer() {
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aa");
                    }
                });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void useConcat() {
        Observable<String> obs1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("obs1");
                subscriber.onError(new Throwable("obs1 err"));
//                subscriber.onNext("oba11");
            }
        });
        Observable<String> obs2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("obs2");
            }
        });
        Observable<String> obs3 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("obs3");
            }
        });

        Observable.concatDelayError(obs1, obs2, obs3)
                .first()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError:" + throwable.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext:" + s);
                    }
                });
    }

}

package com.liuyihui.common.rxjava2demo;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * RxJava 2 Demo
 */
public class RxJava2Demo {
    public static void main(String[] args) {
        Flowable.just("hello world").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    /**
     * 测试基础创建
     */
    @Test
    public void observableCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                System.out.println("djksf");
                observableEmitter.onNext(2);
            }
        })
                .subscribe();

        Observable.create(emitter -> {
            System.out.println("al");
            System.out.println("d");
        });
    }

    /**
     * 测试flatMap操作符
     */
    public void useFlatMap() {
        Flowable.just("sss")
                .flatMap(s ->
                        Flowable.just(s).map(new Function<String, Object>() {
                            @Override
                            public Object apply(String s) throws Exception {
                                return null;
                            }
                        })
                );
    }

    /**
     * 测试concat操作符
     */
    @Test
    public void useConcat() {
        Observable<String> obs1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("s1");
                observableEmitter.onNext("s11");
                observableEmitter.onError(new Throwable("s1 err"));
            }
        });

        Observable<String> obs2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("s2");
            }
        });

        Observable<String> obs3 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("s3");
            }
        });

        Observable.concatArrayDelayError(obs1, obs2, obs3)
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   System.out.println(s);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.out.println(throwable.getMessage());
                            }
                        }
                );
    }

    /**
     * 测试onSub回调
     */
    @Test
    public void testOnSubscribeCallBack() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
                System.out.println("executing onSub");
                observableEmitter.onNext("on next");
                System.out.println("executing onSub2");
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("callback onSubscribe");
                //disposable.dispose();
                //System.out.println("call dispose in onSubscribe");
                //测试结果显示，有写subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())时，调用了dispose，就不会执行订阅了；
                //没有写，则执行订阅，但不回调onNext了
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

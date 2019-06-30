package com.liuyihui.common.rxjava2demo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
}

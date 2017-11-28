package com.liuyihui.common.reactivexdemotest;

import com.liuyihui.common.reactivexdemo.ReactiveXDemo;
import org.junit.Before;
import org.junit.Test;
import rx.functions.Action1;

public class ReactivexDemoTest {

    private ReactiveXDemo reactiveXDemo;

    @Before
    public void before(){
        reactiveXDemo=new ReactiveXDemo();
    }

    @Test
    public void testRxJavaObsrervableFrom() {
        reactiveXDemo.rxJavaObsrervableFrom("Tom", "Jack");
    }

    @Test
    public void testRxJavaBasic() {
        reactiveXDemo.rxJavaBasic();
    }

    @Test
    public void testRxJavaObservableInterval() {
        reactiveXDemo.rxJavaObservableInterval();
    }

    @Test
    public void testMethodUserObservable(){
        reactiveXDemo.methodUseObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("testMethodUserObservable");
            }
        });
    }

    @Test
    public void testuseEmitter(){
        reactiveXDemo.useEmitter();
    }

    @Test
    public void testuseAsyncOnSubscribe(){
        reactiveXDemo.useAsyncOnSubscribe();
    }

}

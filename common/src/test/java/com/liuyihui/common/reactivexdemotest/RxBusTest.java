package com.liuyihui.common.reactivexdemotest;

import com.liuyihui.common.reactivexdemo.RxBus;
import com.liuyihui.common.reactivexdemo.RxEvent;
import org.junit.Test;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RxBusTest {

    /**
     * 测试RxBus用法
     */
    @Test
    public void testUseRxBus() {

        //RxBus添加一位订阅者
        RxBus.getInstance()
                .toObservable(RxEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<RxEvent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(RxEvent event) {
                        System.out.println("在线程:" + Thread.currentThread().getName() + "上,消费事件:" + event);
                        //可判断event类型, 识别为不同的事件
                    }
                });


        //在子线程中用RxBus发送一个事件
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RxEvent event1 = new RxEvent(2, "event");//由参数来区分不同类型的事件
                RxBus.getInstance().sendEvent(event1);//发送事件代码
                System.out.println("在线程:" + Thread.currentThread().getName() + "上,发送事件");
            }
        });
        threadA.setDaemon(true);
        threadA.start();


        //主线程停留6秒
        synchronized (this) {
            try {
                this.wait(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

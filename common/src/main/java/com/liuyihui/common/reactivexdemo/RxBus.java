package com.liuyihui.common.reactivexdemo;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxBus实现
 * <p>
 * 使用subject,既是发送者,又是订阅者
 */
public class RxBus {

    /** 主题 */
    private Subject<Object, Object> subject;


    /**
     * 构造方法
     */
    private RxBus() {
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static RxBus getInstance() {
        return InstanceHolder.RX_BUS_DEMO;
    }

    static class InstanceHolder {
        private static final RxBus RX_BUS_DEMO = new RxBus();
    }


    /**
     * 发送event
     * <p>
     * 原理,主题是被订阅者,和订阅者的综合体, 调用主题的onNext,即调用了订阅者的onNext,event可为自定义为任意类型对象
     *
     * @param rxEvent
     */
    public void sendEvent(RxEvent rxEvent) {
        subject.onNext(rxEvent);
    }

    /**
     * 主题添加订阅者都是先拿到主题内的Observable对象,通过Observable.subscribe添加订阅者
     * <p>
     * 返回主题对象内含的被订阅者(被观察者)对象,以供操作被订阅者
     *
     * @param <T>    被订阅者类型参数,代表在观察者模式中要发送的数据对象类型
     * @param tClass 如果使用RxEvent,则传其class;如果要发送其他对象,则传其他对象的class,同时sendEvent方法需要改为发送其他对象
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return subject.ofType(tClass);
    }

}


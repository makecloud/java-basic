package com.liuyihui.common.事件回调;

/**
 * 被观察者在发生事件时调用次接口<br>
 * 真正观察者实现此接口.<br>
 * 真正观察者赋值给被观察者<br>
 * 达到被观察者通知观察者的目的
 */
public interface Listener<T> {
    void do1(T t);

    void do2(T t);
}

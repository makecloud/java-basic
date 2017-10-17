package com.liuyihui.common.事件回调;

/**
 * 观察者<br>
 * 继承于一个父类,父类中定义好动作方法. 被观察者在实现的时候调用父类的各个动作方法.
 */
public class Listener1<T> implements Listener<T> {


    @Override
    public void do1(Object o) {
        System.out.println(this + " # do1");
    }

    @Override
    public void do2(Object o) {
        System.out.println(this + " # do2");

    }
}

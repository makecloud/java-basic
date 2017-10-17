package com.liuyihui.common.事件回调;

public class TestMain {
    public static void main(String[] args) {
        //被观察者
        EventLauncher eventLauncher = new EventLauncher();
        //两个观察者
        Listener listener1 = new Listener1();
        Listener listener2 = new Listener1();

        //两个观察者注册到被观察者
        eventLauncher.addListener(listener1);
        eventLauncher.addListener(listener2);

        //被观察者发生事件．这里实现了在此事件中,通知所有观察者
        eventLauncher.happends();

        //from形式产生事件
        String[] strArray = {"a", "b", "c"};
        EventLauncher<String> eventLauncher1 = EventLauncher.from(strArray);
        eventLauncher1.addListener(new Listener<String>() {
            @Override
            public void do1(String s) {
                System.out.println("Listener do1 " + s);
            }

            @Override
            public void do2(String s) {
                System.out.println("Listener do2 " + s);
            }

        });
        //发生全部事件
        eventLauncher1.happends();
    }
}

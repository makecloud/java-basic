package com.liuyihui.common.事件回调;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 相当于rxjava中的被观察者<br>
 * <p>
 * 事件发射机
 *
 * @param <T>
 */
public class EventLauncher<T> {

    /** 维护的监听者集合,在需要的时候,回调观察者(即通知观察者) */
    private List<Listener<T>> listenerList = new ArrayList<>();
    /** 添加listener时的回调 */
    private OnAddListenerCallback onAddListenerCallback;
    /** 维护一个事件集合 */
    private List<Event<T>> eventList;
    /** 维护一个 对象转换器 */
    private EventTransformer<T, String> eventTransformer;

    /**
     * 创建默认事件发射机
     */
    public EventLauncher() {
        //初始化默认事件集合
        this.eventList = new ArrayList<Event<T>>();
        //初始化默认"添加listener" 时的回调
        this.onAddListenerCallback = new OnAddListenerCallback<T>() {
            @Override
            public void call(Listener<T> listener) {
                for (Event<T> e : eventList) {
                    listener.do1(e.getCore());
                    listener.do2(e.getCore());
                }
            }
        };
    }


    /**
     * 使用数组创建事件发射机<br>
     * 每个数组元素生成一个事件
     *
     * @param ts  不定类型数组
     * @param <T>
     * @return
     */

    public static <T> EventLauncher<T> from(T[] ts) {

        //创建一个默认事件发射机对象
        EventLauncher<T> eventLauncher = new EventLauncher<>();
        //将数组添加到事件集合
        for (T t : ts) {
            //封装事件并添加到事件集合
            Event<T> event = new Event<T>();
            event.setCore(t);
            eventLauncher.getEventList().add(event);
        }
        return eventLauncher;
    }


    /**
     * 创建launcher方式之一:
     * <p>
     * 定制一个添加listener 动作回调
     *
     * @param l 添加listener 动作回调
     * @return
     */
    public static EventLauncher create(OnAddListenerCallback l) {
        EventLauncher eventLauncher = new EventLauncher();
        if (l != null) {
            eventLauncher.setOnAddListenerCallback(l);
        }
        return eventLauncher;
    }

    /**
     * todo
     * 分解<br>
     *
     * @return
     */
    public EventLauncher<T> deleave() {
        return null;
    }

    /**
     * 事件对象转化<br>
     * 让发出的事件包含的对象改变为另一种对象
     *
     * @return
     */
    public <R> EventLauncher<R> mapEvent(EventTransformer<T, R> eventTransformer) {
        EventLauncher<R> eventLauncher = new EventLauncher<>();

        List<Event<R>> newEventList = new ArrayList<>();

        for (Event<T> event : eventList) {

            T eventCoreObject = event.getCore();
            R r = eventTransformer.transform(eventCoreObject);
        }
        //todo
        return null;
    }

    /**
     * todo
     * 转换成新类型的observerable
     */
    public void map() {
    }


    /**
     * 添加一个监听
     *
     * @param listener
     */
    public void addListener(Listener<T> listener) {
        listenerList.add(listener);
        //回调 添加监听 动作
        if (onAddListenerCallback != null) {
            onAddListenerCallback.call(listener);
        }
    }

    /**
     * 发射事件到某listener
     *
     * @param listener
     */
    public void happenToOneListener(Listener<T> listener) {
        for (Event<T> e : eventList) {
            e.getCore();

            if (eventTransformer != null) {

            }
            listener.do1(e.getCore());
            listener.do2(e.getCore());
        }
    }


    /**
     * 发生所有事件
     */
    public void happends() {
        for (Listener<T> listener : listenerList) {
            for (Event<T> e : eventList) {
                listener.do1(e.getCore());
                listener.do2(e.getCore());
            }
        }
    }


    //getter & setter
    public void setOnAddListenerCallback(OnAddListenerCallback l) {
        this.onAddListenerCallback = l;
    }

    private List<Listener<T>> getListenerList() {
        return listenerList;
    }

    private void setListenerList(List<Listener<T>> listenerList) {
        this.listenerList = listenerList;
    }

    private OnAddListenerCallback getOnAddListenerCallback() {
        return onAddListenerCallback;
    }

    private List<Event<T>> getEventList() {
        return eventList;
    }

    private void setEventList(List<Event<T>> eventList) {
        this.eventList = eventList;
    }

    /**
     * "添加listener" 动作的callback
     *
     * @param <T>
     */
    interface OnAddListenerCallback<T> {

        /**
         * 传入添加的listener
         *
         * @param listener
         */
        void call(Listener<T> listener);
    }
}


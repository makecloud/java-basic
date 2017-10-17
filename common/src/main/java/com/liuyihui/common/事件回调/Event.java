package com.liuyihui.common.事件回调;

/**
 * "事件"数据结构<br>
 * <p>
 * 事件封装一个不定类型
 *
 * @param <T>
 */
public class Event<T> {

    /** 封装原本对象 */
    private T core;

    public T getCore() {
        return core;
    }

    public void setCore(T core) {
        this.core = core;
    }

}

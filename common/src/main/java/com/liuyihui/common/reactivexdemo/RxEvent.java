package com.liuyihui.common.reactivexdemo;

/**
 * 自定义的事件类
 *
 * @author liuyi 2017年12月14日14:24:33
 */
public class RxEvent {
    private int eventType;
    private Object data;

    public RxEvent(int eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }

    @Override
    public String toString() {
        return "eventType=" + this.eventType;
    }
}

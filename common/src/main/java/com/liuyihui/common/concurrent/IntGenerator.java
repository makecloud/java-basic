package com.liuyihui.common.concurrent;

/**
 * 生产者<br>
 * 整数生成器<br>
 */
public abstract class IntGenerator {
    private boolean cancel = false;

    public abstract int nextEven();

    public void cancel() {
        cancel = true;
    }

    public boolean isCanceled() {
        return cancel;
    }
}

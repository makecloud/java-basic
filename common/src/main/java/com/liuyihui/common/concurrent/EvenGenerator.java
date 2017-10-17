package com.liuyihui.common.concurrent;

public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int nextEven() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }
}

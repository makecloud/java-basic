package com.liuyihui.common.泛型应用形式;

/**
 * 泛型应用形式<br>
 * 泛型就是具体运行时不知道是什么类型, 可以为任意类型
 *
 * @param <T>
 */
public class GenericTypeUsage<T> {
    public GenericTypeUsage() {
        T a;
    }

    //泛型方法
    public <M> M stkmcll(M m) {
        return m;
    }

    public void call(T a) {

    }

    public static void main(String[] args) {
        GenericTypeUsage<String> stringWbscreb = new GenericTypeUsage();
        new SBscreb<String>() {

            @Override
            public void call(String s) {

            }
        };
    }
}

interface SBscreb<T> {
    void call(T t);
}

interface Xbsdv<K, V> {

    void call(K k, V v);

    K call(V v);
}

interface Mbsldfj {
    <G> G call();
}

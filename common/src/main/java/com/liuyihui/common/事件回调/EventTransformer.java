package com.liuyihui.common.事件回调;

/**
 * 类型转换器接口
 *
 * @param <O> 旧类型
 * @param <N> 新类型
 */
interface EventTransformer<O, N> {

    /**
     * 类型转换过程
     *
     * @param beforeObject 原类型对象
     * @return 目标类型对象
     */
    N transform(O beforeObject);
}

package com.liuyihui.common.泛型应用形式;

/**
 * 演示怎么获取去泛型参数T的class
 * <p>
 * 从构造方法传入tClass
 *
 * @param <T>
 */
public class BaseDAO<T> {
    Class<T> tClass;

    /**
     * 构造方法
     *
     * @param tClass 在这里传入泛型的class
     */
    public BaseDAO(Class<T> tClass) {
        //实体类的class对象
//        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
//        Type[] types = type.getActualTypeArguments();
//        this.tClass = (Class<T>) types[0];
        this.tClass = tClass;

    }

    /**
     * 执行主方法测试
     *
     * @param args
     */
    public static void main(String[] args) {
        BaseDAO<String> baseDAO = new BaseDAO<>(String.class);
    }

}

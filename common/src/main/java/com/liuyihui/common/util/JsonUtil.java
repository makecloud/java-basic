package com.liuyihui.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.liuyihui.common.io.FileReadWriteLock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * json序列化反序列化工具类
 * Created by liuyi on 2017/11/23.
 */

public class JsonUtil {


    public static <T> List<T> dataToList(Object data, Class<T> tClass) {
        return JSON.parseArray(JSON.toJSONString(data), tClass);
    }

    /**
     * 转固定元素类型的分页实体。如PlanPage，其内是{@code List<Plan> }这种明确的非泛型定义的集合。
     * <p>
     * 转{@code Page<T>}这种带泛型的分页实体类，adapter内{@code AdUnit adunit = dataSet.get(position);}报了异常:
     * <p>
     * {@code java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com
     * .yunge.client.media.statistic.model.AdUnit}
     *
     * @param data
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T dataToObject(Object data, Class<T> tClass) {
        return JSON.toJavaObject((JSON) JSONObject.toJSON(data), tClass);
    }

    /**
     * 通用：
     * json串转为对象
     * json串转为list
     * json串转为带泛型参数的实体类对象
     *
     * @param data
     * @param typeReference e.g. new TypeReference<PlanTempletDetail>(){}
     * @param <T>
     * @return
     */
    public static <T> T dataToObject(Object data, TypeReference<T> typeReference) {
        return JSON.parseObject(JSONObject.toJSONString(data), typeReference);
    }

    /**
     * json文件 ->  Pojo对象
     *
     * @param pojoclass Pojo类class
     * @param file      存储json的文件
     * @param <T>
     * @return pojo对象
     */
    public static <T> T readJsonFileToObject(Class<T> pojoclass, File file) throws IOException {
        T type = null;
        FileReadWriteLock lock = FileReadWriteLock.get(file.getAbsolutePath());
        boolean flag = lock.obtain();
        if (flag) {
            type = JSON.parseObject(readStrFromFile(file.getAbsolutePath()), pojoclass);
            lock.unlock();
        }

        return type;
    }

    /**
     * json文件 ->  Pojo对象/list对象
     *
     * @param typeReference new TypeReference<T>{}
     * @param file
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T readJsonFileToObject(TypeReference<T> typeReference, File file) throws IOException {
        T type = null;
        FileReadWriteLock lock = FileReadWriteLock.get(file.getAbsolutePath());
        boolean flag = lock.obtain();
        if (flag) {
            type = JSON.parseObject(readStrFromFile(file), typeReference);
            lock.unlock();
        }
        return type;
    }

    /**
     * 文件内容 -> String
     *
     * @param fileName 文件名
     * @return 文件内容str
     */
    private static String readStrFromFile(String fileName) throws IOException {
        return readStrFromFile(new File(fileName));
    }

    /**
     * 文件内容 -> String
     *
     * @param file 文件对象
     * @return 文件内容str
     * @throws IOException
     */
    private static String readStrFromFile(File file) throws IOException {
        String result = "";
        char[] temp = new char[4096];
        Reader reader = null;
        int len;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            while ((len = reader.read(temp)) != -1) {
                result += String.valueOf(temp, 0, len);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}

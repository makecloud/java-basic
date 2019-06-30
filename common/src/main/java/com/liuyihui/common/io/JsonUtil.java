package com.liuyihui.common.io;

import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * json序列化反序列化工具类
 * Created by liuyi on 2017/11/23.
 */

public class JsonUtil {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger("JsonUtil");


    /**
     * json文件->对象
     * <p>
     * 同时支持转pojo和转list
     * <p>
     * 使用gson框架(暂时没引用gson框架,暂时注释此方法)
     *
     * @param jsonFile json文件
     * @param tType    类型 转Pojo对象传Pojo.class, 转List对象传new TypeToken<List<PlayTask>>(){}.getType()
     * @param <T>      用泛型T标识返回类型
     * @return pojo对象或者list
     */
    /*public static <T> T fromJsonFile(File jsonFile, Type tType) {
//        Type tType = new TypeToken<List<PlayTask>>() {
//        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(jsonFile));
        } catch (FileNotFoundException e) {
            logger.error("", e);
        }

        return gson.fromJson(reader, tType); // contains the whole reviews list
    }*/

    /**
     * json文件 ->  Pojo对象
     *
     * @param pojoclass Pojo类class
     * @param file 存储json的文件
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
     * 文件 -> String
     *
     * @param fileName 文件名
     * @return 文件内容str
     */
    public static String readStrFromFile(String fileName) throws IOException {
        String result = "";
        char[] temp = new char[4096];
        Reader reader = null;
        int len;
        try {
            reader = new InputStreamReader(new FileInputStream(fileName));
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
}

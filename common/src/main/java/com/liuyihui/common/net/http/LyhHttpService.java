package com.liuyihui.common.net.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liuyihui.common.net.http.exception.LyhException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 我定义的http服务结构
 * <p>
 * 可实现为通用版本http服务<br>
 * 可实现为android版本http服务<br>
 *
 * @author liuyi 2017年11月9日16:52:03
 */
public interface LyhHttpService {

    /**
     * 从url获取json响应
     * 使用 okhttp3 版本
     *
     * @param url get请求的url
     * @return 返回json
     * @throws LyhException
     */
    String getJsonFromUrl(String url);

    /**
     * 从url获取输入流
     *
     * @param url 返回输入流的url
     * @return 输入流对象
     * @throws LyhException
     */
    InputStream getInputStreamFromUrl(String url) throws LyhException;

    /**
     * 从url获取字节数组
     *
     * @param url
     * @return 字节数组
     * @throws LyhException
     */
    byte[] getByteArrayFromUrl(String url) throws LyhException;

    /**
     * 从url获取字节数组输入流
     *
     * @param url
     * @return ByteArrayInputStream对象
     * @throws LyhException
     */
    ByteArrayInputStream getByteArrayInputStreamFromUrl(String url) throws LyhException;

    /**
     * 从url获取apiResponse对象
     * 已处理apiResponse==null
     *
     * @param url
     * @return 对data转换类型时注意：返回的apiresponse的元素data此时为JSONObject型
     */
    ApiResponse getApiResponseObjectFromUrl(String url) throws LyhException;

    /**
     * 从url 获取apiResponse对象的data对象（data为JSONObject型）。
     * 调用前提：apiResponse的data为单个对象时
     *
     * @param url
     * @return
     * @throws LyhException
     */
    JSONObject getJSONObjectDataFromUrl(String url) throws LyhException;

    /**
     * 从url 获取apiResponse对象的data列表对象（JSONArray类型）。
     * 调用前提：apiResponse的data为列表时
     *
     * @param url
     * @return
     * @throws LyhException
     */
    JSONArray getJSONArrayDataFromUrl(String url) throws LyhException;

    /**
     * 从url获取指定类型的对象（实体对象）
     * apiResponse的data为单个对象时
     *
     * @param url
     * @param clazz 指定类型的class对象
     * @param <T>
     * @return 返回值可为能null
     * @throws LyhException
     */
    <T> T getTDataFromUrl(String url, Class<T> clazz) throws LyhException;

    /**
     * 从url获取指定元素类型的列表。
     * 依赖apiResponse的data为列表时
     *
     * @param url
     * @param clazz 指定类型的class对象
     * @param <T>
     * @return
     * @throws LyhException
     */
    <T> List<T> getTListDataFromUrl(String url, Class<T> clazz) throws LyhException;

    /**
     * post请求
     * <p>
     * 期望http响应为字符串,json结构等
     *
     * @param httpUrl url
     * @param params  请求参数
     * @return
     */
    String postRequest(String httpUrl, Map<String, String> params);

    /**
     * 提交post请求到url，返回响应对象（已处理响应为空）
     *
     * @param url     地址
     * @param formMap 请求表单（键值对）
     * @return ApiResponse
     * @throws LyhException
     */
    ApiResponse postUrl(String url, final Map<String, String> formMap) throws LyhException;

    /**
     * 下载
     *
     * @param downloadUrl   下载地址
     * @param localFileName 路径文件名
     */
    void downloadByGet(String downloadUrl,String localFileName);
}

package com.liuyihui.common.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liuyihui.common.http.exception.LyhException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 使用apache的common-httpclient 的通用http服务<br>
 *
 * @author liuyi 2017年11月9日16:54:15
 */
public class HttpService implements LyhHttpService {

    private static HttpClient httpClient = new HttpClient();


    /**
     * 隐藏构造方法
     */
    private HttpService() {
    }

    /**
     * 获取单例
     *
     * @return httpService
     */
    public static HttpService getInstance() {
        return HttpServiceInstanceHolder.httpService;
    }

    /**
     * 单例holder
     */
    private static class HttpServiceInstanceHolder {
        static HttpService httpService = new HttpService();
    }

    /**
     * 使用commons-httpclient框架,执行 http Get 请求
     *
     * @param url 必须带http://
     * @return 请求返回的字符串
     */
    @Override
    public String getJsonFromUrl(String url) {
        String apiResponseStr = null;
        HttpMethod httpMethodGet = new GetMethod(url);
        //设置get请求的重试次数 Provide custom retry handler is necessary
        httpMethodGet.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            int statusCode = httpClient.executeMethod(httpMethodGet);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Response Code:" + statusCode);
                return null;
            }
            apiResponseStr = new String(httpMethodGet.getResponseBody(), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpMethodGet.releaseConnection();
        }
        return apiResponseStr;
    }

    /**
     * 从url获取输入流
     *
     * @param url 返回输入流的url
     * @return 输入流对象
     * @throws LyhException
     */
    @Override
    public InputStream getInputStreamFromUrl(String url) throws LyhException {
        HttpMethod httpMethodGet = new GetMethod(url);
        //设置get请求的重试次数 Provide custom retry handler is necessary
        httpMethodGet.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        //执行，处理响应
        try {
            int statusCode = httpClient.executeMethod(httpMethodGet);
            if (statusCode != 200) {
                System.err.println("Response Code:" + statusCode);
                return null;
            }
            InputStream is = httpMethodGet.getResponseBodyAsStream();
            if (is == null || is.available() == 0) {
                throw new LyhException("获取的输入流中字节数量为0");
            }
            return is;
        } catch (IOException e) {
            e.printStackTrace();
            throw new LyhException("网络连接失败");
        } finally {
            httpMethodGet.releaseConnection();
        }
    }

    @Override
    public byte[] getByteArrayFromUrl(String url) throws LyhException {
        HttpMethod httpMethodGet = new GetMethod(url);
        //执行，处理响应
        try {
            httpClient.executeMethod(httpMethodGet);
            return httpMethodGet.getResponseBody();
        } catch (Exception e) {
            throw new LyhException("网络连接失败");
        } finally {
        }
    }

    @Override
    public ByteArrayInputStream getByteArrayInputStreamFromUrl(String url) throws LyhException {
        byte[] byteArray = getByteArrayFromUrl(url);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        return bais;
    }

    @Override
    public ApiResponse getApiResponseObjectFromUrl(String url) throws LyhException {
        ApiResponse apiResponse = null;
        String jsonstr = getJsonFromUrl(url);
        apiResponse = JSON.parseObject(jsonstr, ApiResponse.class);
        if (apiResponse == null) {
            throw new LyhException("获取的响应对象为空");
        }
        return apiResponse;
    }

    @Override
    public JSONObject getJSONObjectDataFromUrl(String url) throws LyhException {
        ApiResponse apiResponse = getApiResponseObjectFromUrl(url);
        int code = apiResponse.getCode();
        if (code != 0) {
            throw new LyhException(apiResponse.getMessage());
        }
        JSONObject jsonObjectData = (JSONObject) apiResponse.getData();
        return jsonObjectData;
    }

    @Override
    public JSONArray getJSONArrayDataFromUrl(String url) throws LyhException {
        ApiResponse apiResponse = getApiResponseObjectFromUrl(url);
        int code = apiResponse.getCode();
        if (code != 0) {
            throw new LyhException("服务器:" + apiResponse.getMessage());
        }
        JSONArray jsonArrayData = (JSONArray) apiResponse.getData();
        if (jsonArrayData == null) {
            jsonArrayData = new JSONArray();
        }
        return jsonArrayData;
    }

    @Override
    public <T> T getTDataFromUrl(String url, Class<T> clazz) throws LyhException {
        JSONObject jsonObject = getJSONObjectDataFromUrl(url);
        if (jsonObject == null) {
            return null;
        }
        T t = (T) jsonObject.toJavaObject(clazz);
        return t;
    }

    @Override
    public <T> List<T> getTListDataFromUrl(String url, Class<T> clazz) throws LyhException {
        String arrayStr = getJSONArrayDataFromUrl(url).toJSONString();
        List<T> tList;
        tList = JSON.parseArray(arrayStr, clazz);
        return tList;
    }

    /**
     * post请求
     * <p>
     * 期望http响应为字符串,json结构等
     *
     * @param httpUrl url
     * @param params  请求参数
     * @return
     */
    @Override
    public String postRequest(String httpUrl, Map<String, String> params) {
        // (1)构造HttpClient的实例
        HttpClient client = new HttpClient();
        // (2)创建POST方法的实例
        PostMethod postMethod = new PostMethod(httpUrl);
        // (3)设置http request头
        postMethod.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        //(4)设置请求体
        if (params != null && params.size() > 0) {
            NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
            Iterator<String> iterator = params.keySet().iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = params.get(key);
                nameValuePairs[i].setName(key);
                nameValuePairs[i].setValue(value);
                ++i;
            }
            postMethod.setRequestBody(nameValuePairs);
        }
        // (5)执行postMethod
        try {
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("response Code: " + postMethod.getStatusLine());
            }
            // (6)读取response头信息
            Header headerResponse = postMethod.getResponseHeader("Content-Type");
            String headerStr = headerResponse.getValue();
            System.out.println("Response header :" + headerStr);
            // (7)读取内容
            return new String(postMethod.getResponseBody(), "utf8");
        } catch (HttpException e) {
            System.out.println("http exception:" + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        return null;
    }

    @Override
    public ApiResponse postUrl(String url, Map<String, String> params) throws LyhException {
        return JSON.parseObject(postRequest(url, params), ApiResponse.class);

    }

    /**
     * 下载
     *
     * @param downloadUrl   下载地址
     * @param localFileName 路径文件名
     */
    @Override
    public void downloadByGet(String downloadUrl, String localFileName) {

        //1.校验本地文件
        File localFile = new File(localFileName);
        //先删除源文件
        /*if (localFile.delete()) {
            System.err.println("file already exist Before download, deleted !");
        }*/
        if (!localFile.exists()) {
            try {
                localFile.createNewFile();
                System.out.println("file not exist, created !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //下载前这个文件已存的情况, 通过在校验md5, 判断需不需要下载
            System.out.println("file exist");
            //md5不符合,删除文件
            //todo
            //否则return
            return;
        }

        //下载
        long sumLength = 0;
        long existLength = localFile.length();
        String ETag = "";//todo 没有使用etag控制下载过程中文件变了的情况, 应该很少出现这种情况
        HttpClient client = new HttpClient();
        GetMethod get = null;
        FileOutputStream fos = null;
        try {
            //test code, 这段代码用来先下载一部分
//                get.setRequestHeader("Range", "bytes=0-24");
//                System.out.println("continue download after part :" + Arrays.toString(get.getRequestHeaders()));
            System.out.println("beigin http download...");
            get = new GetMethod(downloadUrl);
            int statusCode = client.executeMethod(get);

            //code==200,代表http响应为整个文件
            if (HttpStatus.SC_OK == statusCode) {
                ETag = get.getResponseHeader("ETag").getValue();
                sumLength = Long.parseLong(get.getResponseHeader("Content-Length").getValue());
                System.out.println("file, [sumLength:" + sumLength + "]");
                // 得到网络资源的字节数组,并写入文件,这里是覆盖写入
                byte[] bytes = get.getResponseBody();
                System.out.println("downloaded bytes:" + bytes.length);
                if (bytes.length > 0) {
                    fos = new FileOutputStream(localFile);
//                    System.out.println("begin write file ...");
                    fos.write(bytes);
                    System.out.println("write file finish，" + "fileNowLength:" + localFile.length());
                }

                //未下载完
                if (localFile.length() < sumLength) {
                    System.out.println("downLoad unfinished !\ngo on.\n");
                }
            } else {
                System.out.println("DownLoad file error :" + statusCode);
            }

            //续传
            while (localFile.length() < sumLength) {
                get = new GetMethod(downloadUrl);
                //设置只下载之后的部分
                get.setRequestHeader("Range", "bytes=" + existLength + "-");
                get.setRequestHeader("If-Range", "\"42A5A9BD22A30244C543DE5B363F49F6\"");
                System.out.println("continue download after part :" + Arrays.toString(get.getRequestHeaders()));

                //code==206,代表返回的是部分文件
                if (HttpStatus.SC_PARTIAL_CONTENT == statusCode) {
                    System.out.println("file part :");
                    ETag = get.getResponseHeader("ETag").getValue();
                    // 得到网络资源的字节数组,并写入文件
                    byte[] bytes = get.getResponseBody();
                    System.out.println("download bytes:" + bytes.length);
                    if (bytes.length > 0) {
                        fos = new FileOutputStream(localFile, true);
                        System.out.println("begin write file ...");
                        fos.write(bytes);
                        System.out.println("write file finish." + "fileNowLength:" + localFile.length());
                    }

                    //未下载完
                    if (localFile.length() < sumLength) {
                        System.out.println("downLoad unfinished !\ngo on.\n");
                    }

                } else {
                    System.out.println("DownLoad file error :" + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (get != null) {
                get.releaseConnection();
            }
            client.getHttpConnectionManager().closeIdleConnections(0);
        }

    }
}

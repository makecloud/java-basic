package com.liuyihui.common.net.http.demoFromFriend;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 来自网友的 httpClient使用示范
 *
 * @author liuyi 2017年11月10日10:50:41
 */
public class HttpClientDemo1 {

    private String httpUrl;

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public HttpClientDemo1(String httpUrl) {
        this.httpUrl = httpUrl;
    }


    /**
     * get请求 示范
     *
     * @throws IllegalStateException
     * @throws IOException
     */
    public static void getRequest() throws IllegalStateException, IOException {
        // (1)构造HttpClient的实例
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        // (2)创建GET方法的实例
        GetMethod method = new GetMethod("http://192.168.1.48:8084/map/downloadlist/testdownload.do?downloadId=2cef129f-decb-4910-8d5c-cad7c4564fe0&fileOffset=0");
        // Provide custom retry handler is necessary  
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        try {
            // Execute the method.  
            int statusCode = client.executeMethod(method);
            System.out.println(statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024 * 1024 * 10];
                int r_len = 0;
                FileOutputStream outputStream = new FileOutputStream("F:\\AntDowload\\b.txt");
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method.getResponseCharSet()));
                    outputStream.write(b, 0, r_len);
                }
                outputStream.flush();
                outputStream.close();
            } else {
                System.err.println("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
            if (ins != null) {
                ins.close();
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * post请求 示范
     * <p>
     * 期望http响应为字符串,json结构等
     *
     * @param httpUrl url
     * @param params  请求参数
     * @return
     */
    public static String postRequest(String httpUrl, Map<String, String> params) {
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

    /**
     * post方式断点续传下载
     *
     * @param downloadUrl
     * @param downloadPath
     */
    public static void postRequestDownload(String downloadUrl, String downloadPath) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        // (1)构造HttpClient的实例
        HttpClient client = new HttpClient();
        // (2)创建POST方法的实例
        PostMethod postMethod = new PostMethod(downloadUrl);
        // (3)设置http request头
        postMethod.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        //设置请求体
        NameValuePair[] param = {new NameValuePair("downloadId", ""),
                new NameValuePair("range", ""),};
        postMethod.setRequestBody(param);
        // (4)执行postMethod
        try {
            int statusCode = client.executeMethod(postMethod);
            // (5)读取内容
            if (statusCode == HttpStatus.SC_OK) {
                inputStream = postMethod.getResponseBodyAsStream();
//                System.out.println("inputStream.read()=====" + inputStream.read());
                outputStream = new FileOutputStream(downloadPath);
                byte[] bytes = new byte[10485760];
                int r_len = 0;
                while ((r_len = inputStream.read(bytes)) > 0) {
//                 System.out.println("inputStream.available()====="+inputStream.available());
                    outputStream.write(bytes);
                }
            } else {
                System.err.println("download failed:" + postMethod.getStatusLine());
            }
        } catch (HttpException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用get方式的断点续传下载
     *
     * @param downloadUrl
     * @param localFileName eg: /usr/data/localFile.jpg
     */
    public static void downLoadByGet(String downloadUrl, String localFileName) {

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

    public static void main(String[] args) {
        downLoadByGet("http://test.yungeshidai.com/material/195e4c5e5805313f909cbed25fb13182.jpg",
                "D:\\cloudsong\\player_sign_tool\\androidstudio_outputs_each_flavor\\tmp_\\downloadByGet.jpg");

    }

}
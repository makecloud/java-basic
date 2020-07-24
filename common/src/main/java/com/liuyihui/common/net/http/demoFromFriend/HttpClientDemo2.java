package com.liuyihui.common.net.http.demoFromFriend;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpClientDemo2 {

    public static void main(String[] args) {

    }

    /**
     * get方式下载,断点续传,
     * <p>
     * 使用apache的httpcomponent.httpClient框架
     *
     * @param url
     * @param filePath
     * @return
     */
    private static boolean download(String url, String filePath) {
        HttpClient httpClient1 = new DefaultHttpClient();
        HttpGet httpGet1 = new HttpGet(url);
        try {
            HttpResponse httpResponse1 = httpClient1.execute(httpGet1);
            StatusLine statusLine = httpResponse1.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                File file = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(file);
                InputStream inputStream = httpResponse1.getEntity().getContent();
                byte b[] = new byte[1024 * 1024 * 10];
                int j = 0;
                while ((j = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, j);
                }
                outputStream.flush();
                outputStream.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            httpClient1.getConnectionManager().shutdown();
        }
        return true;
    }
}

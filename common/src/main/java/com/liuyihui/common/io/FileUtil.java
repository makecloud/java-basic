package com.liuyihui.common.io;

import com.alibaba.fastjson.JSON;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA1;

/**
 * 文件读写工具类
 *
 * @author liuyi 2017年11月8日14:38:22
 */
public class FileUtil {

    /**
     * 创建文件File对象，如果不存在则创建目录<br>
     *
     * @param filePath 文件路径
     * @return 文件对象
     */
    public static File createFileIfNoExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 创建目录
     *
     * @param dir 目录
     */
    public static void createDirIfNotExist(String dir) {
        File directory = new File(dir);
        if (!directory.exists() || !directory.isDirectory()) {
            if (!directory.mkdirs()) {
                System.err.println("mkdirs fail");
            }
        }
    }

    /**
     * 利用HMACSHA1加密
     *
     * @param src 源字节
     * @param key key
     * @return 加密后字节
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] hash_hmac(byte[] src, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] cipherByte = mac.doFinal(src);
        return cipherByte;
    }

    //--------------------------------------------读--------------------------------------

    /**
     * 文件内容 -> String
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

    /**
     * 读取inputsteam里的字符串
     *
     * @param is 输入流对象
     * @return 字符串
     */
    public static String readInputStreamToStr(InputStream is) throws IOException {
        int i;
        char c;
        StringBuilder sb = new StringBuilder();
        while ((i = is.read()) != -1) {
            c = (char) i;
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 读取inputsteamreader里的字符串
     *
     * @param isr 输入reader对象
     * @return 字符串
     */
    public static String readInputstreamReaderToStr(InputStreamReader isr) throws IOException {
        int i;
        char c;
        StringBuilder sb = new StringBuilder();
        while ((i = isr.read()) != -1) {
            c = (char) i;
            sb.append(c);
        }

        return sb.toString();
    }

    //--------------------------------------------写-------------------------------------

    /**
     * pojo对象写入文件
     *
     * @param f 文件
     * @param obj pojo对象
     */
    public static void objWriteToFile(File f, Object obj) throws IOException {
        String str = JSON.toJSONString(obj);
        FileWriter fileWriter = null;
        //文件锁，可能有多个线程同时处理文件
        FileReadWriteLock lock = FileReadWriteLock.get(f.getAbsolutePath());
        boolean flag;
        try {
            flag = lock.obtain();
            if (flag) {
                fileWriter = new FileWriter(f);
                fileWriter.write(str);
                lock.unlock();
            }
            lock = null;
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * str写入文件
     *
     * @param f 文件
     * @param str
     */
    public static void strWriteToFile(File f, String str) throws IOException {

        FileWriter fileWriter = null;
        //文件锁，可能有多个线程同时写文件
        FileReadWriteLock lock = FileReadWriteLock.get(f.getAbsolutePath());
        boolean flag;
        try {
            flag = lock.obtain();
            if (flag) {
                fileWriter = new FileWriter(f);
                fileWriter.write(str);
                lock.unlock();
            }
            lock = null;
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //--------------------------------------------删-------------------------------------

    /**
     * 删除文件夹
     * <p>
     * 递归删除文件夹下的所有文件及子文件夹下的所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteNotEmptyDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteNotEmptyDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}

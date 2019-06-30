package com.liuyihui.common.util;

import org.junit.Test;

import java.io.*;

/**
 * 计算代码行数
 * Created by liuyh on 2016/11/25.
 */
public class CountLinesUtil {

    public static void main(String[] args) {
        new CountLinesUtil().doCount();
    }

    @Test
    public void doCountOohlinkPlayer() {
        //资源路径
        File oohlinkappDir = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player");
        int amount = countDirectory(oohlinkappDir);
        System.out.println(amount);

    }

    @Test
    public void countPlatform() {
        File oohlinkappDir = new File("D:\\company_oohlink\\oohlink_platform\\platform_project\\platform");
        int amount = countDirectory(oohlinkappDir);
        System.out.println(amount);
    }

    @Test
    public void doCount() {
        //资源路径
        File appJavaDir = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\app\\src\\main\\java");
        File appResDir = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\app\\src\\main\\res");
        File appManifest = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\app\\src\\main\\AndroidManifest.xml");

        File libJavaDir = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\playersdk\\src\\main\\java");
        File libResDir = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\playersdk\\src\\main\\res");
        File libManifest = new File("D:\\company_oohlink\\oohlink_player\\oohlink-player\\playersdk\\src\\main\\AndroidManifest.xml");

        int appJavaLines = countDirectory(appJavaDir);
        System.out.println("app javaLines：" + (appJavaLines));
        int libJavaLines = countDirectory(libJavaDir);
        System.out.println("lib javaLines：" + (libJavaLines));

        int appResLine = countDirectory(appResDir);
        System.out.println("app xmlLines：" + (appResLine));
        int libResLines = countDirectory(libResDir);
        System.out.println("lib xmlLines：" + (libResLines));

        int appManifestLines = countOneFileLines(appManifest);
        System.out.println("manifestLines:" + (appManifestLines));
        int libManifestLines = countOneFileLines(libManifest);
        System.out.println("lib manifestLines:" + (libResLines));




        System.out.println("总计:" +
                                   (appJavaLines +
                                           libJavaLines +
                                           appResLine +
                                           libResLines +
                                           appManifestLines +
                                           libManifestLines));
    }

    //递归文件夹
    public int countDirectory(File file) {
        int amount = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                amount += countDirectory(f);
            }
        } else {
            amount += countOneFileLines(file);
        }
        return amount;
    }

    public int countOneFileLines(File file) {
        String fileName = file.getName();
        if (!fileName.endsWith("xml")
                && !fileName.endsWith("java")
                && !fileName.endsWith("properties")
                && !fileName.endsWith("txt")
                && !fileName.endsWith("gitignore")
                && !fileName.endsWith("gradle")
                && !fileName.endsWith("pro")
                ) {
            return 0;
        }
        int line = 0;
        try {
            InputStreamReader isr = new FileReader(file);
            BufferedReader br = new BufferedReader(isr);
            while (br.readLine() != null) {
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}

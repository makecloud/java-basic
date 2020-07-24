package com.liuyihui.common.util;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * 批量修改java文件内容工具
 */
public class JavaFIlesUtil {

    /**
     * 按行修改java类文件内容
     */
    @Test
    public void testChange() {
        File dir = new File("/Users/makecloudl/company_oohlink/sinopec/sinopec-app-android/app/src/main/res");
//        File dir = new File("/Users/makecloudl/company_oohlink/migu3-app-android/app/src/main/res");
        changeDirectoryFilesPackage(dir);
    }


    /**
     * 递归目录
     *
     * @param file
     */
    public void changeDirectoryFilesPackage(File file) {
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                changeDirectoryFilesPackage(file1);
            }
        } else {
            if (file.getName().endsWith(".xml")) {
                try {
                    changeJavaFilePackage(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按行修改java类文件内容
     *
     * @param file
     * @throws IOException
     */
    private void changeJavaFilePackage(File file) throws IOException {
        //读
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int counter = 0;

        StringBuilder sb = new StringBuilder();
        String lineStr;
        while ((lineStr = bufferedReader.readLine()) != null) {
            //按行替换
            if (lineStr.contains("com.client.mylibrary.customview")) {
                lineStr = lineStr.replaceFirst("com\\.client\\.mylibrary\\.customview", "com\\.liuyihui\\.mylibrary\\.customview");
                counter++;
            }

            sb.append(lineStr + "\n");

        }

        //写
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(sb.toString());
        bufferedWriter.flush();

        //close
        bufferedReader.close();
        bufferedWriter.close();

        if (counter > 0) {
            System.out.println(String.format("%s,替换%d行", file.getName(), counter));
        }
    }
}

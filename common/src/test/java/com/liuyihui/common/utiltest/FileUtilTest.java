package com.liuyihui.common.utiltest;

import com.liuyihui.common.io.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileUtilTest {
    @Test
    public void testCreateDir() {
//        FileUtil.createDirIfNotExist("D:\\app\\20171122-C296FE8162A66AAD\\abcd");
        FileUtil.createDirIfNotExist("D:\\app\\20171122-C296FE8162A66AAD\\ab\\cd");
    }

    @Test
    public void testFilePath() {
        File file = new File("C:\\Users\\liuyi\\Desktop\\20171123-C296FE8162A66AAD\\material\\../material" +
                "/a668f37b7cdbced21543b952b7592c27.jpg");

        System.out.println(file.exists());
        System.out.println(file.length());
    }

    @Test
    public void testFileGetName() {
        File file = new File("C:\\Users\\liuyi\\Desktop\\20171123-C296FE8162A66AAD\\material\\../material" +
                "/a668f37b7cdbced21543b952b7592c27.jpg");
        System.out.println(file.getName());
    }

    @Test
    public void testDeleteNotEmptyDir(){
        File dir = new File("C:\\Users\\liuyi\\Desktop\\not empty");
        boolean deleted =FileUtil.deleteNotEmptyDir(dir);
        System.out.println(deleted);
    }
}

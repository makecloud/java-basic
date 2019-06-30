package com.liuyihui.common.utiltest;

import com.liuyihui.common.util.ZipUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ZipUtilTest {

    @Test
    public void testunzip() {
        File zipFile = new File("D:\\app\\20171122-C296FE8162A66AAD-C296FE8162A66AAD" +
                "-75a924107c8515ef132cc832a8625c46.zip");
        zipFile = new File("C:\\Users\\liuyi\\Desktop\\20180124-0C63FC025B44-e9f2e352b24de4599ba28ef4d1a63463.zip");

        try {
//            ZipUtils.unzip(zipFile, zipFile.getParent());
            ZipUtils.unzipToCurrentDir(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

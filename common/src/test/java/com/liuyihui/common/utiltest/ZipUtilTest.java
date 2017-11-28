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
        try {
            ZipUtils.unzip(zipFile, zipFile.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

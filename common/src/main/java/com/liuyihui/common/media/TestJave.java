package com.liuyihui.common.media;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

public class TestJave {
    public static void main(String[] args) throws Exception {
        File srcFile1 = new File("/Users/makecloudl/Desktop/aa.mp4");
        //File srcFiles3 = new File("/Users/baimenglong/Desktop/WechatIMG175.png");
        Encoder encoder = new Encoder();
        MultimediaInfo info = encoder.getInfo(srcFile1);
        System.out.println(info.toString());

        // 视频剪切1-4
    }
}

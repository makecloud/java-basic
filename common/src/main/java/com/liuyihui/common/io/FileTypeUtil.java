package com.liuyihui.common.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * 判断文件类型(当然是没有后缀的文件)
 *
 * @author liuyi 2017年11月8日14:38:22
 */
public class FileTypeUtil {

    /**
     * <p>不同的文件，读取的header信息不一样</p>
     */
    private static final int HEADER_SIZE = 4;

    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String GIF = "gif";

    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
        //images
        mFileTypes.put("FFD8FF", JPEG);
        mFileTypes.put("89504E47", PNG);
        mFileTypes.put("47494638", GIF);

        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");

        mFileTypes.put("41433130", "dwg"); //CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); //日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); //邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "zip");
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
        mFileTypes.put("", "");
    }

    public static String getFileType(String filePath) {
        return mFileTypes.get(getFileHeader(filePath));
    }

    //获取文件头信息
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[HEADER_SIZE];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}

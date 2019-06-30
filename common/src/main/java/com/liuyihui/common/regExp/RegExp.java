package com.liuyihui.common.regExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式
 *
 * @author liuyi
 */
@SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
public class RegExp {

    public static void regExp() {
        Pattern pattern = Pattern.compile("^\\w{12}-\\d{8}-\\w{3}.zip$");
        Matcher matcher = pattern.matcher("0C63FC01D82F-20171120-md5.zip");
        boolean b = matcher.matches();
        System.out.println(b);
    }

    public static void regDemo1() {
        String sample0 = "20180123-0C63FC025B44-f879d9d7430e35a320c18006b97e2bd2.zip";
        String sample1 = "20180123-0C63FC025B44_1-f879d9d7430e35a320c18006b97e2bd2.zip";
        String sample2 = "20180123-0C63FC025B44_5-f879d9d7430e35a320c18006b97e2bd2.zip";
        String sample3 = "20180123-0C63FC025B44_23-f879d9d7430e35a320c18006b97e2bd2.zip";
        String sample4 = "20180123-0C63FC025B44_74973195-f879d9d7430e35a320c18006b97e2bd2.zip";

        Pattern pattern = Pattern.compile("^\\d{8}-" + "0C63FC025B44" + "(_\\w+)?-\\w{32}.zip$");

        System.out.println();
        System.out.println(pattern.matcher(sample0).matches());
        System.out.println(pattern.matcher(sample1).matches());
        System.out.println(pattern.matcher(sample2).matches());
        System.out.println(pattern.matcher(sample3).matches());
        System.out.println(pattern.matcher(sample4).matches());
    }

    public static void main(String[] args) {
        regExp();
        regDemo1();
    }
}

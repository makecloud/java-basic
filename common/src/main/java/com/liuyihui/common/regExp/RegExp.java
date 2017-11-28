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

    public static void main(String[] args) {
        regExp();
    }
}

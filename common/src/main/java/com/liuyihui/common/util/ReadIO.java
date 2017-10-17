package com.liuyihui.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 输入输出流应用工具类
 * 
 * 简化频繁写流相关的字节转换代码，直接从流得到字符串
 * @author liuyh
 */
public class ReadIO {
	/**
	 * 读取inputsteam里的字符串
	 * @param is 输入流对象
	 * @return 字符串
	 */
	public static String readInputstream(InputStream is){
		int i;char c;StringBuilder sb = new StringBuilder();
		try {
			while((i=is.read())!=-1){
				c=(char)i;
				sb.append(c);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 读取inputsteamreader里的字符串
	 * @param isr 输入reader对象
	 * @return 字符串
	 */
	public static String readInputstreamreader(InputStreamReader isr){
		int i;char c;StringBuilder sb = new StringBuilder();
		try {
			while((i=isr.read())!=-1){
				c=(char)i;
				sb.append(c);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

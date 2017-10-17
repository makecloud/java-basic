package com.liuyihui.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

/**
 * 针对java为GBK编码的，批量转成utf8
 * 
 * @author liuyh 2017年1月11日
 */
public class Gbk2Utf {

	public static void main(String[] args) {
		// GBK编码格式源码路径s
		String[] srcDirPaths = {
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\camera",
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\decoding",
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\view" };

		// 转为UTF-8编码格式源码路径s
		String[] utf8DirPaths = {
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\camera\\utf8",
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\decoding\\utf8",
				"D:\\workspace_learn\\QR_CodeScan\\src\\com\\mining\\app\\zxing\\view\\utf8" };

		for (int i = 0; i < srcDirPaths.length; i++) {
			// GBK编码格式源码路径
			String srcDirPath = srcDirPaths[i];
			// 转为UTF-8编码格式源码路径
			String utf8DirPath = utf8DirPaths[i];
			// 获取所有java文件
			Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath),
					new String[] { "java" }, true);

			for (File javaGbkFile : javaGbkFileCol) {
				// UTF8格式文件路径
				String utf8FilePath = utf8DirPath
						+ javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
				// 使用GBK读取数据，然后用UTF-8写入数据
				try {
					FileUtils.writeLines(new File(utf8FilePath), "UTF-8",
							FileUtils.readLines(javaGbkFile, "GBK"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

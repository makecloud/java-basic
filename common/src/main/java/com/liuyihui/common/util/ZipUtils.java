package com.liuyihui.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * zip解压缩 工具类
 *
 * @author liuyi 2017年11月22日16:32:28
 */
public class ZipUtils {


    /**
     * 解压到当前文件夹
     * <p>
     * 已测试
     * <p>
     * 考虑解压后的文件与已存在文件重名情况怎么办?
     *
     * @param zipFile
     * @param descDir 目标目录
     * @throws IOException
     */
    public static void unzip(File zipFile, String descDir) throws IOException {
        if (!zipFile.exists()) {
            throw new RuntimeException("zip文件不存在");
        }

        ZipFile zf = new ZipFile(zipFile);
        Enumeration entries = zf.entries();
        ZipEntry entry = null;
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            System.out.println("解压出" + entry.getName());
            if (entry.isDirectory()) {
                String dirPath = descDir + File.separator + entry.getName();
                File dir = new File(dirPath);
                dir.mkdirs();
            } else {
                // 表示文件
                File f = new File(descDir + File.separator + entry.getName());
                if (!f.exists()) {
                    String dirs = f.getParent();
                    File parentDir = new File(dirs);
                    parentDir.mkdirs();
                }

                f.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zf.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);
                int count;
                byte[] buf = new byte[8192];
                while ((count = is.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                is.close();
                fos.close();

            }

        }
    }


    /**
     * 解压到参数file的当前文件夹
     * <p>
     * 已使用
     *
     * @param zipFile
     * @return
     */
    public static void unzipToCurrentDir(File zipFile) throws IOException {
        String currentPath = zipFile.getParent();
        if (!zipFile.exists()) {
            throw new RuntimeException("zip文件不存在");
        }

        ZipFile zf = new ZipFile(zipFile);
        Enumeration entries = zf.entries();
        ZipEntry entry = null;
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            System.out.println("解压出" + entry.getName());
            if (entry.isDirectory()) {
                String dirPath = currentPath + File.separator + entry.getName();
                File dir = new File(dirPath);
                dir.mkdirs();
            } else {
                // 表示文件
                File f = new File(currentPath + File.separator + entry.getName());
                if (!f.exists()) {
                    String dirs = f.getParent();
                    File parentDir = new File(dirs);
                    parentDir.mkdirs();
                }

                f.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zf.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);
                int count;
                byte[] buf = new byte[8192];
                while ((count = is.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                is.close();
                fos.close();
            }
        }
    }
}

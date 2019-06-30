package com.liuyihui.common.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.List;


/**
 * 使用java nio 操作文件的一些基本属性.本想给文件添加备注等属性,但是添加不了的.
 */
public class FileProcessor {
    public static void main(String[] args) {

        try {
            File a = new File("D:\\cloudsong\\client\\migu_client_document\\migu-media-2.0.0.1-debug.apk");
            Path filePath = Paths.get(a.toURI());

//            Files.readSymbolicLink(filePath);

            //acl属性view?
            AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(filePath,
                    AclFileAttributeView.class);
            System.out.println(aclFileAttributeView.name());
            System.out.println(aclFileAttributeView.getAcl().get(0).toString());
            //基本属性
            BasicFileAttributes basicFileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            //用户定义属性view?
            UserDefinedFileAttributeView fileAttributeView = Files.getFileAttributeView(filePath,
                    UserDefinedFileAttributeView.class);
            List<String> fileAttributeList = fileAttributeView.list();
            System.out.println(fileAttributeList);
            System.out.println(Files.getAttribute(filePath, "user:aaa", LinkOption.NOFOLLOW_LINKS));
            //posix属性? windows下不支持?
//            PosixFileAttributes posixFileAttributes = Files.readAttributes(filePath, PosixFileAttributes.class);
//            System.out.println(posixFileAttributes.group());
//            System.out.println(posixFileAttributes.owner());
//            System.out.println(posixFileAttributes.permissions());
            //FileOwner属性view
            FileOwnerAttributeView fileOwnerAttributeView = Files.getFileAttributeView(filePath, FileOwnerAttributeView
                    .class);
            System.out.println(fileOwnerAttributeView.getOwner().getName());
            //dos属性view
            DosFileAttributes dosFileAttributes = Files.readAttributes(filePath, DosFileAttributes.class);
            dosFileAttributes.isArchive();


            //设置属性
//            Files.setAttribute(filePath, "user:备注", "什么鬼", LinkOption.NOFOLLOW_LINKS);
            //设置属性2
//            ByteBuffer byteBuffer = ByteBuffer.wrap("mmm".getBytes("utf8"));
//            Files.setAttribute(filePath, "user:备注", byteBuffer, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        //文件路径
        File a = new File("D:\\cloudsong\\client\\migu_client_document\\咪咕APP测试Bug清单.docx");
        Path path = Paths.get(a.toURI());

        // User-Defined File Attributes View 用户自定义文件属性
        // 检测文件系统是否支持自定义属性
        try {
            FileStore store = Files.getFileStore(path);
            if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
                System.out
                        .println("The user defined attributes are not supported on: " + store);
            } else {
                System.out.println("The user defined attributes are supported on: " + store);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置文件属性
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path,
                UserDefinedFileAttributeView.class);
        /*try {
            int written = userDefinedFileAttributeView.write(
                    "file.description",
                    Charset.defaultCharset().encode(
                            "This file contains private information!"));
            System.out.println("write user defined file attribute return :"
                    + written);
        } catch (IOException e) {
            System.err.println(e);
        }*/
        // 获取文件的所有自定义属性
        try {
            for (String attributeName : userDefinedFileAttributeView.list()) {
                //打印属性名
                System.out.print(userDefinedFileAttributeView.size(attributeName) + "-" + attributeName);
                //打印属性
                int size = userDefinedFileAttributeView.size(attributeName);
                ByteBuffer bb = ByteBuffer.allocateDirect(size);
                userDefinedFileAttributeView.read(attributeName, bb);
                bb.flip();
                System.out.print(" = ");
                System.out.println(Charset.defaultCharset().decode(bb).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

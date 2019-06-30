package com.liuyihui.common.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 第一个freemaker学习类
 *
 * @author liuyi 2017年12月26日10:40:15
 */
public class FreemakerUtil {
    /**
     * @param name
     * @return
     */
    public Template getTemplate(String name) {
        try {
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
            Template template = configuration.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void print(String name, Map<String, Object> root) {
        try {
            //将模板输出到输出流
            Template template = this.getTemplate(name);
            template.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(""));
            Template template = this.getTemplate(name);
            template.process(root,fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

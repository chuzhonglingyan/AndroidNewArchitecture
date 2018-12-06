package com.yuntian.generatecode;

/**
 * @author chulingyan
 * @time 2018/12/06 23:00
 * @describe
 */

import com.google.common.base.CaseFormat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class Generator {


    private static final String BEAN_KEY = "bean";


    public void generate(File outDirFile, String packageName, String modelName) {
        try {
            generateContact(outDirFile, packageName, modelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateContact(File outDirFile, String packageName, String modelName) throws Exception {
        Configuration config = getConfiguration("contract.ftl");
        Template javaFileFtl = config.getTemplate("contract.ftl");
        generate(javaFileFtl, outDirFile, packageName+".contract", modelName,"contract");
    }

    private void generate(Template javaFileFtl, File outDirFile, String packageName, String modelName,String  tagName) throws Exception {
//        // 创建数据模型
        String clazzName = tableNameConvertUpperCamel(modelName)+tableNameConvertUpperCamel(tagName);

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        root.put("author", GenerateClass.AUTHOR);
        root.put("date", GenerateClass.DATE);
        root.put("describe", "接口约束");
        ParametersBean testBean = new ParametersBean();
        testBean.setPackageName(packageName);
        testBean.setClazzName(clazzName);
        root.put(BEAN_KEY, testBean);

        File file = toJavaFilename(outDirFile, testBean.getPackageName(), testBean.getClazzName());
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();
        Writer writer = new FileWriter(file);
        try {
            javaFileFtl.process(root, writer);
            writer.flush();
            System.out.println("Written " + file.getCanonicalPath());
        } finally {
            writer.close();
        }
    }


    public Configuration getConfiguration(String template) throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setClassForTemplateLoading(getClass(), "/");
        System.out.println(getClass().getResource(""));
        //这里要设置取消使用Local语言环境
        config.setLocalizedLookup(false);
        try {
            config.getTemplate(template);
        } catch (TemplateNotFoundException e) {
            File dir = new File("src/main/resources/");

            if (dir.exists() && new File(dir, template).exists()) {
                config.setDirectoryForTemplateLoading(dir);
                config.getTemplate(template);
            } else {
                throw e;
            }
        }
        return config;
    }


    private File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = javaPackage.replace('.', '\\');
        File packagePath = new File(outDirFile, packageSubPath);
        return new File(packagePath, javaClassName + ".java");
    }

    public static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

}


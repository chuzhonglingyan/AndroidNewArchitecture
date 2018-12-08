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
            generateService(outDirFile, packageName, modelName);
            generateRepository(outDirFile, packageName, modelName);
            generateViewModel(outDirFile, packageName, modelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateContact(File outDirFile, String basePackageName, String modelName) throws Exception {
        Configuration config = getConfiguration("contract.ftl");
        Template javaFileFtl = config.getTemplate("contract.ftl");
        generate(javaFileFtl, outDirFile,basePackageName, basePackageName+".contract", modelName,"contract");
    }

    public void generateService(File outDirFile, String basePackageName, String modelName) throws Exception {
        Configuration config = getConfiguration("service.ftl");
        Template javaFileFtl = config.getTemplate("service.ftl");
        generate(javaFileFtl, outDirFile, basePackageName,basePackageName+".net.service", modelName,"service");
    }

    public void generateRepository(File outDirFile, String basePackageName,String modelName) throws Exception {
        Configuration config = getConfiguration("repository.ftl");
        Template javaFileFtl = config.getTemplate("repository.ftl");
        generate(javaFileFtl, outDirFile,basePackageName, basePackageName+".repository", modelName,"repository");
    }


    public void generateViewModel(File outDirFile, String basePackageName,String modelName) throws Exception {
        Configuration config = getConfiguration("viewmodel.ftl");
        Template javaFileFtl = config.getTemplate("viewmodel.ftl");
        generate(javaFileFtl, outDirFile,basePackageName, basePackageName+".viewmodel", modelName,"ViewModel",false);
    }

    private void generate(Template javaFileFtl, File outDirFile,String basePackageName, String packageName, String modelName,String  tagName ) throws Exception {
        generate( javaFileFtl,  outDirFile, basePackageName,  packageName,  modelName,  tagName, true);
    }
    private void generate(Template javaFileFtl, File outDirFile,String basePackageName, String packageName, String modelName,String  tagName,boolean flag) throws Exception {
//        // 创建数据模型
        String clazzName="";
        if (flag){
            clazzName = tableNameConvertUpperCamel(modelName)+tableNameConvertUpperCamel(tagName);
        }else {
            clazzName = tableNameConvertUpperCamel(modelName)+tagName;
        }

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        root.put("author", GenerateClass.AUTHOR);
        root.put("date", GenerateClass.DATE);
        root.put("describe", "接口约束");
        root.put("modelNameUpperCamel", tableNameConvertUpperCamel(modelName));
        root.put("basePackageName",basePackageName);

        File file = toJavaFilename(outDirFile, packageName, clazzName);
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


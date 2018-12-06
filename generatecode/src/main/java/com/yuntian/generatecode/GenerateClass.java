package com.yuntian.generatecode;

import com.google.common.base.CaseFormat;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import static com.yuntian.generatecode.ProjectConstant.BASE_PACKAGE;
import static com.yuntian.generatecode.ProjectConstant.CONTRACT_PACKAGE;

public class GenerateClass {


    public static final String JAVA_PATH = "\\src\\main\\java"; //java文件路径

    public static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    public static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "\\src\\main\\resources\\generator\\template";//模板位置

    public static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    public static final String PACKAGE_PATH_CONTRACT = packageConvertPath(CONTRACT_PACKAGE);//生成的Contract存放路径

    public static final String AUTHOR = "chulingyan";//@author
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());//@date


    public static String getProjectPath(String moduleName) {
        return PROJECT_PATH.replace("\\generatecode", "") + "\\" + moduleName;
    }

    public static String getJavaPath(String moduleName) {
        return GenerateClass.getProjectPath(moduleName) + GenerateClass.JAVA_PATH;
    }

    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        //这里要设置取消使用Local语言环境
        cfg.setLocalizedLookup(false);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        return cfg;
    }


    public static void generateCode(String moduleName, String modelName) {
        if (StringUtils.isNoneBlank(moduleName) && StringUtils.isNoneBlank(modelName)) {
            genContact(moduleName, modelName);
        }
    }

    public static void genContact(String moduleName, String modelName) {
        try {
            Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("author", AUTHOR);
            data.put("date", DATE);
            data.put("describe", "接口约束");
            String modelNameUpperCamel = tableNameConvertUpperCamel(modelName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(getJavaPath(moduleName) + CONTRACT_PACKAGE , modelNameUpperCamel + "Contact.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("contract.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Contact.java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成Contact失败", e);
        }
    }




    private static File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = javaPackage.replace('.', '/');
        File packagePath = new File(outDirFile, packageSubPath);
        return new File(packagePath, javaClassName + ".java");
    }


    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }


    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
}

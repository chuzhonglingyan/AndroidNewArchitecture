package com.yuntian.generatecode;

/**
 * @author chulingyan
 * @time 2018/12/06 20:39
 * @describe
 */
public class ProjectConstant {

    public static final String BASE_PACKAGE = "com.yuntian.androidnewarchitecture";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
    public static final String BASE_DIR= "\\com\\yuntian\\androidnewarchitecture\\";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）

    public static final String CONTRACT_PACKAGE = BASE_DIR + "contract\\";//生成的contract所在包

}

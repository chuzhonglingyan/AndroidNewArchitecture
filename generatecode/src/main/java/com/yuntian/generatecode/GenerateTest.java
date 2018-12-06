package com.yuntian.generatecode;

import org.junit.Test;

import java.io.File;

import static com.yuntian.generatecode.ProjectConstant.BASE_PACKAGE;

/**
 * @author   chulingyan
 * @time     2018/12/7  0:11
 * @see {https://www.jianshu.com/p/ff0c265d7623}
 */

public class GenerateTest {





    @Test
    public void generateCode() {
        try {
            Generator daoGenerator = new Generator();
            daoGenerator.generate(new File(GenerateClass.getJavaPath("app")), BASE_PACKAGE,"good");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

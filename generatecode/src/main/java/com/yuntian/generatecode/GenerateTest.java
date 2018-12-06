package com.yuntian.generatecode;

import org.junit.Test;

import java.io.File;

import static com.yuntian.generatecode.ProjectConstant.BASE_PACKAGE;
import static com.yuntian.generatecode.ProjectConstant.CONTRACT_PACKAGE;

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

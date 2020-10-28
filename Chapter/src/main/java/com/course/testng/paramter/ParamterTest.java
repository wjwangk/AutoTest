package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 参数化测试的数据来源于 resources 下的paramter.xml文件
 */
public class ParamterTest {

    @Test
    @Parameters({"name","age"})
    public void test1(String name ,int age){
        System.out.println("name="+name+";  age="+age);
    }
}

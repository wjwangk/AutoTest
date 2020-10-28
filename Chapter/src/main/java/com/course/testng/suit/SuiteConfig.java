package com.course.testng.suit;

import org.testng.annotations.*;

/**
 * 这个类用于所有套件的配置
 */
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before Suite运行啦");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after Suite运行啦");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }
}

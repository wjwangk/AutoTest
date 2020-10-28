package com.course.testng.IgnoreTest;

import org.testng.annotations.Test;
/*
忽略测试：
忽略执行方法
 @Test(enabled = false)
 */

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 执行");
    }

    @Test(enabled = false)  // @Test后面加上(enabled = false)，就忽略这条case，不会执行了
    public void ingnore2(){
        System.out.println("ingnore2 执行");
    }
    @Test(enabled = true)      //默认为 true,所以可以不写，也会执行
    public void ingnore3(){
        System.out.println("ignore3 执行");
    }
}

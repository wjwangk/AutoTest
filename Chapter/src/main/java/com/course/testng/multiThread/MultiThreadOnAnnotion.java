package com.course.testng.multiThread;

import org.testng.annotations.Test;

//多线程测试，注解方式实现
public class MultiThreadOnAnnotion {

    @Test(invocationCount = 10,threadPoolSize = 3) //用10个线程来执行
    public void test1(){
        System.out.println(1);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

}

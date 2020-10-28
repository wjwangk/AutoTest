package com.course.testng.suit;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000) //单位为毫秒. 意思是设置超时时间是3000ms
    public void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }
    @Test(timeOut = 3000) //单位为毫秒. 意思是设置超时时间是3000ms,实际睡了5000ms,肯定会超时
    public void testFailed() throws InterruptedException{
        Thread.sleep(5000);
    }


}

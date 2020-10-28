package com.course.testng.ExpectedException;

import org.testng.annotations.Test;

/*
什么时候用
期望结果为某个异常的时候
例如：我们传入了某些不合法的参数，程序抛出了异常
也就是说我们的预期结果就是这个异常
 */
public class ExpectedException {

    //这是一个测试结果会失败的异常
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    //这是一个成功的异常

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的异常测试");
        throw new RuntimeException();
    }
}

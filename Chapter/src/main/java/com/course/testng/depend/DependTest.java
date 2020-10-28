package com.course.testng.depend;

import org.testng.annotations.Test;

/**依赖测试：
 * 当被执行的依赖失败，就不在执行后面的方法。例如：支付前需要先登录，
 * 当用户登录失败了，就不会执行支付操作
 */
public class DependTest {

    @Test
    public void test1(){
        System.out.println("test1 run");

    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run");
    }
}

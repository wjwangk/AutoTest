package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 从配置文件中获取内容，并不是在代码中写死
 */
public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;

    //测试前获取配置文件
    @BeforeTest
    public void beforTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);  //配置文件的名称和编码
        url=bundle.getString("test.url");  //获取配置文件中的字段
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中获取拼接测试的url，域名+路径
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);

        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }

}

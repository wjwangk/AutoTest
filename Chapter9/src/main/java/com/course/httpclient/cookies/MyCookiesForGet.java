package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 功能说明：获取get请求的cookies
 * 注意：从配置文件中获取内容，并不是在代码中写死
 */
public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    //用来存储cookie信息的变量。  把公共变量抽取出来作为全局变量，这样每个方法中都可以使用 this.store 调用
    private CookieStore store;

    //测试前获取配置文件
    @BeforeTest
    public void beforTest(){
        //获取配置文件的名称和编码，getBundle中写的文件名称必须和 resources 下的一致，另一个要注意字符编码
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");  //获取配置文件application中的 url 字段,用哪个，key就等于哪个
    }

    /** 访问 Chapter7 中 startupWithCookies.json 中的第1条
    【 "description":"这是一个待cookies返回信息的get请求",】
     */
    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中获取拼接测试的url，域名+路径
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //测试逻辑代码书写，固定写法，和 demo下的 类中代码一样
        HttpGet get = new HttpGet(testUrl);

        //httpClient 本身没有获取cookies信息的能力，需要用 dafaultHttpClient
        DefaultHttpClient client =new  DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"utf-8");  //获取response 结果
        System.out.println(result);

       // 获取cookies信息
         this.store = client.getCookieStore();
        List<Cookie> cookiesList = store.getCookies();

        for(Cookie cookie: cookiesList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name="+name + ";  cookie value="+value);

        }

    }


    /**
     * 访问 Chapter7 中 startupWithCookies.json 中的第2条
     * [ "description":"这是个带cookies 信息的get请求",]
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws   IOException{
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();

     // 设置cookies信息
        httpClient.setCookieStore(this.store);

        //获取json中的返回
        HttpResponse httpResponse = httpClient.execute(get);



        //获取响应的状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode ="+ statusCode);

        //获取状态成功后，返回结果
        if (statusCode ==200){
            String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(result);
        }
    }

}

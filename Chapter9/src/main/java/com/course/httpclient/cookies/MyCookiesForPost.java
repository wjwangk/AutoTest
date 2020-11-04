package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 获取post请求的cookies
 */
public class MyCookiesForPost {
// 以下代码 一直到 testGetCookies()方法完 都是复制自 MyCookiesForGet类
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
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");  //获取response 结果
        System.out.println(result);

        // 获取cookies的信息
        this.store = client.getCookieStore();
        List<Cookie> cookiesList = store.getCookies();

        for (Cookie cookie : cookiesList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name=" + name + ";  cookie value=" + value);

        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testUrl = this.url + uri ;

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","wangwu");
        param.put("age","10");
        //设置请求头信息 header信息
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"uft-8");
        post.setEntity(entity);
        //声明一个对象来进行相应结果存储
        String result;
        //设置cookies 信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
         result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期,具体步骤如下
        //将返回的响应结果字符串转化成为 json 对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String success =(String) resultJson.get("wangwu");
        String status = (String) resultJson.get("status");
        //判断实际结果与预期结果的一致性
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);
    }

}

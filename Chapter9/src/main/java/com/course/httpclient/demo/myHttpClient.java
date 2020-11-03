package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * httpclient:模拟http客户端的一种技术，可以向服务端发送get、post请求
 */
public class myHttpClient {

    @Test
    public void test1() throws IOException {
        //用来存放结果
        String result;
        HttpGet get = new HttpGet("http://localhost:8888/getcookies");
        //这个是用来执行get方法的
        HttpClient client = HttpClients.createDefault();
        HttpResponse response=client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}

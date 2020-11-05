package com.course.server;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getcookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest  装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies 信息成功";
    }


}

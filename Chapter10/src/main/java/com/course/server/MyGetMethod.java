package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    /**
     * 返回一个带cookies信息的get请求
     * @param response
     * @return
     */
    @RequestMapping(value = "/getcookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "Get")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest  装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies 信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端传递 cookies信息",httpMethod = "Get")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookie=request.getCookies();   //得到的是一个数组，数组内容为 key,value形式
        if(Objects.isNull(cookie)){
            return "没有携带cookies信息";
        }
        for(Cookie cookies:cookie)
            if(cookies.getName().equals("login") && cookies.getValue().equals("true")){
                 return "带cookies的get请求成功";
            }
        return "请携带cookies信息";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     *第一种实现方式： url: key=value&key=value
     */
    @RequestMapping(value = "/get/param",method = RequestMethod.GET)
    @ApiOperation(value = "带参数的get请求实现方式一",httpMethod = "Get")
    public Map<String,Integer> getPara(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> map = new HashMap<>();
        map.put("西装",100);
        map.put("衬衫",200);
        map.put("鞋子",500);

        return map;
    }

    /**
     * 第二种实现方式： url=  ip:port/get/with/param/10/20
     */

    @RequestMapping("/my/get/param/{start}/{end}")
    @ApiOperation(value = "带参数的get请求实现方式二",httpMethod = "Get")
    public Map myGetParam(@PathVariable Integer start,
                          @PathVariable Integer end){
        Map<String,Integer> map = new HashMap<>() ;
        map.put("T恤",99);
        map.put("巧克力",100);
        map.put("核桃",200);
        return map;
    }


}

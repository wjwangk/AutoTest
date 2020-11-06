package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我所有的post请求")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量时用来装我们cookies信息的
    private static Cookie cookie;
   //用户登录成功，获取到cookies,然后再访问其他接口获取到列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(@RequestParam(value = "userName",required = true) String name,    //参数后加上value就是传参值，required = true表示必传
                      @RequestParam(value = "passWord",required = true) String password,   //注意：参数写成 @RequestParam，那么请求参数类型就是 params
                      HttpServletResponse response){
        if(name.equals("zhangsan") && password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }

      return "用户名或者密码错误";
    }
}

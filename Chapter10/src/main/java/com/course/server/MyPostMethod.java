package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我所有的post请求")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量时用来装我们cookies信息的
    private static Cookie cookie;
   //用户登录成功，获取到cookies,
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

    //以上方法获取到cookies后，用管理员账号 zhangsan/123456登录后，可以访问用户列表信息
    @RequestMapping(value = "/getUerList",method = RequestMethod.POST)
    @ApiOperation(value = "这是获取用户列表的接口",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        //获取cookies
        Cookie[] cookie = request.getCookies();
        User user = new User();
        //判断cookies 正确时，使用正确的管理员账号和密码，可以的用户列表信息
        for(Cookie cookie1:cookie){
            //验证cookie是否合法
            if(cookie1.getName().equals("login") && cookie1.getValue().equals("true")){
                //验证管理员登录
                if(u.getUserName().equals("zhangsan") && u.getPassWord().equals("123456")){
                    user.setName("lisi");
                    user.setAge(10);
                    user.setSex("man");
                     return user.toString();
                }
            }
        }
        return "参数错误";
    }
}



package com.course.bean;

import lombok.Data;

@Data
public class User {
    private String name;   //用户列表中的姓名
    private String sex;
    private int age;
    private String userName;   //管理员账号
    private String passWord;  //管理员密码
}

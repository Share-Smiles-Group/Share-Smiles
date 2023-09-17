package com.sharesmiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharesmiles.dto.LoginRequest;
import com.sharesmiles.model.User;
import com.sharesmiles.service.UserService;

// Controller类作为应用的HTTP层，直接与客户端交互，接收HTTP请求，调用服务层的方法处理业务逻辑，然后返回响应。
@RestController
// Spring Web的注解，是一个RESTful的控制器。处理HTTP请求，返回JSON或其他RESTful格式的响应。
@RequestMapping("/api/user")
// 所有在这个控制器中定义的请求映射（Request Mapping）都将以/api/users为基础路径。
public class UserController {
    @Autowired
    // 一个注解，自动装配依赖关系
    // 使用 @Autowired 注解，告诉Spring: "我需要一个‘UserService’实例，请在你的容器内找到它并为我注入这个字段"
    private UserService userService;

    // 以POST方法发送到/api/users/register的HTTP请求
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // 以POST方法发送到/api/users/login的HTTP请求
    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest request) {
        return userService.login(request.getUsername(), request.getPassword());
    }
}

package com.alibaba.controller;

import com.alibaba.bean.Result;
import com.alibaba.bean.User;
import com.alibaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    @ResponseBody
    public Result regist(User user, String code, RedirectAttributes attributes) {
        return userService.regist(user, code, attributes);
    }

    /**
     * 登录
     *
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(User user) {
        return userService.login(user);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(String username, String password) {
        return userService.update(username, password);
    }

    @PostMapping(value = "/findemail")
    @ResponseBody
    public Result find(String email) {
    return userService.find(email);
    }
}


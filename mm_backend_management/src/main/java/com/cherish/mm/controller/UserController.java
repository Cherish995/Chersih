package com.cherish.mm.controller;

import com.cherish.mm.constants.Constants;
import com.cherish.mm.entity.Result;
import com.cherish.mm.pojo.User;
import com.cherish.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 处理登录请求
     *
     * @param
     * @param
     * @throws IOException
     */
    @RequestMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        try {
            // 获取请求参数

            // 调用业务层
            User loginUser = userService.check(user);

            // 登录成功,保存登录状态
            session.setAttribute(Constants.LOGIN_ON_USER, loginUser);

            // 响应用户名
            return new Result(true, "登陆成功", user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session) {
        try {
            // 清楚登录状态
            session.removeAttribute(Constants.LOGIN_ON_USER);
            return new Result(true, "登出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "登出失败");
        }
    }
}

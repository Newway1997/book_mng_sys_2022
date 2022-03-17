package com.bookmngsys.controller;

import com.bookmngsys.domain.UserDto;
import com.bookmngsys.entity.User;
import com.bookmngsys.service.ReaderService;
import com.bookmngsys.service.UserService;
import com.bookmngsys.tool.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Newway
 */
@Api("用户管理")
@RequestMapping("/api/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ReaderService readerService;

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public Response login(@RequestBody User user, HttpSession session) {
        User result = userService.login(user);
        //登陆成功
        if (result != null) {
            if (result.getStatus() == 1) {
                session.setAttribute("USER_SESSION", UserDto.convertToUserDto(result));
                return new Response().success();
            } else {
                return new Response().failure("账号已被封禁");
            }

        }
        return new Response().failure("账号或密码错误");
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public Response register(@RequestBody User user) {
        //登陆成功
        if (!userService.checkUserIsExist(user.getUsername())) {
            if (userService.register(user)) {
                return new Response().success();
            } else {
                return new Response().failure("注册失败");
            }
        }
        return new Response().failure("账号已存在");
    }


    @ApiOperation("更新密码")
    @PostMapping(value = "/updatePass")
    @ResponseBody
    public Response updatePass(@RequestBody Map<String, String> map, @RequestAttribute("user") UserDto userDto) {
        //登陆成功
        User user = userService.getNormalUser(userDto.getId());
        if (!user.getPassword().equals(map.get("oldPass"))) {
            return new Response().failure("请输入正确的原密码");
        } else {
            userService.updatePass(user.getId(), map.get("newPass"));
            return new Response().success();
        }
    }

    @ApiOperation("登出")
    @GetMapping(value = "/logout")
    @ResponseBody
    public Response logout(HttpSession session) {
        session.removeAttribute("USER_SESSION");
        return new Response().success();
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping(value = "/checkUser")
    @ResponseBody
    public Response checkUser(@RequestAttribute("user") UserDto userDto) {
        User result = userService.getNormalUser(userDto.getId());
        return new Response().success(UserDto.convertToUserDto(result));
    }


    @ApiOperation("启用用户")
    @GetMapping("/enableUser")
    @ResponseBody
    public Response enableUser(@RequestParam int id, @RequestAttribute("user") UserDto userDto) {
        //按isbn借阅图书
        if (!userDto.isAdmin()) {
            return new Response().failure("当前用户无权限");
        }
        userService.enableUser(id);
        return new Response().success();
    }

    @ApiOperation("禁用用户")
    @GetMapping("/disableUser")
    @ResponseBody
    public Response disableUser(@RequestParam int id, @RequestAttribute("user") UserDto userDto) {
        //按isbn借阅图书
        if (!userDto.isAdmin()) {
            return new Response().failure("当前用户无权限");
        }
        userService.disableUser(id);
        return new Response().success();
    }
}

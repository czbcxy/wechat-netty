package com.czb.wechat.controller;

import com.czb.wechat.pojo.Users;
import com.czb.wechat.service.UserService;
import com.czb.wechat.utils.JSONResult;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 今天使用spring boot 中@requestBody注解进行接受参数，出现异常，415 Unsupported Media Type，
 * 研究后发现，使用@requestBody注解的话， 前端传递参数时需要JSON格式的参数，
 * 而且Content-Type为：application/json;charset=UTF-8 格式，
 * 另外如果@requestBody 和 @requestParam 同时使用的话，@requestParam的参数需要拼接到URL上才可以拿到。
 * 以前一直以为在SpringMVC环境中，@RequestBody接收的是一个Json对象，一直在调试代码都没有成功，
 * 后来发现，其实 @RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。
 * 然而在ajax请求往往传的都是Json对象，后来发现用 JSON.stringify(data)的方式就能将对象变成字符串。
 * 同时ajax请求的时候也要指定dataType: "json",contentType:"application/json"
 * 这样就可以轻易的将一个对象或者List传到Java端，使用@RequestBody即可绑定对象或者List.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult test() {
        return JSONResult.ok();
    }

    @RequestMapping(value = "regOrlogin", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult UsernameIsExist(@RequestBody  Users users) {

        System.out.println(users.toString());
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword()))
            return JSONResult.errorMsg("用户名或密码不能为空");
        try {
            return userService.SelectUsernameIsExist(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONResult.errorMsg("500");
    }

    @RequestMapping(value = "updateNickname",method = RequestMethod.POST)
    public JSONResult updateNickname(@RequestBody Users user){
        System.out.println(user.toString());
        return userService.updateNicknameById(user);
    }

}

package com.suju.vote.controller;

import com.suju.vote.utils.response.RespBean;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/web/hello")
    public RespBean helloWeb() {
        return RespBean.ok("你好，通过网页端登录的用户");
    }

    @GetMapping("/wx/hello")
    public RespBean helloWeixin() {
        return RespBean.ok("你好，通过微信小程序端登录的用户");
    }
}

package com.moon.dlog.controller;

import com.moon.dlog.domain.R;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description
 * @Author linyl
 * @Date 2022-12-19 20:06
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}

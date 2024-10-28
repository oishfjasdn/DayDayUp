package org.example.security.demos.web.controller;

import org.example.security.demos.web.domain.ResponseResult;
import org.example.security.demos.web.domain.User;
import org.example.security.demos.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
    @GetMapping("/accountout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}


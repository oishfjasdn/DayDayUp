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

    @PostMapping("/userlogin")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/userout")
    public ResponseResult logout() {
        return loginService.logout();
    }

    @PostMapping("/useregist")
    public ResponseResult register(@RequestBody User user) {
        return loginService.register(user);
    }

    @PostMapping("/userdelete")
    public ResponseResult deleteUser(@RequestBody User user, @RequestHeader("token") String token) throws Exception {
        return loginService.deleteUser(user,token);
    }
}


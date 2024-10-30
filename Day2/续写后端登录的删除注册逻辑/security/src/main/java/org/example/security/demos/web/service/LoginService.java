package org.example.security.demos.web.service;

import org.example.security.demos.web.domain.ResponseResult;
import org.example.security.demos.web.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult register(User user);

    ResponseResult deleteUser(User user,String token) throws Exception;
}

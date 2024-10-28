package org.example.security.demos.web.service;

import org.example.security.demos.web.domain.LoginUser;
import org.example.security.demos.web.domain.ResponseResult;
import org.example.security.demos.web.domain.User;
import org.example.security.demos.web.utils.JwtUtil;
import org.example.security.demos.web.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServicelmp implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        //认证通过后，使用userId生成jwt,jwt存入结果集返回
        //将Authenticate对象中的Principal强转为LoginUser
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = String.valueOf(loginUser.getUser().getId());
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //将完整的用户信息存入redis，userId作为key(jwt是前端获取到的，进行后续的jwt转userId)
        redisCache.setCacheObject("login:" + userId, loginUser);//此处保存的loginUser包含用户信息
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //从SecurityContextHolder中获取用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userId从redis中删除信息
        Long userId = loginUser.getUser().getId();
        System.out.println(userId);
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "退出成功");
    }

}


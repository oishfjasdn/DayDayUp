package org.example.security.demos.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.security.demos.web.domain.LoginUser;
import org.example.security.demos.web.domain.ResponseResult;
import org.example.security.demos.web.domain.User;
import org.example.security.demos.web.mapper.UserMapper;
import org.example.security.demos.web.utils.JwtUtil;
import org.example.security.demos.web.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServicelmp implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseResult login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        if (userMapper.selectOne(queryWrapper).getDelFlag() == 1)
            return new ResponseResult(400, "用户已被删除");
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
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "退出成功");
    }

    @Override
    public ResponseResult register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        if (userMapper.selectOne(queryWrapper) == null) {
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            userMapper.insert(user);
            User loginuser = userMapper.selectOne(queryWrapper);
            String userId = String.valueOf(loginuser.getId());
            String jwt = JwtUtil.createJWT(userId);
            redisCache.setCacheObject("login:" + userId, new LoginUser(loginuser));
            return new ResponseResult(200, "注册成功", jwt);
        }
        return new ResponseResult(400, "用户已存在");
    }

    @Override
    public ResponseResult deleteUser(User user, String token) throws Exception {
        String id = JwtUtil.parseJWT(token).getSubject();
        System.out.println(id);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        if (userMapper.selectOne(queryWrapper).getUserName().equals(user.getUserName())) {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);  // 设置条件
            updateWrapper.set("del_flag", Long.valueOf(1));  // 设置要更新的字段和值
            userMapper.update(null, updateWrapper);
            return new ResponseResult(200, "删除成功");

        } else return new ResponseResult(400, "删除失败");
    }


}


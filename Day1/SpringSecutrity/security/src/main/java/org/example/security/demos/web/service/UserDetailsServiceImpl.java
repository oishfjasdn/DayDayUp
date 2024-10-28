package org.example.security.demos.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.security.demos.web.domain.LoginUser;
import org.example.security.demos.web.domain.User;
import org.example.security.demos.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//根据username查用户信息
        LambdaQueryWrapper<User> ulqw = new LambdaQueryWrapper<>();
        ulqw.eq(User::getUserName, username);
        User user = userMapper.selectOne(ulqw);
        //获取到对象为空,抛出异常
        if (Objects.isNull(user)) {
            //后面交给authenticationEntryPoint的实现类处理，抛出“认证失败异常”
            throw new RuntimeException("用户名或者密码错误");
        }
        return new LoginUser(user);
    }
}

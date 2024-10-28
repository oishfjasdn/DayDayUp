package org.example.security;

import org.example.security.demos.web.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class MApperTest {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test(){
        System.out.println("hello");
        System.out.println(mapper.selectList(null));
    }
    @Test
    public void bCryptPasswordEncoderTest(){
        //每次加密结果中只有前面的部分一样：$2a$10$，后面的部分每次都不一样，每一次的密文都不一样
        String encode = bCryptPasswordEncoder.encode("1234");
        String encode1 = bCryptPasswordEncoder.encode("1234");
        //比对的是明文，只要密文的明文和比对的相同则返回true
        System.out.println(bCryptPasswordEncoder.matches("1234",
                "$2a$10$lYKttuZoa.t25jEtJz0KBeA27vloCvhKMwoWHw/qMopi1gsRkRk9."));
        System.out.println(encode);
        System.out.println(encode1);
    }

}

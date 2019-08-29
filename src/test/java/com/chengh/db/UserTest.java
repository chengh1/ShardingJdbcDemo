package com.chengh.db;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.chengh.db.entity.User;
import com.chengh.db.mapper.UserMapper;
import com.chengh.db.util.IdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ShardingJdbcAplication.class)
@WebAppConfiguration
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Resource
    IdGenerator idGenerator;

    @Test
    public void save() {
        for (Integer i = 0; i < 100; i++) {
            User user = new User();
            user.setName("chengh" + i);
            user.setSex(i % 2);
            user.setPhone("12345678910");
            user.setEmail("123@qq.com");
            user.setPassword("123456");
            userMapper.save(user);
        }
    }

    @Test
    public void getByIds() {
        System.out.println(JSON.toJSONString(
                userMapper.getById(123L)));
    }

    @Test
    public void getGeneratorId() {
        for(int i=0;i<50;i++) {
            System.out.println(idGenerator.generateKey().toString());
        }
    }
}

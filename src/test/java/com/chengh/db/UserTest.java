package com.chengh.db;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.chengh.db.entity.User;
import com.chengh.db.mapper.GoodsMapper;
import com.chengh.db.mapper.UserMapper;
import com.chengh.db.util.IdGenerator;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ShardingJdbcAplication.class)
@WebAppConfiguration
public class UserTest {

    @Resource
    UserMapper userMapper;

    @Resource
    IdGenerator idGenerator;

    @Resource
    GoodsMapper goodsMapper;

    @Test
    public void getGeneratorId() {
        for (int j = 0; j < 100; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println(idGenerator.generateKey().toString());
                }
            }).start();
        }
    }

    @Test
    public void saveUser() {
        for (Integer i = 0; i < 100; i++) {
            User user = new User();
            user.setName("chengh" + i);
            user.setSex(i % 2);
            user.setPhone("12345678910");
            user.setEmail("123456@qq.com");
            user.setPassword("123456");
            userMapper.save(user);
        }
    }

    @Test
    public void getUserByd() {
        System.out.println(JSON.toJSONString(userMapper.getById(113551716062330880L)));
    }

    @Test
    public void getUserByds() {
        System.out.println(JSON.toJSONString(userMapper.getByIds(Lists.newArrayList(12L, 33L))));
    }

    @Test
    public void getGoodsById() {
        System.out.println(JSON.toJSONString(goodsMapper.getById(3L)));
    }
}

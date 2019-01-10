package com.wang.userservice;

import com.wang.userservice.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	@Resource
	private RedisUtil redisUtil;

	@Test
	public void testRedisUtil() {

		redisUtil.set("test00000000", 123123);
	}

}


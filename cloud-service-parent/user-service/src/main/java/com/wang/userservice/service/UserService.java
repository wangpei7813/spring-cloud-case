package com.wang.userservice.service;

import com.wang.common.constant.RedisKey;
import com.wang.userservice.async.UserInfoAsyncQueue;
import com.wang.userservice.async.UserLoader;
import com.wang.userservice.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private RedisUtil redisUtil;

    public String login(String userId, String password) throws Exception {

//		HashOperations<String, Object, Object> cityHash = redisTemplate.opsForHash();

        //1.先通过当前用户的缓存信息 userId 属于哪一个城市的
        //城市进行维度的划分---->beijing
        // select count(1) from user_beijing where user_id = ? and password = ?

        //2. 通过用户ID和密码进行登录验证
        if ("admin".equals(userId) && "123456".equals(password)) {

            //3. token生成规则
            //3.1 token需要进行加密
            String token = userId + "_" + System.currentTimeMillis();
            //3.2 再次去对token进行一个处理
            String cacheKey = "x-token" + ":" + userId;

            redisUtil.set(cacheKey, token, RedisKey.TOKEN_EXPIRE_TIME);

            //5. 异步的去加载用户其他相关信息
            UserInfoAsyncQueue.submit(new UserLoader(userId));

            return token;
        } else {
            //用户名或密码错误
            return "100001";
        }
    }


}

package com.wang.userservice.async;

import com.wang.common.util.FastJsonConvertUtil;
import com.wang.userservice.listener.ApplicationFactory;
import com.wang.userservice.utils.RedisUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 异步加载用户信息
 * @author: wp
 * @date: 2019/1/10 10:03
 */
public class UserLoader implements Runnable {

    private String userId;

    public UserLoader(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {

        RedisUtil redisUtil = ApplicationFactory.getBean(RedisUtil.class);

        //比如用户登录成功,去用户表里面获取用户的角色、权限、相关的描述信息
        //select * from user_info where userId = ?
        //大型互联公司 没有去做复杂join查询  都是单独进行查询[单表查询]
        String address = "北京市朝阳区酒仙桥中路";
        String phone = "12345678901";
        String role = "admin";
        String level = "10";

        Map<String, String> userInfos = new HashMap<>();
        userInfos.put("address", address);
        userInfos.put("phone", phone);
        userInfos.put("role", role);
        userInfos.put("level", level);
        redisUtil.set("user_info:" + userId, FastJsonConvertUtil.convertObjectToJSON(userInfos));
    }

}

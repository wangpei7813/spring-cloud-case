package com.wang.userservice.service;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	public String login(String userId, String password) throws Exception {
		
		HashOperations<String, Object, Object> cityHash = redisTemplate.opsForHash();
		
		//1.先通过当前用户的缓存信息 userId 属于哪一个城市的
		//城市进行维度的划分---->beijing  
		// select count(1) from user_beijing where user_id = ? and password = ?
		
		//2. 通过用户ID和密码进行登录验证
		if("admin".equals(userId) && "123456".equals(password)) {
			
			//3. token生成规则
			Date loginTime = new Date();
			Date exprieTime = DateUtils.addMinutes(loginTime, 30);
			String loginTimeStr = DateFormatUtils.format(loginTime, "yyyMMddHHmmss");
			String exprieTimeStr = DateFormatUtils.format(exprieTime, "yyyMMddHHmmss");
			//3.1 token需要进行加密
			String token = userId + "$" + loginTimeStr;
			//3.2 再次去对token进行一个处理
			//key存储规则: x-token  + ":"  + userId
			String cacheKey = "x-token" + ":" + userId;
			//value存储规则: token + ":" + exprieTime
			String cacheValue = token + ":" + exprieTimeStr;
			
			System.err.println("-----------:" + redisTemplate);
			
			//4. 找到所对应的hash集合
			//beijing_usertoken {10001:token1 , 10002:token2, ...} 
			cityHash.put("beijing:usertoken", cacheKey, cacheValue);

			//5. 异步的去加载用户其他相关信息
//			UserInfoAsyncQueue.submit(new UserLoader(userId));
			
			return token;
		} else {
			return "100001";	//用户名或密码错误
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.wang.userservice.api;

import com.wang.userservice.service.UserService;
import com.wang.userservice.utils.FastJsonConvertUtil;
import com.wang.userservice.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("password") String password) throws Exception {
        return userService.login(userId, password);
    }

	/*@Autowired
	private OrderFeignClient orderFeignClient;
	
	@RequestMapping(value="/getOrder")
	public String getOrder() throws Exception {
		String ret1 = orderFeignClient.getOrder("1");
		System.err.println(" ret1: " + ret1 );
		return "getOrder获取成功!----> "  + " ret1: " + ret1 ;
	}
	
	@RequestMapping(value="/getOrderList")
	public String getOrderList() throws Exception {
		String ret2 = orderFeignClient.getOrderList();
		System.err.println(" ret2: " + ret2);
		return "getOrderList获取成功!----> "  + " ret2: " + ret2 ;
	}*/

	@GetMapping("/test")
    public String test(@RequestHeader("token") String token, String userId) {
	    log.info("token = {}", token);
        Object o = redisUtil.get("user_info:" + userId);
        return FastJsonConvertUtil.convertObjectToJSONWithNullValue(o);
    }
}

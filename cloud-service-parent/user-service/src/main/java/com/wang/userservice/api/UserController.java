package com.wang.userservice.api;

import com.wang.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

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


}

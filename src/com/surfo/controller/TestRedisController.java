package com.surfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfo.service.RedisService;

@Controller
@RequestMapping("testCon")
public class TestRedisController {
	@Autowired
	private RedisService redisService;
	private static final Logger LOGGER = Logger.getLogger(TestRedisController.class);
	@RequestMapping("/showsindex")
	public String showsindex(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
		LOGGER.info("查询用户： + userId");
		//AcctUser userInfo = userService.load(userId);
		//modelMap.addAttribute("userInfo", userInfo);
		return "/test/test";
	}
	
	@RequestMapping("dotest")
	@ResponseBody
	public Map<String, Object> dotest(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String pam =request.getParameter("pam");
		LOGGER.info("*****************start****************");
		redisService.leftin(pam);
		System.out.println(redisService.rigthout("feng_key"));
		LOGGER.info("*****************end****************");
		Map m = new HashMap();
		m.put("eti", "成功");
		return m;
	}
}

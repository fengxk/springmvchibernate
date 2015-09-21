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

import com.surfo.entity.highchartpo.HighParam;
import com.surfo.service.HighService;
import com.surfo.service.RedisService;

@Controller
@RequestMapping("highcharts_con")
public class HighChartController {
	
	@Autowired
	private HighService highService;
	private static final Logger LOGGER = Logger.getLogger(TestRedisController.class);
	@RequestMapping("/showsindex/{param}")
	public String showsindex(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@PathVariable String param){
		return "/highcharts/high"+param;
	}
	
	
	@RequestMapping("/getdata")
	@ResponseBody
	public Map<String, Object> getdata(HttpServletRequest request,HttpServletResponse response,
			ModelMap model){
		Map<String,Object> map = new HashMap<String,Object>();
		
		HighParam hp = new HighParam();
		hp.setCategories(highService.getX_Axis());
		hp.setSeries(highService.getY_Axis());
		map.put("hp", hp);
		return map;
	}
	
	
}

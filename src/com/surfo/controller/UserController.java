package com.surfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfo.common.Constant;
import com.surfo.common.utils.MD5Util;
import com.surfo.entity.AcctUser;
import com.surfo.service.UserService;
import com.surfo.utils.Pager;

/**  
 * 创建时间：2015-4-8 下午13:31 
 * @author fengxk  
 * @version 2.2  
 * Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable String userId){
		LOGGER.info("查询用户： + userId");
		//AcctUser userInfo = userService.load(userId);
		//modelMap.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}
	
	@RequestMapping("/showInfos")
	public @ResponseBody Pager showUserInfos(Pager pager,ModelMap modelMap){
		LOGGER.info("查询用户全部用户");
		AcctUser acctUser = getLoginAccount();
		String hql="";
		if("admin".equals(acctUser.getUserName())){
			hql=" from AcctUser where 1=1 ";
		}else{
			hql=" from AcctUser where 1=1 and userName='"+acctUser.getUserName()+"'";
		}
		
		pager = userService.getAcctUserPageList(hql.toString(),new HashMap(),pager);
		return pager;
	}
	@RequestMapping("/main")
	public String main(ModelMap modelMap){
		LOGGER.info("显示主页面");
		//后台获取security保存的session中的用户信息
		
		//获取security的上下文
		SecurityContext securityContext = SecurityContextHolder.getContext();
		//获取认证对象
		Authentication authentication = securityContext.getAuthentication();
		//在认证对象中获取主体对象
		Object principal = authentication.getPrincipal();
		
		String username = "";
		if(principal instanceof UserDetails){
			username = ((UserDetails) principal).getUsername();
		}else {
			username = principal.toString();
		}
		modelMap.addAttribute("username", username);
		return "/user/main";
	}
	
	
	@RequestMapping("/index")
	public String manage(ModelMap modelMap){
		LOGGER.info("显示主页面");
		modelMap.addAttribute("msg", "manage");
		AcctUser acctUser = getLoginAccount();
		modelMap.addAttribute("userAttrName", acctUser.getUserName());
		return "/userIndex";
	}
	
	@RequestMapping(value="/save",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String saveOrUpdate(AcctUser acctUser){
		LOGGER.info("保存");
		String msg = Constant.OPTION_FAIL;
		if(null != acctUser){
			acctUser.setPassword(MD5Util.GetMD5Code(acctUser.getPassword()));
			userService.saveOrUpdateAcctUser(acctUser);
			msg = Constant.OPTION_SUCCESS;
		}
		return msg;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(@RequestBody Map<String,Integer> params){
		LOGGER.info("删除");
		userService.deleteById(String.valueOf(params.get("id")));
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", Constant.OPTION_SUCCESS);
		return map;
	}

	@RequestMapping("/getUserById")
	@ResponseBody
	public AcctUser getUserById(Integer id){
		AcctUser acctUser = userService.getAcctUserById(id.toString());
		return acctUser;
	}
	public AcctUser getLoginAccount(){
		//获取security的上下文
		SecurityContext securityContext = SecurityContextHolder.getContext();
		//获取认证对象
		Authentication authentication = securityContext.getAuthentication();
		//在认证对象中获取主体对象
		Object principal = authentication.getPrincipal();
		
		String username = "";
		if(principal instanceof UserDetails){
			username = ((UserDetails) principal).getUsername();
		}else {
			username = principal.toString();
		}
		
		AcctUser acctUser = userService.findByUserName(username);
		return acctUser;
	}
	
}


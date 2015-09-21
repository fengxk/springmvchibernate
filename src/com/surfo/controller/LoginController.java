package com.surfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfo.entity.AcctUser;

/**
 * @author fengxk
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Log log = LogFactory.getLog("LoginController");//Logger.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(@ModelAttribute AcctUser acctUser, @RequestParam(required = false) Boolean logout, Errors errors) {
		log.info("login");
		if (null != logout) {
			errors.reject("msg", "已经安全退出");
		}
		return "/login/login";
	}

	@RequestMapping("/toLogin")
	public void toLoging(HttpServletRequest request, HttpServletResponse response, String verifyCode, Locale locale, Model model) {
		log.info("login");
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				model.addAttribute("username", ((UserDetails) principal).getUsername());
			} else {
				model.addAttribute("username", principal);
			}
			response.sendRedirect("j_spring_security_check");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验验证码是否正确
	 * @param request
	 * @param response
	 * @param verifyCode
	 */
	@RequestMapping("/validateVerifyCode")
	@ResponseBody
	public Map<String, String> validateVerifyCode(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		String verifyCode = request.getParameter("verifyCode");
		log.info(verifyCode);
		Map<String, String> map = new HashMap<String, String>();
		if (null != request.getSession().getAttribute("rand") && request.getSession().getAttribute("rand").equals(verifyCode)) {
			map.put("status", "1");
		} else {
			map.put("status", "0");
		}
		map.put("status", "1");
		return map;

	}
}

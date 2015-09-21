package com.surfo.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.surfo.common.utils.VerifyCodeUtils;
import com.surfo.entity.AcctUser;

@Controller
@RequestMapping("manager")
public class ManagerController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/verifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			Map<String,Object> map = VerifyCodeUtils.getVerifyCode();
			request.getSession().setAttribute("rand", map.get("sRand"));
			 OutputStream os = response.getOutputStream();
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", os);
			os.flush();
			os.close();
			os = null;
			response.flushBuffer();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/index")
	@Secured("VOTE_CATEGORY_READ")
	public ModelAndView  index() {
		Map<String, List<AcctUser>> map = new HashMap<String, List<AcctUser>>();
		List<AcctUser> users = new ArrayList<AcctUser>();
		for (int i = 0; i < 5; i++) {
			AcctUser user = new AcctUser();
			user.setId(i);
			user.setUserName("用户——"+i);
			users.add(user);
		}
		map.put("users", users);
		return new ModelAndView("index",map);
	}
	
}

package com.surfo.Servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;





public class InitSys extends HttpServlet {
	private static final long serialVersionUID = 7254669949275106004L;
	private static Log logger = LogFactory.getLog(InitSys.class.getName());
	
	private static String WEB_INF_PATH = "";
	private static String WEB_ROOT_PATH = "";
	private static ServletContext CONSTANT_SERVLETCONTEXT ;
	
	public void destroy() {
		super.destroy(); 
	}

	
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	/***************����*************/
    	 CONSTANT_SERVLETCONTEXT = this.getServletContext();    
//    	logger.info("��Ŀ�е�WEB-INFĿ¼����"+(WEB_INF_PATH = config.getServletContext().getRealPath("/WEB-INF"))+"��");
//		logger.info("��Ŀ�еĸ�Ŀ¼����"+(WEB_ROOT_PATH = config.getServletContext().getRealPath("/"))+"��");
    	/***************���� ��ѯ���*************/
    	 logger.info("初始化注入redis工厂");
    	 
    	 logger.info("初始化注入成功");
    	 
    	 
    	 
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static String getWEB_INF_PATH() {
		return WEB_INF_PATH;
	}
	public static void setWEB_INF_PATH(String web_inf_path) {
		WEB_INF_PATH = web_inf_path;
	}
	public static String getWEB_ROOT_PATH() {
		return WEB_ROOT_PATH;
	}
	public static void setWEB_ROOT_PATH(String web_root_path) {
		WEB_ROOT_PATH = web_root_path;
	}
	public static ServletContext getCONSTANT_SERVLETCONTEXT() {
		return CONSTANT_SERVLETCONTEXT;
	}
	public static void setCONSTANT_SERVLETCONTEXT(
			ServletContext constant_servletcontext) {
		CONSTANT_SERVLETCONTEXT = constant_servletcontext;
	}



	
}

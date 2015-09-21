package com.surfo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ApplicationExceptionHandler extends RuntimeException implements HandlerExceptionResolver {
	private static final Log log = LogFactory.getLog("ApplicationExceptionHandler");
	private static final long serialVersionUID = 1L;
	
	public ApplicationExceptionHandler(){
		super();
	}

	public ApplicationExceptionHandler(String msg, Throwable cause){
		super(msg ,cause);
	}
	
	public ApplicationExceptionHandler(String msg){
		super(msg);
	}
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public String  handleException(Exception ex ,HttpServletRequest req){
//		System.out.println(".........error........"+ex.getMessage());
//		return ClassUtils.getShortName(ex.getClass())+ex.getMessage();
//	}

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object o , Exception ex) {
		// TODO Auto-generated method stub
		Map<String,Object> model = new HashMap<String,Object>() ;
		model.put("ex",ex) ;
		String viewName = ClassUtils.getShortName(ex.getClass()) ;
		viewName="error";
		StackTraceElement[] e =  ex.getStackTrace();
		log.error("****************************************");
		log.error(ex.fillInStackTrace());
		for(int i=0;i<e.length;i++){
			log.error(e[i]);
		}
		return new ModelAndView("/error/"+viewName,model);
	}
}

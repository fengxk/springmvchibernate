package com.surfo.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * Apr 15, 20152:14:38 PM
 * @author fengxk
 * @version 1.0
 */
public class PubMethod {
	
	// 判断是否为空。
	public static boolean isEmpty(String Value) {
		return (Value == null || Value.trim().equals("") || "null".equals(Value));
	}

	/*
	 * @function:判空 
	 */
	public static boolean isEmpty(List list) {
		if (list == null || list.size() == 0)
			return true;
		else
			return false;
	}

	/*
	 * @function:判空 
	 */
	public static boolean isEmpty(Set set) {
		if (set == null || set.size() == 0)
			return true;
		else
			return false;
	}

	/*
	 * @function:判空 
	 */
	public static boolean isEmpty(Map map) {
		if (map == null || map.size() == 0)
			return true;
		else
			return false;
	}

	// 判断是否为空。
	public static boolean isEmpty(Object Value) {
	//	if (Value == null )
		//--wangf--------------start
		if (Value == null || Value == "")
			return true;
		else
			return false;
	}
	// 判断是否为空。
	public static boolean isEmpty(Long obj) {
		if (obj == null || obj.longValue() == 0)
			return true;
		else
			return false;
	}
	
	// 判断是否为空。
	public static boolean isEmpty(Double obj) {
		if (obj == null || obj.doubleValue() == 0.0)
			return true;
		else
			return false;
	}
	public static boolean isEmpty2(Double obj) {
		if (obj == null)
			return true;
		else
			return false;
	}

	// 判断是否为空。
	public static boolean isEmpty(Object[] Value) {
		if (Value == null || Value.length == 0)
			return true;
		else
			return false;
	}
	
	
	// debug sql
	public static void debugHQL(org.apache.log4j.Logger logger,
			org.hibernate.Query queryObject, String[] params, Object[] values) {
			logger.info("HQL is :\n" + queryObject.getQueryString());
			logger.info("params:");
			if(params != null && params.length > 0)
				for (int index = 0; index < params.length; index++) {
					logger.info(params[index] + " = " + values[index] + ",");
				}
	}
	public static void debugSQL(org.apache.log4j.Logger logger,
			String sql) {
			logger.info("SQL is :\n" + sql);
	}
	public static void debugHQL(org.apache.log4j.Logger logger,
			String hql) {
			logger.info("HQL is :\n" + hql);
	}
	public static String SecondSwitchDateformat(String t){
		if(PubMethod.isEmpty(t)){
			t="0";
		}
		if(t.indexOf(".")>-1){
			t= t.split("\\.")[0];
		}
		int s = Integer.valueOf(t);
		String str="";
		int N = s/3600;       
		s = s%3600;       
		int K = s/60;      
		s = s%60;       
		int M = s; 
		if(N==0){
			str+="00:";
		}else if(N>0&&N<10){
			str+="0"+String.valueOf(N)+":";
		}else{
			str+=String.valueOf(N)+":";
		}
		
		if(K==0){
			str+="00:";
		}else if(K>0&&K<10){
			str+="0"+String.valueOf(K)+":";
		}else{
			str+=String.valueOf(K)+":";
		}
		 
		if(M==0){
			str+="00";
		}else if(M>0&&M<10){
			str+="0"+String.valueOf(M);
		}else{
			str+=String.valueOf(M);
		}
		return str;
	}
	public static String RanColor(){
		 String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		return r + g + b;             
	}
	
	/**
	 * 添加X轴 按时间点，或日期
	 * @param sb
	 * @param quota_mode
	 * @return
	 * @throws ParseException 
	 */
	public static String addCategory(String quota_mode,String starttime, String endtime) {
		StringBuffer sb = new StringBuffer();
		sb.append("<categories>");
		if("mode_hour".equals(quota_mode)){//按时
			for(int i=0;i<24;i++){
				sb.append("<category label='"+i+"' />");
			}
		}else{
			int mt=0;
			try {
				mt = DateUtils.getDaysDistance(starttime,endtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<mt+1;i++){
				sb.append("<category label='"+DateUtils.getLaterNDay(starttime, i)+"' />");
			}
		}
		sb.append("</categories>");
		return sb.toString();
	}
	/**
	 * 增加标题
	 * @return
	 */
	public static String addCaptionChart(String quota_mode,String starttime, String endtime){
		StringBuffer sb = new StringBuffer();
		int mt=0;
		try {
			mt = DateUtils.getDaysDistance(starttime,endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sb.append("<chart caption='' subcaption='' lineThickness='1' showValues='0' ");
		if(!"mode_hour".equals(quota_mode)){//按日期
			if(mt>13&&mt<25){
				sb.append(" labelStep='2' ");
			}else if(mt>=25){
				sb.append(" labelStep='3' ");
			}else{}
		}
		sb.append(" formatNumberScale='0' anchorRadius='2'   divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10'>");
		return sb.toString();
	}
	/**
	 * 增加结尾
	 * @return
	 */
	public static String addEndChart(){
		StringBuffer sb = new StringBuffer();
		sb.append("<styles><definition><style name='CaptionFont' type='font' size='12'/></definition><application><apply toObject='CAPTION' styles='CaptionFont' /><apply toObject='SUBCAPTION' styles='CaptionFont' /></application></styles></chart>");
		return sb.toString();
	}
}

package com.surfo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils {
	/**
	 * 得到今天天的时间
	 */
	public static String getToday(){
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");//
		return today;
	}
	/**
	 * 得到昨天的时间
	 */
	public static String getYesterday(){
		Calendar   cal   =   Calendar.getInstance(); 
		cal.add(Calendar.DATE,   -1); 
		String   yesterday   =   new   SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
		return yesterday;
		
	}
	/**
	 * 得到前天的时间
	 */
	public static String getBeforeYesterday(){
		Calendar   cal   =   Calendar.getInstance(); 
		cal.add(Calendar.DATE,   -2); 
		String   yesterday   =   new   SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
		return yesterday;
		
	}
	/**
	 * N 天前（包含今天）
	 * @return
	 */
	public static String getBeforeNDay(int N){
		Calendar   c   =   Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH,-(N-1));//关键是这个7天前.... 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat( "yyyy-MM-dd"); 
		return sdf.format(c.getTime());
	}
	/**
	 * time时间之后N天的时间
	 * @param time
	 * @param N
	 * @return
	 */
	public static String getLaterNDay(String time,int N){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(sdf.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH,N);//关键是这个7天前.... 
		return sdf.format(c.getTime());
	}
	/**
	 * 时间差
	 * @param mintime
	 * @param maxtime
	 * @return
	 * @throws ParseException
	 */
	public static int getInstaceDate(String mintime,String maxtime) throws ParseException {
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = dfs.parse(mintime);
		Date end = dfs.parse(maxtime);
		long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
		return new Long(between).intValue();
	}
	/**
	 * 两个时间点的时间差
	 * @return
	 * @throws ParseException
	 */
	public static int getInstaceDateSeconds(String mintime,String maxtime) throws ParseException{
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = dfs.parse(mintime);
		Date end = dfs.parse(maxtime);
		long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
		return new Long(between).intValue();
	}
	/**
	 * 时间相差天数
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysDistance(String starttime,String endtime) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Date smdate=sdf.parse(starttime);
	    Date bdate=sdf.parse(endtime);
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis ();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis ();       
        long between_days=(time2-time1)/ (1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));    
	}
	/**
	 * 字符串转换Date yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringToDate(String time) throws ParseException{
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dfs.parse(time);
		return date;
	}
	public static String getDateToString(Date date) throws ParseException{
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfs.format(date);
	}
	/**
	 * 字符串转换Date  yyyy-MM-dd
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringToDate2(String time) throws ParseException{
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dfs.parse(time);
		return date;
	}
}

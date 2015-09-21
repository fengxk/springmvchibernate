package com.surfo.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.surfo.utils.DateUtils;

@Component
public class TaskTimer {
	private static final Logger LOGGER = Logger.getLogger(TaskTimer.class);
//	@Autowired
//	private FilingDataService taskTimerService;
//	//归档前一天的数据
//	@Scheduled(cron="0 0 2 * * ?") //每天凌晨2点执行一次
//	public void doFiling(){
//		LOGGER.info("归档昨天的数据"+DateUtils.getDateSwitchString(new Date()));
//		taskTimerService.doFilingData();
//	}
//	//没月第一天，归档上个月的数据
//	@Scheduled(cron="0 0 3 1 * ?")   //每月1日3点执行一次 
//	public void doFilingMonth(){
//		LOGGER.info("归档上个月的数据"+DateUtils.getDateSwitchString(new Date()));
//		taskTimerService.doFilingDataMonth();
//	}
}

package com.ddf.rent.job;

import java.util.Date;

import com.ddf.reference.common.DateReference;
import com.ddf.rent.service.simple.MonthRentAmountTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.ddf.rent.service.task.StatsHouseAvgPriceTask;
@Component
@Configurable
@EnableScheduling
public class TaskJob {
	private Logger logger = Logger.getLogger(TaskJob.class);
	@Autowired
	private StatsHouseAvgPriceTask statsHouseAvgPriceTask;
	//每1分钟执行一次
	@Scheduled(cron = "0 */1 *  * * * ")
//	@Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行一次
	public void execute(){
		try {
			logger.info("======================== ddf-service-rent job start =================="+new Date());
			//初始化统计数据表
			statsHouseAvgPriceTask.generateMonthRentAmountData();
			//开始统计
			statsHouseAvgPriceTask.statsHouseAvgPrice();
		} catch (Exception e) {
			logger.error("ddf-service-rent taskJob error:"+e.getMessage(),e);
		}
	}
}

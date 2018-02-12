package com.ddf.rent.match.job;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

//1.修改数据，防止并发，2不允许并发执行
@Component("rentMatchJob") 
@PersistJobDataAfterExecution  
@DisallowConcurrentExecution
public class RentMatchJob extends QuartzJobBean{
//	@Autowired
//	private HouseMatchRecordService houseMatchRecordService;
  
    @Override  
    protected void executeInternal(JobExecutionContext arg0)throws JobExecutionException {  
        System.out.println("执行方法"+new Date());
        /*HouseMatchRecord houseMatchRecord=new HouseMatchRecord();
        houseMatchRecord.setAmout(new BigDecimal("1"));
        houseMatchRecord.setChaoxiang("1");
        houseMatchRecord.setCheckInTime(new Date());
        houseMatchRecord.setCreateDate(new Date());
        houseMatchRecord.setCurrentFloor(1l);
        houseMatchRecord.setDanyuanhao("单元号");
        houseMatchRecord.setHouseCertificateImg("图片");
        houseMatchRecord.setHouseFacilityIds("facilitIds");
        houseMatchRecord.setHouseId("1");
        houseMatchRecord.setHouseImgs("imgs");
        houseMatchRecord.setHouseLabelIds("labelIds");
        houseMatchRecord.setId("1");
        houseMatchRecord.setLandlordId("2");
        houseMatchRecord.setLodgerRequireIds("hha");
        houseMatchRecord.setLoudonghao("5");
        houseMatchRecord.setMatchStatus("1");
        houseMatchRecord.setMetorStationId("1");
        houseMatchRecord.setMetroLineId("2");
        houseMatchRecord.setMianji(new BigDecimal("1"));
        houseMatchRecord.setRemark("备注");
        houseMatchRecord.setShi(1l);
        houseMatchRecord.setShihao("12");
        houseMatchRecord.setStatus("success");
        houseMatchRecord.setTing(2l);
        houseMatchRecord.setTotalFloor(12l);
        houseMatchRecord.setUpdateDate(new Date());
        houseMatchRecord.setWei(3l);
        houseMatchRecord.setXiaoquName("小区名字");
        houseMatchRecord.setZhuangxiu("装修");
        houseMatchRecordService.create(houseMatchRecord);*/
        
    }  
  
}  
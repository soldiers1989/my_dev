package com.ddf.rent.service.task;

import com.ddf.entity.rent.eo.StatsStatus;
import com.ddf.entity.rent.vo.MonthRentAmountTaskVo;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.dic.AreaReference;
import com.ddf.rent.service.simple.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StatsHouseAvgPriceTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AreaReference areaReference;
    @Autowired
    private IdReference idReference;
    @Autowired
    private DateReference dateReference;
    @Autowired
    private MonthRentAmountCityService monthRentAmountCityService;
    @Autowired
    private MonthRentAmountDistrictService monthRentAmountDistrictService;
    @Autowired
    private MonthRentAmountCircleService monthRentAmountCircleService;
    @Autowired
    private MonthRentAmountXiaoquService monthRentAmountXiaoquService;
    @Autowired
    private MonthRentAmountTaskService monthRentAmountTaskService;

    /**
     * 按月份初始化数据
     */
    public void generateMonthRentAmountData() {
        logger.info("============================== 生成 所有租金表数据 ================================");
        List<MonthRentAmountTaskVo> taskVoList = monthRentAmountTaskService.findListByDateAndStatus();
        for (MonthRentAmountTaskVo taskVo : taskVoList) {
            Long date = taskVo.getDate();//统计租金 月份
            Date bdate = taskVo.getBdate();
            Date edate = taskVo.getEdate();
            monthRentAmountCityService.generateRentAmountByCity(date, bdate, edate);
            monthRentAmountDistrictService.generateRentAmountByDistrict(date, bdate, edate);
            monthRentAmountCircleService.generateRentAmountByCircle(date, bdate, edate);
            monthRentAmountXiaoquService.generateRentAmountByXiaoqu(date, bdate, edate);
            taskVo.setStatus(StatsStatus.SUCCESS);
            monthRentAmountTaskService.modify(taskVo);
        }

    }

    /**
     * 按月份 统计房子均价
     */
    public void statsHouseAvgPrice() {
        logger.info("============================== 开始统计 所有租金表数据 ================================");
        monthRentAmountCityService.statsRentAmountCityByMonth();
        monthRentAmountDistrictService.statsRentAmountDistrictByMonth();
        monthRentAmountCircleService.statsRentAmountCircleByMonth();
        monthRentAmountXiaoquService.statsRentAmountXiaoquByMonth();
    }

    /**
     * 设置 一室 二室 三室 均价
     * @param currentDate
     * @param id
     * @param spiderHouseVos
     * @return
     */
    public MonthRentAmountVo getMonthRentAmountVo(Date currentDate, String id, List<SpiderHouseVo> spiderHouseVos) {
        MonthRentAmountVo city = new MonthRentAmountVo();
        for (SpiderHouseVo spiderHouseVo : spiderHouseVos) {
            Integer room = spiderHouseVo.getRoom();
            BigDecimal amount = spiderHouseVo.getAmount();
            city.setId(id);
            city.setStatus(StatsStatus.SUCCESS.name());
            city.setStatsDate(currentDate);
            switch (room){
                case 1:
                    city.setOneRoomAmount(amount);
                    break;
                case 2:
                    city.setTwoRoomAmount(amount);
                    break;
                case 3:
                    city.setThreeRoomAmount(amount);
                    break;
            }
        }
        BigDecimal oneRoomAmount = city.getOneRoomAmount();
        BigDecimal twoRoomAmount = city.getTwoRoomAmount();
        BigDecimal threeRoomAmount = city.getThreeRoomAmount();
        //统计均价
        if (oneRoomAmount!=null){
            city.setCalOneRoomAmount(oneRoomAmount);
        }else if(twoRoomAmount!=null) {
            twoRoomAmount = twoRoomAmount.divide(new BigDecimal(2),2);
            city.setCalOneRoomAmount(twoRoomAmount);
        }else if(threeRoomAmount!=null) {
            threeRoomAmount = threeRoomAmount.divide(new BigDecimal(3),3);
            city.setCalOneRoomAmount(threeRoomAmount);
        }
        return city;
    }
}

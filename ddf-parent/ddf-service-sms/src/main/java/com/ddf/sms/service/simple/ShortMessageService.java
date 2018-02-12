package com.ddf.sms.service.simple;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.entity.sms.dto.ShortMessage;
import com.ddf.entity.sms.eo.ShortMessageType;
import com.ddf.entity.sms.query.ShortMessageQuery;
import com.ddf.entity.sms.vo.ShortMessageVo;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.sms.Config;
import com.ddf.sms.dao.ShortMessageDao;
import com.ddf.sms.util.AliMsgTemplate;
import com.ddf.sms.util.AliMsgUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * short_message Service
 * @author robot
 * @version 2018-01-11
 */
@Service
public class ShortMessageService extends CrudServiceImpl<ShortMessage,ShortMessageVo,ShortMessageQuery>{
	
	private Logger logger = Logger.getLogger(ShortMessageService.class);
	
	@Autowired
	private ShortMessageDao shortMessageDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private CacheReference cacheReference;
	
	@Autowired
	private SmsParamService smsParamService;
	
	public Page<ShortMessageVo> query4page(int pageNum, int pageSize){
		ShortMessageQuery shortMessageQuery = new ShortMessageQuery();
		shortMessageQuery.buildPageSql(pageNum, pageSize);
		List<ShortMessageVo> list = this.findList(shortMessageQuery);
		long totalCount = this.findCount(shortMessageQuery);
		Page<ShortMessageVo> page = new Page<ShortMessageVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<ShortMessageVo> query4page(ShortMessageQuery shortMessageQuery){
		if(shortMessageQuery==null){
			shortMessageQuery = new ShortMessageQuery();
		}
		if(StringUtil.isEmpty(shortMessageQuery.getSortSql())){
			shortMessageQuery.buildSortSql("order by a.create_date desc");
		}else{
			shortMessageQuery.buildSortSql(shortMessageQuery.getSortSql());
		}
		shortMessageQuery.buildPageSql();
		List<ShortMessageVo> list = this.findList(shortMessageQuery);
		long totalCount = this.findCount(shortMessageQuery);
		Page<ShortMessageVo> page = new Page<ShortMessageVo>(shortMessageQuery.getPageNum(), shortMessageQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<ShortMessageVo> query4pagehelper(int pageNum, int pageSize){
		ShortMessageQuery shortMessageQuery = new ShortMessageQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<ShortMessageVo> list = this.findList(shortMessageQuery);
        Page<ShortMessageVo> page = new Page<ShortMessageVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(ShortMessage shortMessage){
		String id = idReference.createId(TableName.short_message).getData();
		shortMessage.setId(id);
		shortMessage.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(shortMessage);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(ShortMessage shortMessage){
		shortMessage.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(shortMessage);
	}
	
	
	/**
	 * 发送自定义短信
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean sendContent(String mobile,String content){
		//发送短信
		SendSmsResponse sendSms = AliMsgUtil.sendSms(mobile, content, AliMsgTemplate.verification_content);
		logger.info("短信发送返回结果============================="+sendSms.getMessage());
		
		//发送成功记录日志
		if(sendSms.getCode() != null && sendSms.getCode().equals("OK")) {
			ShortMessage shortMessage = new ShortMessage();
			shortMessage.setId(idReference.createId(TableName.short_message).getData());
			shortMessage.setContent(content);
			shortMessage.setMobile(mobile);
			shortMessage.setType(ShortMessageType.send_content);
			shortMessage.setCreateDate(dateReference.queryCurrentDate().getData());
			dao.create(shortMessage);
			return true;
		}
		return false;
	}
	
	/**
	 * 发送短信验证码
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public ApiResponseResult sendCode(String mobile){
		//获取随机数验证码，保存到缓存key:"verify_" + mobile
		String code = this.createVerifyContent(mobile);
		return this.sendCode(mobile, code);
	}
	
	/**
	 * 发送短信验证码
	 * @param mobile
	 * @param code
	 * @return
	 */
	public ApiResponseResult sendCode(String mobile,String code){
		//缓存验证码
		cacheReference.put4time(RedisKeyConstant.verify_ + mobile, code, 120);
		logger.info("短信验证码============================="+code);
		
		ShortMessage shortMessage = new ShortMessage();
		shortMessage.setId(idReference.createId(TableName.short_message).getData());
		shortMessage.setContent(code);
		shortMessage.setMobile(mobile);
		shortMessage.setType(ShortMessageType.send_code);
		shortMessage.setCreateDate(dateReference.queryCurrentDate().getData());
		
		//发送短信开关  0-关闭短信 1-开启短信
		String isNo_sms = smsParamService.queryValueByName(Config.smsParam_sms);
		if(isNo_sms!=null && "0".equals(isNo_sms)){
			//发送短信开关
			shortMessage.setSwitchStatus(false);
			//记录日志
			dao.create(shortMessage);
			return ApiResponseResult.SUCCESS;
		}else{
			//发送短信开关
			shortMessage.setSwitchStatus(true);
			
			//发送短信验证码
			SendSmsResponse sendSms = AliMsgUtil.sendSms(mobile, code, AliMsgTemplate.verification_code);
			logger.info("短信发送返回结果============================="+sendSms.getMessage());
			
			//发送成功记录日志
			if(sendSms.getCode() != null && sendSms.getCode().equals("OK")) {
				dao.create(shortMessage);
				return ApiResponseResult.SUCCESS;
			}else{
				if (sendSms.getCode().contains("小")) {	
					return ApiResponseResult.SMS_OVERTOP_HOUR_ERROR;
				 }
				 if (sendSms.getCode().contains("天")) {	
					 return ApiResponseResult.SMS_OVERTOP_DAY_ERROR;
				}
			}
			return ApiResponseResult.SMS_ERROR;
		}
		
	}
	
	/**
	 * 验证短信验证码(缓存中的验证码)
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean checkCode(String mobile,String code){
		if(StringUtil.isEmpty(mobile) || StringUtil.isEmpty(code)) {
			return false;
		}
		//获取缓存验证码
		String redis_code = cacheReference.get(RedisKeyConstant.verify_ + mobile).getData();
		if(StringUtil.isEmpty(redis_code)) {
			return false;
		}
		if(code.equals(redis_code)){
			return true;
		}
		return false;
	}
	
	
	/**
     * 产生验证码
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    private String createVerifyContent(String mobile){
    	String verify = (int)((Math.random()*9+1)*1000)+"";// 四位数验证码-正式环境
        logger.info(mobile+" 验证码为：" + verify);
        return verify;
    }
}
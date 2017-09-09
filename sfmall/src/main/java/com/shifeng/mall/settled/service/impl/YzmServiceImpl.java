package com.shifeng.mall.settled.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.shifeng.mall.settled.dto.YzmDTO;
import com.shifeng.mall.settled.service.YzmService;
import com.shifeng.provide.ali.service.AliService;
import com.shifeng.response.ReqResponse;
import com.shifeng.util.Const;
import com.shifeng.util.SerializationUtil;
import com.shifeng.util.redis.RedisTool;

@Service("yzmServiceImpl")
public class YzmServiceImpl implements YzmService{
	@Resource(name = "aliService")
	private AliService aliService;
	
	/**
	 * 短信验证码发送间隔时间
	 */
	static final Integer POST_YZM_INTERVAL_TIME = 60;
	
	/**
	 * 短信验证码有效期
	 */
	static final Integer YZM_VALID_MINUTE = 5;
	
	/**
	 * 短信接收时间
	 */
	public static String SMS_USERID_INTERVAL_TIME_KEY ="SMS:USERID:INTERVAL:%s";
	
	/**
	 * 短信发送次数
	 */
	public static String SMS_USERID_COUNT_KEY ="SMS:USERID:COUNT:%s";
	
	/**
	 * 短信验证码
	 */
	public static String SMS_USERID_YZM_KEY ="SMS:USERID:YZM:%s";
	
	/**
	 * 发送短信手机
	 */
	public static String SMS_USERID_PHONE_KEY ="SMS:USERID:PHONE:%s";
	
	/**
	 * 发送验证码
	 * @param map
	 * @param userId 用户ID
	 * @param phone 手机号
	 */
	public void validateYzmTime(Map<String,String> map,String userId,String phone){
		String time_key = String.format(SMS_USERID_INTERVAL_TIME_KEY, userId);
		String time = RedisTool.get(time_key);
		
		if(!StringUtils.isEmpty(time)){
			map.put(Const.ERROR_INFO, "短信发送间隔为"+POST_YZM_INTERVAL_TIME+"秒，请稍后重试!!!");
			return;
		}
		
		String count_key = String.format(SMS_USERID_COUNT_KEY, userId);
		String count = RedisTool.get(count_key);
		if(!StringUtils.isEmpty(count)&&Integer.valueOf(count)==3){
			map.put(Const.ERROR_INFO, "商家入驻一天最多发送3次验证码！");
			return;
		}
		
		//上次发送短信时间
		RedisTool.incr(time_key);
		RedisTool.expire(time_key, POST_YZM_INTERVAL_TIME);
		//发送短信次数
		RedisTool.incr(count_key);
		RedisTool.expire(count_key, 24*60*60);
		
		// 生成4为数字验证码
		int yzm = (int) (Math.random() * 8999) + 1000;
		ReqResponse<Integer> req = aliService.sendSMS(phone, "{\"code\":\""+yzm+"\",\"product\":\"商家入驻\"}", "SMS_16340212");
		if(req.getCode()==0){
			String yzm_key = String.format(SMS_USERID_YZM_KEY, userId);
			RedisTool.set(yzm_key,yzm+"");
			RedisTool.expire(yzm_key,YZM_VALID_MINUTE*60);
			
			String phone_key = String.format(SMS_USERID_PHONE_KEY, userId);
			RedisTool.set(phone_key,phone+"");
			RedisTool.expire(phone_key,YZM_VALID_MINUTE*60);
			
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		}else{
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_ERROR);
			map.put(Const.ERROR_INFO, "短信发送失败！");
		}
	}
	
	/**
	 * 验证验证码
	 * @param map
	 * @param userId 用户ID
	 * @param phone 手机号
	 * @param yzm 验证码
	 */
	public void verifyYzm(Map<String,String> map,String userId,String phone,String yzm){
		String yzm_key = String.format(SMS_USERID_YZM_KEY, userId);
		String r_yzm = RedisTool.get(yzm_key);
		
		boolean bool = true;
		//验证码是否失效
		if(StringUtils.isEmpty(r_yzm)){
			bool = false;
		}else{
			//验证码是否正确
			if(!yzm.equals(r_yzm)){
				bool = false;
			}else{
				String phone_key = String.format(SMS_USERID_PHONE_KEY, userId);
				String r_phone = RedisTool.get(phone_key);
				//接受短信手机与当前手机是否一致
				if(!phone.equals(r_phone)){
					bool = false;
				}
			}
		}
		
		if(bool){
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		}else{
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_ERROR);
		}
		
	}
	
}

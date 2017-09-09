package com.shifeng.mall.settled.service;

import java.util.Map;

public interface YzmService {
	/**
	 * 发送验证码
	 * @param map
	 * @param userId 用户ID
	 * @param phone 手机号
	 */
	public void validateYzmTime(Map<String,String> map,String userId,String phone);
	
	/**
	 * 验证验证码
	 * @param map
	 * @param userId 用户ID
	 * @param phone 手机号
	 * @param yzm 验证码
	 */
	public void verifyYzm(Map<String,String> map,String userId,String phone,String yzm);
	
}

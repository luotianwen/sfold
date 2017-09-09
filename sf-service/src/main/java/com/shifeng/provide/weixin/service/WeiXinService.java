package com.shifeng.provide.weixin.service;

import com.shifeng.entity.user.SysUser;
import com.shifeng.response.ReqResponse;

public interface WeiXinService {
	
	/**
	 * 微信用户登录
	 * @param appid	公众号的唯一标识
	 * @param secret	公众号的唯一凭证密钥，即appsecret
	 * @param code 用户的code
	 * @return
	 */
	ReqResponse<SysUser> login(String appid,String secret,String code);

}

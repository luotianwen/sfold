package com.shifeng.provide.weixin.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shifeng.entity.user.JointLogin;
import com.shifeng.entity.user.SysUser;
import com.shifeng.provide.util.Constant;
import com.shifeng.provide.util.HttpUtil;
import com.shifeng.provide.util.JointLoginType;
import com.shifeng.provide.weixin.dao.WeiXinDao;
import com.shifeng.provide.weixin.service.WeiXinService;
import com.shifeng.response.ReqResponse;
import com.shifeng.util.redis.RedisTool;

@Service("weiXinServiceImpl")
public class WeiXinServiceImpl implements WeiXinService{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "weiXinDao")
	private WeiXinDao dao;
	
	//@Value("#{properties['weixin.appid']}")
	private String appid;
	//@Value("#{properties['weixin.secret']}")
	private String secret;
	
	/**
	 * 获取微信公众号access_token
	 * @return
	 */
	private String getAccessToken(){
		String access_token = RedisTool.get(Constant.WEIXIN_KEY);
		if(access_token == null){
			try {
				/*HttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(new URI("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid
						+"&secret="+secret));
				//执行Get请求，  
				HttpResponse httpresponse = httpclient.execute(httpGet);
				HttpEntity entity = httpresponse.getEntity();
				String body = EntityUtils.toString(entity);*/
				String body = HttpUtil.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid
						+"&secret="+secret);
				//System.out.println(body);
				JSONObject json = JSON.parseObject(body);
				access_token = json.getString("access_token");
				if(access_token != null){
					//System.out.println(json.getString("expires_in"));
					//access_token写入redis缓存
					RedisTool.set(Constant.WEIXIN_KEY, access_token);
					//设置过期时间
					RedisTool.expire(Constant.WEIXIN_KEY, 60*110);
				}else{
					logger.error("获取微信公众号access_token出错："+ body);
				}
			} catch (Exception e) {
				logger.error("获取微信公众号access_token出错：", e);
			}
		}
		return access_token;
	}
	
	/**
	 * 根据code获取微信用户的access_token
	 * @param code
	 * @return
	 */
	private JointLogin getUserAccessToken(String code){
		JointLogin jl = null;
		try {
			/*HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(new URI("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid
					+"&secret="+secret+"&code="+code+"&grant_type=authorization_code"));
			//执行Get请求，  
			HttpResponse httpresponse = httpclient.execute(httpGet);
			HttpEntity entity = httpresponse.getEntity();
			String body = EntityUtils.toString(entity);*/
			String body = HttpUtil.httpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid
					+"&secret="+secret+"&code="+code+"&grant_type=authorization_code");
			//System.out.println(body);
			JSONObject json = JSON.parseObject(body);
			String access_token = json.getString("access_token");
			if(access_token != null){
				jl = new JointLogin();
				jl.setLoginType(JointLoginType.WEIXIN);
				jl.setAccessToken(access_token);
				jl.setExpiresIn(json.getString("expires_in"));
				jl.setRefreshToken(json.getString("refresh_token"));
				jl.setOpenid(json.getString("openid"));
				jl.setScope(json.getString("scope"));
			}else{
				logger.error("根据code获取微信用户的access_token出错："+ body);
			}
		} catch (Exception e) {
			logger.error("根据code获取微信用户的access_token出错：", e);
		}
		return jl;
	}
	
	/**
	 * 根据微信用户的access_token 、openid获取用户信息
	 */
	private SysUser getUserInfo(JointLogin jointLogin){
		 SysUser user = null;
		try {
			String body = HttpUtil.httpGet("https://api.weixin.qq.com/sns/userinfo?access_token="+jointLogin.getAccessToken()
					+"&openid="+jointLogin.getOpenid()+"&lang=zh_CN");
			JSONObject json = JSON.parseObject(body);
			//用户昵称
			String nickname = json.getString("nickname");
			if(nickname != null){
				 user = new SysUser();
				 user.setName(nickname);
				 //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
				 user.setSex(json.getString("sex"));
				 //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）
				 //，用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
				 user.setPortrait(json.getString("headimgurl"));
				 //province	用户个人资料填写的省份
				 //city	普通用户个人资料填写的城市
				 //country	国家，如中国为CN
				 jointLogin.setUnionid(json.getString("unionid"));
				 
			}else{
				logger.error("根据微信用户的access_token 、openid获取用户信息出错："+ body);
			}
		} catch (Exception e) {
			logger.error("根据微信用户的access_token 、openid获取用户信息：", e);
		}
		return user;
	}

	/**
	 * 微信用户登录
	 * @param appid	公众号的唯一标识
	 * @param secret	公众号的唯一凭证密钥，即appsecret
	 * @param code 用户的code
	 * @return
	 */
	public ReqResponse<SysUser> login(String appid,String secret,String code) {
		ReqResponse<SysUser> req = new ReqResponse<SysUser>();
		this.appid = appid;
		this.secret = secret;
		try {
			JointLogin jointLogin = this.getUserAccessToken(code);
			if(jointLogin != null){
				SysUser user = this.getUserInfo(jointLogin);
				if(user != null){
					dao.updateLoginInfo(jointLogin, user);
					req.setData(user);
				}else{
					req.setCode(1);
					req.setMsg("登录出错");
				}
			}else{
				req.setCode(1);
				req.setMsg("登录出错");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信用户登录出错：",e);
			req.setCode(1);
			req.setMsg("登录出错");
		}
		return req;
	}
	
}

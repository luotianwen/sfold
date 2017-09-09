package com.shifeng.webapi.common;

public class Constant {
	
	/**
	 * 验证码有效时间，十分钟
	 */
	public static int  AUTHCODE_VALID_TIME = 1*60*10;
	
	/**
	 * 接口每分钟最大允许调用次数
	 */
	public static int MINUTE_MAX_VISIT_COUNT = 20;
	
	/**
	 * ticket
	 */
	public static String TICKET_KEY ="ticket:%s";
	
	/**
	 * 用户登录授权token
	 * user_id_token_token值
	 * 存储用户id
	 */
	public static String USER_ID_TOKEN_KEY ="user_id_token:%s";
	
	/**
	 * 用户登录信息
	 * user_token_token值
	 * 存储用户登录信息
	 */
	public static String USER_TOKEN_KEY ="user_token:%s";
	
	/**
	 * 验证码key
	 * authcode_ticket值_验证码类型（reg....）
	 */
	public static String AUTHCODE_KEY ="authcode:%s_%s";

	
	/**
	 * 访问次数key
	 * visit_count_ticket值_method方法名
	 */
	public static String VISIT_COUNT_KEY ="visit:count:%s_%s";
	
	/**
	 * 每天短信发送次数key
	 *notice:mobileCode:count:手机号
	 */
	public static String NOTICE_MOBILECODE_DAY_COUNT_KEY ="notice:mobileCode:count:day:%s";
	
	/**
	 * 每天每个手机号最大发送手机验证码次数
	 */
	public static int DAY_MAX_SEND_MOBILECODE_COUNT = 5;
	
	/**
	 * 每分钟每个手机号最大发送手机验证码次数
	 */
	public static int MINUTE_MAX_SEND_MOBILECODE_COUNT = 1;
	/**
	 * 每分钟短信发送次数key
	 *notice:mobileCode:minute:count:手机号
	 */
	public static String NOTICE_MOBILECODE_MINUTE_COUNT_KEY ="notice:mobileCode:count:minute:%s";
	/**
	 * 短信验证码key
	 *mobileCode:手机号
	 */
	public static String MOBILECODE_KEY ="mobileCode:%s";

	/**
	 * 注册短信验证码key
	 *reg_mobileCode:手机号
	 */
	public static String REG_MOBILECODE_KEY ="reg_mobileCode:%s";

	/**
	 *注册手机号key
	 *reg_phone:ticket值
	 */
	public static String REG_PHONE_KEY ="reg_phone:%s";

	/**
	 *注册进度key
	 *reg_progress:ticket值
	 */
	public static String REG_PROGRESS_KEY ="reg_progress:%s";

	/**
	 * 找回密码短信验证码key
	 *FINDPWD_mobileCode:手机号
	 */
	public static String FINDPWD_MOBILECODE_KEY ="findPwd_mobileCode:%s";

	/**
	 *找回密码手机号key
	 *FINDPWD_phone:ticket值
	 */
	public static String FINDPWD_PHONE_KEY ="findPwd_phone:%s";

	/**
	 *找回密码进度key
	 *FINDPWD_progress:ticket值
	 */
	public static String FINDPWD_PROGRESS_KEY ="findPwd_progress:%s";
	
	
	/**
	 * app首页数据缓存key
	 *
	 */
	public static String APP_HOMEPAGE_DATA_KEY ="app_homepage_data";
	

	/**
	 * app首页分类广告数据缓存key
	 *
	 */
	public static String APP_HOMEPAGE_CATEGORY_AD_DATA_KEY ="app_homepage_category_ad_data";
	

	/**
	 * 订单待结算商品信息缓存
	 * order_stay_settlement:token值
	 */
	public static String ORDER_STAY_SETTLEMENT ="order_stay_settlement:%s";
	
	
	
}

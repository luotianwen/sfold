package com.shifeng.entity.fx;

import java.io.Serializable;
import java.util.Date;
/** 
 * 用户余额日志(fx_user_money_log)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-07 11:21:34 
 */  
public class FxUserMoneyLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//uid
  	 private Integer uid;
 	//金额
  	 private double money;
 	//最后更新时间
  	 private Date lasttime;
 	//余额
  	 private double lastmoney;



	 
    /**
    *uid
	* @return
    */ 
	public Integer getUid() {
		return uid;
	}
    /**
    *uid
	* @param uid
    */ 
	public void setUid(Integer uid) {
		this.uid = uid;
	}
    /**
    *金额
	* @return
    */ 
	public double getMoney() {
		return money;
	}
    /**
    *金额
	* @param money
    */ 
	public void setMoney(double money) {
		this.money = money;
	}
    /**
    *最后更新时间
	* @return
    */ 
	public Date getLasttime() {
		return lasttime;
	}
    /**
    *最后更新时间
	* @param lasttime
    */ 
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
    /**
    *余额
	* @return
    */ 
	public double getLastmoney() {
		return lastmoney;
	}
    /**
    *余额
	* @param lastmoney
    */ 
	public void setLastmoney(double lastmoney) {
		this.lastmoney = lastmoney;
	}
	
}

package com.shifeng.entity.fx;

import java.io.Serializable;
import java.util.Date;
/** 
 * 用户余额(fx_user_money)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-27 11:06:20 
 */  
public class FxUserMoney implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//uid
  	 private Integer uid;
	//name
	private String name;
 	//金额
  	 private Double money;
 	//最后更新时间
  	 private Date lasttime;
 	//所有佣金
  	 private Double allmoney;
 	//已提现佣金
  	 private Double costmoney;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
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
	public Double getMoney() {
		return money;
	}
    /**
    *金额
	* @param money
    */ 
	public void setMoney(Double money) {
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
    *所有佣金
	* @return
    */ 
	public Double getAllmoney() {
		return allmoney;
	}
    /**
    *所有佣金
	* @param allmoney
    */ 
	public void setAllmoney(Double allmoney) {
		this.allmoney = allmoney;
	}
    /**
    *已提现佣金
	* @return
    */ 
	public Double getCostmoney() {
		return costmoney;
	}
    /**
    *已提现佣金
	* @param costmoney
    */ 
	public void setCostmoney(Double costmoney) {
		this.costmoney = costmoney;
	}
	
}

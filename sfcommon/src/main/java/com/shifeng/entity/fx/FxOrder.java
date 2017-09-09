package com.shifeng.entity.fx;

import java.io.Serializable;
import java.util.Date;
/** 
 * 分销订单(fx_order)实体类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-27 11:06:20 
 */  
public class FxOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	//orderid
  	 private String orderid;
 	//分销结算状态
  	 private Integer state;
 	//佣金
  	 private Double commission;
 	//推荐用户id
  	 private Integer recommend_userid;
 	//被推荐用户id
  	 private Integer recommended_userid;
 	//状态
  	 private Integer type;



	 
    /**
    *orderid
	* @return
    */ 
	public String getOrderid() {
		return orderid;
	}
    /**
    *orderid
	* @param orderid
    */ 
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
    /**
    *分销结算状态
	* @return
    */ 
	public Integer getState() {
		return state;
	}
    /**
    *分销结算状态
	* @param state
    */ 
	public void setState(Integer state) {
		this.state = state;
	}
    /**
    *佣金
	* @return
    */ 
	public Double getCommission() {
		return commission;
	}
    /**
    *佣金
	* @param commission
    */ 
	public void setCommission(Double commission) {
		this.commission = commission;
	}
    /**
    *推荐用户id
	* @return
    */ 
	public Integer getRecommend_userid() {
		return recommend_userid;
	}
    /**
    *推荐用户id
	* @param recommend_userid
    */ 
	public void setRecommend_userid(Integer recommend_userid) {
		this.recommend_userid = recommend_userid;
	}
    /**
    *被推荐用户id
	* @return
    */ 
	public Integer getRecommended_userid() {
		return recommended_userid;
	}
    /**
    *被推荐用户id
	* @param recommended_userid
    */ 
	public void setRecommended_userid(Integer recommended_userid) {
		this.recommended_userid = recommended_userid;
	}
    /**
    *状态
	* @return
    */ 
	public Integer getType() {
		return type;
	}
    /**
    *状态
	* @param type
    */ 
	public void setType(Integer type) {
		this.type = type;
	}
	
}

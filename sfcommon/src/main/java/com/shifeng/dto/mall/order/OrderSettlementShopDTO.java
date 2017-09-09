package com.shifeng.dto.mall.order;

import java.io.Serializable;
import java.util.List;

/**
 * 订单结算信息
 * @author WinZhong
 *
 */
public class OrderSettlementShopDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

  	//店铺id
 	private Integer shopid;
	//店铺优惠券ID
	private String shopCouponId;
	//是否开发票(1：不开发票；2：开发票)
	private Integer notPutInvoice;
	//发票ID
	private Integer invoiceId;
	//备注留言
	private String remark;
	//店铺商品
	private List<OrderWareDTO> wares;
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getShopCouponId() {
		return shopCouponId;
	}
	public void setShopCouponId(String shopCouponId) {
		this.shopCouponId = shopCouponId;
	}
	public Integer getNotPutInvoice() {
		return notPutInvoice;
	}
	public void setNotPutInvoice(Integer notPutInvoice) {
		this.notPutInvoice = notPutInvoice;
	}
	public Integer getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<OrderWareDTO> getWares() {
		return wares;
	}
	public void setWares(List<OrderWareDTO> wares) {
		this.wares = wares;
	}
	@Override
	public String toString() {
		return "OrderSettlementShopDTO [shopid=" + shopid + ", shopCouponId=" + shopCouponId + ", notPutInvoice="
				+ notPutInvoice + ", invoiceId=" + invoiceId + ", remark=" + remark + ", wares=" + wares + "]";
	}
	
	

}

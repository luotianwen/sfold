package com.shifeng.pay.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.shifeng.common.OrderType;
import com.shifeng.dao.BaseDao;
import com.shifeng.pay.entity.order.UnpaidOrderInfo;
import com.shifeng.pay.entity.pay.PayPaymentRecord;
import com.shifeng.pay.service.order.OrderService;

/**
 * 订单
 * @author WinZhong
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    @Resource(name = "baseDaoImpl")
    private BaseDao dao;

	protected Logger logger = Logger.getLogger(this.getClass());
	

	
	/**
	 * 判断订单是否已支付
	 * @param order_id	订单id
	 * @param order_type	订单类型
	 * @return
	 */
	public UnpaidOrderInfo isOrderNoDeal(String order_id,String order_type) throws Exception {
		UnpaidOrderInfo order = null;
		switch (order_type) {
		case OrderType.WARE:
			order = (UnpaidOrderInfo)dao.findForObject("wareOrderMapper.getUnpaidWareOrderById", order_id);
			break;
		case OrderType.SHOP://商家店铺入驻续费订单
			order = (UnpaidOrderInfo)dao.findForObject("UnpaidOrderMapper.getUnpaidShopOrderById", order_id);
			break;
		default:
			break;
		}
		
		return order;
	}
	


	/**
	 * 更新订单支付状态
	 * @param order_id	订单id
	 * @param order_type	订单类型
	 * @param paymentRecord	订单支付流水信息
	 */
	public void updateOrderState(String order_id,String order_type,Object paymentRecord)  throws Exception {
	  boolean isSave = true;
	  PayPaymentRecord pp = new PayPaymentRecord(paymentRecord);
	  String pay_serial_number = pp.getSerial_number();
	  switch (order_type) {
		case OrderType.WARE:
		 	dao.update("wareOrderMapper.updateParentOrderPayState", new String[]{order_id,pay_serial_number});
		 	dao.update("wareOrderMapper.updateOrderPayState", new String[]{order_id,pay_serial_number});
			break;
		case OrderType.SHOP://商家店铺入驻续费订单	
			dao.update("shopOrderMapper.updateOrderPayState", new String[]{order_id,pay_serial_number});
		break;
		default:
			isSave = false;
			break;
	  }
		if(isSave){
			dao.save("payPaymentRecordMapper.savePayPaymentRecord",pp);
		}
	  
	}
	
	

}

package com.shifeng.webapi.service.order;

import java.util.List;

import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.dto.mall.order.OrderWareDTO;

/**
 * 
 * @author WinZhong
 *
 */
public interface OrderService {
	
	/**
	 * 获取订单预览信息
	 * @return
	 */
	List<OrderPreviewInfoDTO> getOrderPreviewInfo(List<OrderWareDTO>  products);
	
	/**
	 * 订单结算
	 * @param orderSettlementInfo
	 * @return
	 */
	OrderPayConfirmDTO settlement(String user_id,OrderSettlementDTO  orderSettlementInfo);

}

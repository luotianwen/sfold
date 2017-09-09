package com.shifeng.provide.mall.service;

import java.util.List;

import com.shifeng.dto.mall.order.OrderWareDTO;
import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.response.ReqResponse;

/**
 * 商城订单Service接口
 * @author WinZhong
 *
 */
public interface MallOrderService {
	
	

	
	/**
	 * 获取订单预览信息
	 * @return
	 */
	ReqResponse<List<OrderPreviewInfoDTO>> getOrderPreviewInfo(List<OrderWareDTO>  products);
	
	/**
	 * 订单结算
	 * @param orderSettlementInfo
	 * @return
	 */
	ReqResponse<OrderPayConfirmDTO> settlement(String user_id,OrderSettlementDTO  orderSettlementInfo);
	

}

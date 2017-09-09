package com.shifeng.webapi.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.shifeng.dto.mall.order.OrderWareDTO;
import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.provide.mall.service.MallOrderService;
import com.shifeng.response.ReqResponse;
import com.shifeng.webapi.service.order.OrderService;

/**
 * 
 * @author WinZhong
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    @Resource(name = "mallOrderService")
    private MallOrderService mallOrderService;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 获取订单预览信息
	 * @return
	 */
	public List<OrderPreviewInfoDTO> getOrderPreviewInfo(List<OrderWareDTO>  products) {
		try {
			ReqResponse<List<OrderPreviewInfoDTO>> result = mallOrderService.getOrderPreviewInfo(products);
			if(result.getCode() == 0){
				System.out.println(result.getData());
				return result.getData();
			}
		} catch (Exception e) {
			logger.error("【添加购物车商品】出错：", e);
		}
		return null;
	}
	

	
	/**
	 * 订单结算
	 * @param orderSettlementInfo
	 * @return
	 */
	public OrderPayConfirmDTO settlement(String user_id,OrderSettlementDTO orderSettlementInfo) {
		try {
			ReqResponse<OrderPayConfirmDTO> result = mallOrderService.settlement(user_id,orderSettlementInfo);
			if(result.getCode() == 0){
				return result.getData();
			}
		} catch (Exception e) {
			logger.error("【订单结算】出错：", e);
		}
		return null;
	}

}

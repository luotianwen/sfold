package com.shifeng.provide.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.config.annotation.Service;
import com.shifeng.dto.mall.order.OrderWareDTO;
import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.provide.mall.dao.MallOrderDao;
import com.shifeng.provide.mall.service.MallOrderService;
import com.shifeng.response.ReqResponse;

/**
 * 商城订单Service接口实现
 * @author WinZhong
 *
 */
@Service(timeout=1200000)
public class MallOrderServiceImpl implements MallOrderService{

    private Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "mallOrderDao")
	private MallOrderDao mallOrderDao;
	
	

	
	/**
	 * 获取订单预览信息
	 */
	public ReqResponse<List<OrderPreviewInfoDTO>> getOrderPreviewInfo(List<OrderWareDTO>  products) {

		ReqResponse<List<OrderPreviewInfoDTO>> req = new ReqResponse<List<OrderPreviewInfoDTO>>();
		try {
			if(products == null || products.size() == 0){
				return req;
			}
			mallOrderDao.getOrderPreviewInfo(products,req);
			return req;
		} catch (Exception e) {
			logger.error("【获取订单预览信息】出错：", e);
			req.setCode(1);
			req.setMsg("【获取订单预览信息】异常");
			return req;
		}
	}
	

	
	/**
	 * 订单结算
	 * @param orderSettlementInfo
	 * @return
	 */
	public ReqResponse<OrderPayConfirmDTO> settlement(String user_id,OrderSettlementDTO  orderSettlementInfo) {
		ReqResponse<OrderPayConfirmDTO> req = new ReqResponse<OrderPayConfirmDTO>();
		try {
			mallOrderDao.saveSettlement(user_id,orderSettlementInfo,req);
			return req;
		} catch (Exception e) {
			logger.error("【订单结算】出错：", e);
			req.setCode(1);
			req.setMsg("【订单结算】异常");
			return req;
		}
	}
	

}

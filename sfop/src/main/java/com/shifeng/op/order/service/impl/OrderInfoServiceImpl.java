package com.shifeng.op.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shifeng.dao.BaseDao;
import com.shifeng.entity.order.OrderInfo;
import com.shifeng.op.dto.order.OrderInfoDTO;
import com.shifeng.op.order.service.OrderInfoService;
import com.shifeng.plugin.page.Page;

/** 
 * 订单表(o_orderInfo)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-02-24 10:43:55 
 */  
@Service("orderInfoServiceImpl")
public class OrderInfoServiceImpl implements OrderInfoService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<OrderInfoDTO> findAllOrderInfo(Page page) throws Exception{
		return (List<OrderInfoDTO>) dao.findForList("orderInfoMapper.findAllOrderInfoPage", page);
	}
	
	/**
	 * 根据ID查询
	 */
	public OrderInfo findOrderInfoById(String id) throws Exception{
		return (OrderInfo) dao.findForObject("orderInfoMapper.findOrderInfoById", id);
	}
	
	/**
	 * 新增
	 * @param orderInfo
	 * @throws Exception
	 */
	public void saveOrderInfo(OrderInfo orderInfo) throws Exception{
		dao.save("orderInfoMapper.saveOrderInfo", orderInfo);
	}
	
	/**
	 * 修改
	 * @param orderInfo
	 * @throws Exception
	 */
	public void updateOrderInfo(OrderInfo orderInfo) throws Exception{
		dao.update("orderInfoMapper.updateOrderInfo", orderInfo);
	}
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteOrderInfo(String id) throws Exception{
		dao.delete("orderInfoMapper.deleteOrderInfo", id);
	}
	
}

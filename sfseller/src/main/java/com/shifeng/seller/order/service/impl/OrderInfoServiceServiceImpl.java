package com.shifeng.seller.order.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shifeng.dao.BaseDao;
import com.shifeng.entity.order.OrderInfoService;
import com.shifeng.seller.order.service.OrderInfoServiceService;
import com.shifeng.plugin.page.Page;

/** 
 * 订单售后(o_orderInfo_service)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-04-12 19:08:54 
 */  
@Service("orderInfoserviceServiceImpl")
public class OrderInfoServiceServiceImpl implements OrderInfoServiceService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<OrderInfoService> findAllOrderInfoService(Page page) throws Exception{
		return (List<OrderInfoService>) dao.findForList("orderInfoserviceMapper.findAllOrderInfoServicePage", page);
	}
	
	/**
	 * 根据ID查询
	 */
	public OrderInfoService findOrderInfoServiceById(String id) throws Exception{
		return (OrderInfoService) dao.findForObject("orderInfoserviceMapper.findOrderInfoServiceById", id);
	}
	
	/**
	 * 新增
	 * @param orderInfoservice
	 * @throws Exception
	 */
	public void saveOrderInfoService(OrderInfoService orderInfoservice) throws Exception{
		dao.save("orderInfoserviceMapper.saveOrderInfoService", orderInfoservice);
	}
	
	/**
	 * 修改
	 * @param orderInfoservice
	 * @throws Exception
	 */
	public void updateOrderInfoService(OrderInfoService orderInfoservice) throws Exception{
		dao.update("orderInfoserviceMapper.updateOrderInfoService", orderInfoservice);
	}
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteOrderInfoService(String id) throws Exception{
		dao.delete("orderInfoserviceMapper.deleteOrderInfoService", id);
	}
	
}

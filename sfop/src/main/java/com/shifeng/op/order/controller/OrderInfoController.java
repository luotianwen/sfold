package com.shifeng.op.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shifeng.entity.order.OrderInfo;
import com.shifeng.op.dto.order.OrderInfoDTO;
import com.shifeng.op.dto.order.SearchOrderInfoDTO;
import com.shifeng.op.entity.users.Users;
import com.shifeng.op.order.service.OrderInfoService;
import com.shifeng.plugin.page.Page;
import com.shifeng.util.Const;


/** 
 * 订单表(o_orderInfo)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-02-24 10:43:55 
 */ 
@Controller
@RequestMapping(value="/orderInfo")
public class OrderInfoController{
	
	@Resource(name="orderInfoServiceImpl")
	private OrderInfoService orderInfoServiceImpl;

	/**
	 * 列表页面
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goOrderInfoList")
	public ModelAndView goOrderInfoList(ModelAndView mv) throws Exception{
		mv.setViewName("order/orderInfoList");
		return mv;
	}
	 
	 
	/**
	 * 查询所有
	 * @param page
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findAllOrderInfo")
	@ResponseBody
	public Map<String,Object> findAllOrderInfo(Page page,SearchOrderInfoDTO dto) throws Exception{
		if(dto==null){
			dto = new SearchOrderInfoDTO();
		}
		page.setT(dto);
		Map<String,Object> map = new HashMap<String,Object>();
		List<OrderInfoDTO> orderInfos = orderInfoServiceImpl.findAllOrderInfo(page);
		map.put("orderInfos", orderInfos);
		map.put("page", page);
		return map;
	}
 
 
	/**
	 * 跳转编辑页面
	 * @param mv
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goOrderInfoEdit")
	@ResponseBody
	public ModelAndView goOrderInfoEdit(ModelAndView mv,String id) throws Exception{
		mv.addObject("id", id);
		mv.setViewName("order/orderInfoEdit");
		return mv;
	}
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findOrderInfoById")
	@ResponseBody
	public Map<String,Object> findOrderInfoById(String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		OrderInfo orderInfo = orderInfoServiceImpl.findOrderInfoById(id);
		map.put("orderInfo",orderInfo);
		return map;
	}
	
	/**
	 * 新增
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrderInfo")
	@ResponseBody
	public Map<String,Object> saveOrderInfo(OrderInfo orderInfo,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.OP_SESSION_USER);
		try {
			orderInfoServiceImpl.saveOrderInfo(orderInfo);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 修改
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOrderInfo")
	@ResponseBody
	public Map<String,Object> updateOrderInfo(OrderInfo orderInfo,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.OP_SESSION_USER);
		try {
			orderInfoServiceImpl.updateOrderInfo(orderInfo);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteOrderInfo")
	@ResponseBody
 	public Map<String,Object> deleteOrderInfo(String id) throws Exception{
 		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		try {
			orderInfoServiceImpl.deleteOrderInfo(id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
 	}
 
}

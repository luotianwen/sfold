package com.shifeng.webapi.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shifeng.dto.mall.MallUserAddressDTO;
import com.shifeng.dto.mall.freight.MallShopWareFreight;
import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.dto.mall.order.OrderWareDTO;
import com.shifeng.response.ReqResponse;
import com.shifeng.webapi.common.ApiVersion;
import com.shifeng.webapi.common.ErrorMsg;
import com.shifeng.webapi.controller.BaseController;
import com.shifeng.webapi.service.mall.FreightService;
import com.shifeng.webapi.service.order.OrderService;
import com.shifeng.webapi.service.user.AddressService;

/**
 * 订单API接口 Controller
 * @author WinZhong
 *
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "orderServiceImpl")
	protected OrderService orderService;
	
	@Resource(name = "addressServiceImpl")
	protected AddressService addressService;
	
	@Resource(name = "freightServiceImpl")
	protected FreightService freightService;
	
	
	
	/**
	 * 确认订单
	 * @param version
	 * @param token
	 * @param products 购买商品信息json串 示例：[{"sku":"3","pcount":"1"},{"sku":"63","pcount":"2"},{"sku":"47","pcount":"3"}]
	 * @param sign (version+token+products)
	 * @return
	 */
	@RequestMapping(value = "/confirm_order")
	@ResponseBody
	public ReqResponse<Map<String,Object>> confirm_order(String version,String token,String products,String sign){
		ReqResponse<Map<String,Object>> req = new ReqResponse<Map<String,Object>>();
    	//登录验证
		if(!checkLogin(token, "order/confirm_order", req)){
		//	return req;
		}
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, version+token+products,req)){
					//OrderPreloadingDTO
					//user_id = "23";
					List<OrderWareDTO>  orderWareList = JSON.parseArray(products, OrderWareDTO.class); 
					System.out.println(orderWareList);
					List<OrderPreviewInfoDTO> productList = orderService.getOrderPreviewInfo(orderWareList);
					//获取默认收货地址
					MallUserAddressDTO address = addressService.getDefaultAddress(user_id);
					//计算运费
					List<MallShopWareFreight> shopWareFreights = freightService.getWareFreights(orderWareList, address.getProvince()+""); 
					
					Map<String,Object> map = new HashMap<String,Object>();
					
					map.put("address", address);
					map.put("products", productList);
					map.put("shopWareFreights", shopWareFreights);
					req.setData(map);
					/*String key = String.format(Constant.ORDER_STAY_SETTLEMENT, token);
					//token写入redis缓存
					RedisTool.set(key, JSON.toJSONString(o));
					//设置过期时间 单位：秒
					RedisTool.expire(key, 1*60*30);*/
				}
				break;
			default://不支持的版本号
				req.setCode(ErrorMsg.UNSUPPORTED_VERSION.getCode());
				req.setMsg(ErrorMsg.UNSUPPORTED_VERSION.getMsg());
				break;
		}
		return req;
	}
	
	/**
	 * 订单结算
	 * @param version
	 * @param token
	 * @param orderInfo
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/settlement")
	@ResponseBody
	public ReqResponse<OrderPayConfirmDTO> settlement(String version,String token,String orderInfo,String sign){
		ReqResponse<OrderPayConfirmDTO> req = new ReqResponse<OrderPayConfirmDTO>();
    	//登录验证
		if(!checkLogin(token, "order/settlement", req)){
			return req;
		}
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, version+token+orderInfo,req)){
					OrderSettlementDTO  orderSettlementInfo = JSON.parseObject(orderInfo, OrderSettlementDTO.class); 
					System.out.println(orderSettlementInfo);
					OrderPayConfirmDTO orderPayConfirm = orderService.settlement(user_id,orderSettlementInfo);
					if(orderPayConfirm == null){
						req.setCode(ErrorMsg.FAIL.getCode());
						req.setMsg(ErrorMsg.FAIL.getMsg());
					}else{
						req.setData(orderPayConfirm);
					}
					
				}
				break;
			default://不支持的版本号
				req.setCode(ErrorMsg.UNSUPPORTED_VERSION.getCode());
				req.setMsg(ErrorMsg.UNSUPPORTED_VERSION.getMsg());
				break;
		}
		return req;
	}
	
	
	
	/**
	 * 订单列表
	 * @param version
	 * @param token
	 * @param order_state 订单状态	
	 * @param sign  md5(version + token + order_state)
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ReqResponse<?> list(String version,String token,String order_state,String sign){
		ReqResponse<?> req = new ReqResponse<Object>();
		
		//是否能继续获取访问
		if(!this.checkLogin(token, "order/list", req)){
			return req;
		}
		//用户id
		String user_id = this.getUserId(token, req);
		if(user_id == null){//用户未登录
			return req;
		}
		
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, version+token+order_state,req)){
					
				}
				
				break;
			default://不支持的版本号
				req.setCode(ErrorMsg.UNSUPPORTED_VERSION.getCode());
				req.setMsg(ErrorMsg.UNSUPPORTED_VERSION.getMsg());
				break;
		}
		
		
		return req;
	}	
	

	
	
	/**
	 * 订单明细
	 * @param version
	 * @param token
	 * @param order_id 	订单id 	
	 * @param sign  md5(version + token + order_id)
	 * @return
	 */
	@RequestMapping(value = "/details")
	@ResponseBody
	public ReqResponse<?> details(String version,String token,String order_id,String sign){
		ReqResponse<?> req = new ReqResponse<Object>();
		
		//是否能继续获取访问
		if(!this.checkLogin(token, "order/details", req)){
			return req;
		}
		//用户id
		String user_id = this.getUserId(token, req);
		if(user_id == null){//用户未登录
			return req;
		}
		
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, version+token+order_id,req)){
					
				}
				 
				break;
			default://不支持的版本号
				req.setCode(ErrorMsg.UNSUPPORTED_VERSION.getCode());
				req.setMsg(ErrorMsg.UNSUPPORTED_VERSION.getMsg());
				break;
		}
		
		
		return req;
	}	

}

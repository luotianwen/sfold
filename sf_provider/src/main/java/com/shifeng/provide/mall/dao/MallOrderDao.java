package com.shifeng.provide.mall.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.shifeng.dao.BaseDao;
import com.shifeng.dto.mall.MallUserAddressDTO;
import com.shifeng.dto.mall.freight.MallShopWareFreight;
import com.shifeng.dto.mall.order.OrderPayConfirmDTO;
import com.shifeng.dto.mall.order.OrderPreviewInfoDTO;
import com.shifeng.dto.mall.order.OrderPreviewWareInfoDTO;
import com.shifeng.dto.mall.order.OrderSettlementDTO;
import com.shifeng.dto.mall.order.OrderSettlementShopDTO;
import com.shifeng.dto.mall.order.OrderWareDTO;
import com.shifeng.entity.order.OrderDetailInfo;
import com.shifeng.entity.order.OrderInfo;
import com.shifeng.entity.order.PorderInfo;
import com.shifeng.provide.entity.ware.WareSKU;
import com.shifeng.response.ReqResponse;
import com.shifeng.util.IdWorker;

/**
 * 
 * @author WinZhong
 *
 */
@Service("mallOrderDao")
public class MallOrderDao {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name = "mallFreightDao")
	private MallFreightDao mallFreightDao;

	/**
	 * 获取订单预览信息
	 * @param products
	 * @param req
	 * @throws Exception 
	 */
	public void getOrderPreviewInfo(List<OrderWareDTO> products, ReqResponse<List<OrderPreviewInfoDTO>> req) throws Exception {
		//预览店铺信息
		List<OrderPreviewInfoDTO> shopList = (List<OrderPreviewInfoDTO>)dao.findForList("mallOrderMapper.getPreviewShop", products);
		//预览商品信息
		List<OrderPreviewWareInfoDTO> wareList = (List<OrderPreviewWareInfoDTO>)dao.findForList("mallOrderMapper.getPreviewWare", products);
		if(shopList != null && shopList.size() != 0){
			Map<String,Integer> map = new HashMap<String,Integer>();
			for(OrderWareDTO p:products){
				map.put(p.getSku(), p.getPcount());
			}
			for(OrderPreviewInfoDTO shop:shopList){
				List<OrderPreviewWareInfoDTO> wares = new ArrayList<OrderPreviewWareInfoDTO>();
				for(OrderPreviewWareInfoDTO ware:wareList){
					if(shop.getShopid() == ware.getShopid()){
						ware.setNumber(map.get(ware.getSku()));
						wares.add(ware);
					}
				}
				shop.setWareList(wares); 
			}
			
		}
		req.setData(shopList);
		
	}

	/**
	 * 订单结算
	 * @param orderSettlementInfo
	 * @param req
	 * @throws Exception 
	 */
	public void saveSettlement(String user_id,OrderSettlementDTO orderSettlementInfo, ReqResponse<OrderPayConfirmDTO> req) throws Exception {
		 //父订单ID
		 String perentId = IdWorker.getId()+"";
		 //提交时间
		 Date subTime = new Date();
		 //获取收货地址
		 MallUserAddressDTO mallUserAddress = (MallUserAddressDTO)dao.findForObject("malluseraddressMapper.getAddressById", 
				 new String[]{user_id,orderSettlementInfo.getAddressId()+""});
		 //详细地址
		 String address = mallUserAddress.getProvinceName()+mallUserAddress.getCityName()+mallUserAddress.getAreaName()+mallUserAddress.getAddress();

		 //父订单
		 PorderInfo porderInfo = new PorderInfo();
		 porderInfo.setPerentId(perentId);
		 porderInfo.setSubTime(subTime);
		 porderInfo.setUserId(user_id);
		 porderInfo.setReceiveName(mallUserAddress.getContacts());
		 porderInfo.setPhoneNumber(mallUserAddress.getPhone());
		 porderInfo.setAddress(address);
		 
		 //订单总金额
		 double p_order_total_price = 0.00;
	 	 //商家优惠金额
	  	 double p_seller_discount = 0.00;
	 	 //订单货款金额（订单总金额-商家优惠金额）
	  	 double p_order_seller_price = 0.00;
	 	 //商品总金额
	  	 double p_totalMoney = 0.00;
	 	 //用户应付金额
	  	 double p_order_payment = 0.00;
	 	 //商品的运费
	  	 double p_freight_price = 0.00;
		 
	  	 //待删除的购物车
	  	 List<Integer> delCartList = new ArrayList<Integer>();
	  	 
		 for(OrderSettlementShopDTO shop:orderSettlementInfo.getShops()){
			 Map<String,Integer> wareMap = new HashMap<String,Integer>();
			 for(OrderWareDTO p:shop.getWares()){
				 wareMap.put(p.getSku(), p.getPcount());
				 if(null != p.getCartId() && p.getCartId() != 0){
					 delCartList.add(p.getCartId());
				 }
			 }
			 ReqResponse<List<MallShopWareFreight>> freightReq = new ReqResponse<List<MallShopWareFreight>>();
			 //计算运费
			 mallFreightDao.getWareFreights(shop.getWares(), orderSettlementInfo.getAddressId()+"", freightReq);
			 List<MallShopWareFreight> shopWareFreightList = freightReq.getData();
			 //运费
		  	 double freight = 0.00;
		  	 if(freightReq.getCode() == 0 && shopWareFreightList != null && shopWareFreightList.size() > 0){
		  		freight = shopWareFreightList.get(0).getFreight();
		  	 }else{
		  		req.setCode(1);
		  		req.setMsg("运费错误");
		  		break;
		  	 }
			 //获取购买商品信息
			 List<WareSKU> skus = (List<WareSKU>)dao.findForList("mallWareMapper.getSettlementWareSku", shop.getWares());
			 //创建订单信息
			 OrderInfo orderInfo = new OrderInfo();
			 orderInfo.setOrderId(IdWorker.getId()+"");
			 orderInfo.setPerentId(perentId);
			 orderInfo.setUserId(user_id);
			 orderInfo.setSubTime(subTime);
			 orderInfo.setReceiveName(mallUserAddress.getContacts());
			 orderInfo.setPhoneNumber(mallUserAddress.getPhone());
			 orderInfo.setAddress(address);
			 //是否结算(1：是；2：否)
			 orderInfo.setIsSettlement(2);
			 //订单状态
			 orderInfo.setOrderStatus(1);
			 orderInfo.setRemark(shop.getRemark());
			 
			 //订单总金额
			 double order_total_price = 0.00;
		 	 //商家优惠金额
		  	 double seller_discount = 0.00;
		 	 //订单货款金额（订单总金额-商家优惠金额）
		  	 double order_seller_price = 0.00;
		 	 //商品总金额
		  	 double totalMoney = 0.00;
		 	 //用户应付金额
		  	 double order_payment = 0.00;
		 	 //商品的运费
		  	 double freight_price = 0.00;
			 
			 //订单明细列表
			 List<OrderDetailInfo> orderDetailList = new ArrayList<OrderDetailInfo>();
			 for(WareSKU sku:skus){
				 //订单明细
				 OrderDetailInfo orderDetailInfo = new OrderDetailInfo();
				 orderDetailInfo.setOrderId(orderInfo.getOrderId());
				 orderDetailInfo.setPid(sku.getId());
				 orderDetailInfo.setSku(sku.getSku());
				 orderDetailInfo.setProductNumber(sku.getNumber());
				 orderDetailInfo.setProductName(sku.getName());
				 orderDetailInfo.setProductImage(sku.getColorPic());
				 orderDetailInfo.setCategory(sku.getCid());
				 orderDetailInfo.setProductStatus(sku.getActivitytype());
				 orderDetailInfo.setInitialPrice(sku.getMarketprice());
				 orderDetailInfo.setSoldPrice(sku.getPrice());
				 orderDetailInfo.setDiscount(sku.getMarketprice()-sku.getPrice());
				 orderDetailInfo.setQuantity(wareMap.get(sku.getSku()));
				 orderDetailInfo.setTotalMoney(orderDetailInfo.getSoldPrice()*orderDetailInfo.getQuantity());
				 orderDetailInfo.setColor(sku.getColorName());
				 orderDetailInfo.setSpecification(sku.getSpecName());
				 orderDetailInfo.setPerentId(perentId);
				 orderDetailList.add(orderDetailInfo);
				 //计算订单总金额
				 p_order_total_price += sku.getMarketprice()*orderDetailInfo.getQuantity();
			 	 //商品总金额
				 p_totalMoney += sku.getMarketprice()*orderDetailInfo.getQuantity();
			 	 //商家优惠金额
				 p_seller_discount +=  orderDetailInfo.getDiscount()*orderDetailInfo.getQuantity();
				 //用户应付金额
				 p_order_payment += orderDetailInfo.getTotalMoney();
				 

				 //计算订单总金额
				 order_total_price += sku.getMarketprice()*orderDetailInfo.getQuantity();
			 	 //商品总金额
				 totalMoney += sku.getMarketprice()*orderDetailInfo.getQuantity();
			 	 //商家优惠金额
				 seller_discount +=  orderDetailInfo.getDiscount()*orderDetailInfo.getQuantity();
				 //用户应付金额
				 order_payment += orderDetailInfo.getTotalMoney();
			 }
			 p_order_total_price += freight;
			 //商品的运费
			 p_freight_price += freight;
			 
			 order_total_price += freight;
			 //商品的运费
			 freight_price += freight;
			 
		 	 //订单货款金额（订单总金额-商家优惠金额）
			 order_seller_price = order_total_price - seller_discount;
			 orderInfo.setOrder_total_price(order_total_price);
			 orderInfo.setSeller_discount(seller_discount);
			 orderInfo.setOrder_seller_price(order_seller_price);
			 orderInfo.setTotalMoney(totalMoney);
			 orderInfo.setOrder_payment(order_payment+freight_price);
			 orderInfo.setFreight_price(freight_price);
			 
			 
			 /**保存订单明细*/
			 dao.save("mallOrderDetailMapper.saveOrderDetail", orderDetailList);
			 /**保存订单*/
			 dao.save("mallOrderMapper.saveOrderInfo", orderInfo);
			 if(Objects.equal(2, shop.getNotPutInvoice()) && shop.getInvoiceId() != null){//是否开发票(1：不开发票；2：开发票)
				 Map<String,Object> map = new HashMap<String,Object>();
				 map.put("user_id", user_id);
				 map.put("invoiceId", shop.getInvoiceId());
				 map.put("orderId", orderInfo.getOrderId());
				 map.put("postName", mallUserAddress.getContacts());
				 map.put("postPhone", mallUserAddress.getPhone());
				 map.put("postAddress", address);
				 map.put("totalMoneyLower", orderInfo.getOrder_payment());
				 /**保存发票信息*/
				 dao.save("mallOrderInvoiceMapper.saveOrderInvoice", map);
			 }
			 
		 }
		 
	 	 //订单货款金额（订单总金额-商家优惠金额）
		 p_order_seller_price = p_order_total_price - p_seller_discount;
		 porderInfo.setOrder_total_price(p_order_total_price);
		 porderInfo.setSeller_discount(p_seller_discount);
		 porderInfo.setOrder_seller_price(p_order_seller_price);
		 porderInfo.setTotalMoney(p_totalMoney);
		 porderInfo.setOrder_payment(p_order_payment+p_freight_price);
		 porderInfo.setFreight_price(p_freight_price);
		 /**保存父订单*/
		 dao.save("mallPorderMapper.savePorder", porderInfo);
		 
		 if(delCartList.size() > 0){//如果商品来自购物车，则删除购物车商品
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("user_id", user_id);
			 map.put("skus", delCartList);
			 /**删除购物车商品*/
			 dao.delete("mallcartMapper.deleteMyCartWare", map);
		 }
		 
		 OrderPayConfirmDTO orderPayConfirm = new OrderPayConfirmDTO();
		 orderPayConfirm.setOrder_total_price(p_order_total_price);
		 orderPayConfirm.setSeller_discount(p_seller_discount);
		 orderPayConfirm.setOrder_seller_price(p_order_seller_price);
		 orderPayConfirm.setTotalMoney(p_totalMoney);
		 orderPayConfirm.setOrder_payment(p_order_payment);
		 orderPayConfirm.setFreight_price(p_freight_price+p_freight_price);
		 orderPayConfirm.setOrderId(perentId);
		 req.setData(orderPayConfirm);
		 
	}

}

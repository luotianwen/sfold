package com.shifeng.seller.shop.controller;

import com.shifeng.entity.shop.ShopCategorySpec;
import com.shifeng.seller.controller.BaseController;
import com.shifeng.seller.entity.users.Users;
import com.shifeng.seller.shop.service.ShopCategorySpecService;
import com.shifeng.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/** 
 * 店铺分类规格属性(p_shop_category_spec)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-28 13:20:44 
 */ 
@Controller
@RequestMapping(value="/shopcategoryspec")
public class ShopCategorySpecController extends BaseController {

	@Resource(name="shopcategoryspecServiceImpl")
	private ShopCategorySpecService shopcategoryspecServiceImpl;

	/**
	 * 新增
	 * @param shopcategoryspec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveShopCategorySpec")
	@ResponseBody
	public Map<String,Object> saveShopCategorySpec(ShopCategorySpec shopcategoryspec,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		shopcategoryspec.setShopid(user.getShopid());
		try {
			shopcategoryspecServiceImpl.saveShopCategorySpec(shopcategoryspec,map);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 修改
	 * @param shopcategoryspec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateShopCategorySpec")
	@ResponseBody
	public Map<String,Object> updateShopCategorySpec(ShopCategorySpec shopcategoryspec,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		try {
			shopcategoryspecServiceImpl.updateShopCategorySpec(shopcategoryspec);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteShopCategorySpec")
	@ResponseBody
 	public Map<String,Object> deleteShopCategorySpec(String id) throws Exception{
 		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		try {
			shopcategoryspecServiceImpl.deleteShopCategorySpec(id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
 	}
 
}

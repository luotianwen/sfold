package com.shifeng.webapi.service.category.impl;

import com.alibaba.fastjson.JSON;
import com.shifeng.dto.mall.MallCategoryDTO;
import com.shifeng.provide.mall.service.MallCategoryService;
import com.shifeng.response.ReqResponse;
import com.shifeng.util.Const;
import com.shifeng.util.redis.RedisTool;
import com.shifeng.webapi.service.category.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "mallCategoryService")
	protected MallCategoryService mallCategoryService;
	

	
	/**
	 * 获取分类列表
	 * @return
	 */
	public List<MallCategoryDTO> getCategoryList() {
		String flag="0";//0标识要更新 1不要
		try {
			flag = RedisTool.get(Const.CATEGORY_FLAG);
			if(StringUtils.isEmpty(flag)){
				flag="0";
			}
		} catch (Exception e) {
			flag="0";
		}
		List<MallCategoryDTO> categoryList = null;
		try {
			String data = RedisTool.get(Const.CATEGORY_CACHE);
			if(StringUtils.isNotBlank(data)){
				categoryList = JSON.parseArray(data, MallCategoryDTO.class);
			}
		} catch (Exception e) {
			logger.error("【从redis获取分类列表缓存数据出错】出错：", e);
		}
		try {
			if(null == categoryList||flag.equals("0")){
				ReqResponse<List<MallCategoryDTO>> result = mallCategoryService.getCategoryList();
				if(result.getCode() == 0){
					categoryList = result.getData();
					RedisTool.set(Const.CATEGORY_CACHE, JSON.toJSONString(categoryList));
					//RedisTool.expire(Const.CATEGORY_CACHE, 1*60*30);
					RedisTool.set(Const.CATEGORY_FLAG, "1");
				}
			}
		} catch (Exception e) {
			logger.error("【获取分类列表】出错：", e);
		}
		return categoryList;
	}

}

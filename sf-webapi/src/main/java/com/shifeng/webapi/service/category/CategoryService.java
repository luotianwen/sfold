package com.shifeng.webapi.service.category;

import java.util.List;

import com.shifeng.dto.mall.MallCategoryDTO;

public interface CategoryService {
	
	/**
	 * 获取分类列表
	 * @return
	 */
	List<MallCategoryDTO> getCategoryList();

}

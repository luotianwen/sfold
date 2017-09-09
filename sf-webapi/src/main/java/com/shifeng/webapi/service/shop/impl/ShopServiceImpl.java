package com.shifeng.webapi.service.shop.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.shifeng.dao.BaseDao;
import com.shifeng.webapi.service.shop.ShopService;

/**
 * 
 * @author WinZhong
 *
 */
@Service("shopServiceImpl")
public class ShopServiceImpl implements ShopService{

    @Resource(name = "baseDaoImpl")
    private BaseDao dao;

	protected Logger logger = Logger.getLogger(this.getClass());

}

package com.shifeng.op.system.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shifeng.dao.BaseDao;
import com.shifeng.entity.system.SysSpecial;
import com.shifeng.op.system.service.SysSpecialService;
import com.shifeng.plugin.page.Page;

/** 
 * 专题页名称(sys_special)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-04 15:22:51 
 */  
@Service("sysspecialServiceImpl")
public class SysSpecialServiceImpl implements SysSpecialService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SysSpecial> findAllSysSpecial(Page page) throws Exception{
		return (List<SysSpecial>) dao.findForList("sysspecialMapper.findAllSysSpecialPage", page);
	}
	
	/**
	 * 根据ID查询
	 */
	public SysSpecial findSysSpecialById(String id) throws Exception{
		return (SysSpecial) dao.findForObject("sysspecialMapper.findSysSpecialById", id);
	}
	
	/**
	 * 新增
	 * @param sysspecial
	 * @throws Exception
	 */
	public void saveSysSpecial(SysSpecial sysspecial) throws Exception{
		dao.save("sysspecialMapper.saveSysSpecial", sysspecial);
	}
	
	/**
	 * 修改
	 * @param sysspecial
	 * @throws Exception
	 */
	public void updateSysSpecial(SysSpecial sysspecial) throws Exception{
		dao.update("sysspecialMapper.updateSysSpecial", sysspecial);
	}
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysSpecial(String id) throws Exception{
		dao.delete("sysspecialMapper.deleteSysSpecial", id);
	}
	
}

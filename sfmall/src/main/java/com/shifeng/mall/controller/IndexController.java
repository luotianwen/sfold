package com.shifeng.mall.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
*    
 */
@Controller
public class IndexController extends BaseController{

	/**
	 * 跳转到网站首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="index")
	public ModelAndView index(ModelAndView mv)throws Exception {
		mv.setViewName("index.btl");
		return mv;
	}	

}

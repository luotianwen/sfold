package com.shifeng.webapi.controller.mall;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shifeng.plugin.page.Page;
import com.shifeng.response.ReqResponse;
import com.shifeng.webapi.common.ApiVersion;
import com.shifeng.webapi.common.ErrorMsg;
import com.shifeng.webapi.controller.BaseController;
import com.shifeng.webapi.service.mall.WareCommentService;

/**
 * 评价
 * @author WinZhong
 *
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 商品评价
	 */
	@Resource(name = "wareCommentServiceImpl")
	protected WareCommentService wareCommentService;
	
	
	/**
	 * 获取商品评论列表
	 * @param version
	 * @param ticket
	 * @param productId
	 * @param currentPage 当前页
	 * @param sign (version+ticket+productId+currentPage)
	 * @return
	 */
	@RequestMapping(value = "/wareComments")
	@ResponseBody
	public ReqResponse<Page> wareComments(String version,String ticket,String productId,Integer currentPage,Integer pageSize,String sign){
		ReqResponse<Page> req = new ReqResponse<Page>();
    	//验证ticket
    	if(!this.visitInspect(ticket,"comment/wareComments", req)){
    		return req;
    	}
    	String content = version+ticket+productId+currentPage;
		if(null == pageSize){
			pageSize =10;
		}else{
			content +=pageSize;
		}
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, content,req)){
					if(null == currentPage){
						currentPage =1;
					}
					Page page = wareCommentService.getWareComments(productId, currentPage,pageSize);
				    req.setData(page);
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
	 * 获取商品评论列表
	 * @param version
	 * @param ticket
	 * @param commentId 评论ID
	 * @param currentPage 当前页
	 * @param sign (version+ticket+commentId+currentPage)
	 * @return
	 */
	@RequestMapping(value = "/wareCommentRepay")
	@ResponseBody
	public ReqResponse<Page> wareCommentRepay(String version,String ticket,String commentId,Integer currentPage,String sign){
		ReqResponse<Page> req = new ReqResponse<Page>();
    	//验证ticket
    	if(!this.visitInspect(ticket,"comment/wareCommentRepay", req)){
    		return req;
    	}
		//验证版本号
		switch (version) {
			case ApiVersion.V_1_0_0:
				//验证签名
				if(this.verifySign(sign, version+ticket+commentId+currentPage,req)){
					if(null == currentPage){
						currentPage =1;
					}
					Page page = wareCommentService.getWareCommentRepay(commentId, currentPage);
				    req.setData(page);
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

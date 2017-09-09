package com.shifeng.mall.product.controller;

import com.shifeng.entity.brand.Brand;
import com.shifeng.entity.search.WareSkuInfo;
import com.shifeng.mall.controller.BaseController;
import com.shifeng.mall.product.service.ProductService;
import com.shifeng.plugin.page.Page;
import com.shifeng.provide.coupon.service.CouponService;
import com.shifeng.provide.mall.service.MallWareCommentService;
import com.shifeng.response.ReqResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品
 */
@Controller
@RequestMapping(value = "product")
public class ProductController extends BaseController{
    @Resource(name="productService")
    private ProductService productService;
    @Resource(name="mallWareCommentService")
    private MallWareCommentService mallWareCommentService;
    @Resource(name="couponService")
    private CouponService couponService;

    /**
     *大家都在买
     */
    @RequestMapping(value = "/everybodybuy")
    @ResponseBody
    public List<WareSkuInfo> everybodybuy(String cid, String pcid, String pid) throws Exception {
         List<WareSkuInfo> skuInfos=productService.everybodybuy(cid,pcid,pid);
        return skuInfos;
    }
    /**
     *猜你喜欢
     */
    @RequestMapping(value = "/guessyoulike")
    @ResponseBody
    public List guessyoulike(String cid,String pcid,String pid) throws Exception {
        List<WareSkuInfo> skuInfos=productService.guessyoulike(cid,pcid,pid);
        return skuInfos;
    }
    /**
     *品牌信息
     */
    @RequestMapping(value = "/getBrandInfoById")
    @ResponseBody
    public Brand getBrandInfoById(String brandid) throws Exception {
        Brand brand=productService.getBandInfoById(brandid);
        return brand;
    }
    /**
     *商品评价
     */
    @RequestMapping(value = "/getComment")
    @ResponseBody
    public ModelAndView getComment(ModelAndView mv, String pid, Page page) throws Exception {
        ReqResponse<Page> pages=mallWareCommentService.getWareComments(pid,page.getCurrentPage(),page.getPageSize());
        if(pages.getCode()==0) {

            Page page2 = pages.getData();
            mv.addObject("page",page2);//List<WareCommentDTO>
            mv.addObject("wareComments",page2.getResultData());
        }
        mv.setViewName(basePath + "product/comment.btl");
        return mv;
    }
}

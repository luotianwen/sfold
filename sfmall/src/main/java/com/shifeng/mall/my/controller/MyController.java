package com.shifeng.mall.my.controller;

import com.shifeng.dto.mall.MallCartShopDTO;
import com.shifeng.dto.mall.MallUserAddressDTO;
import com.shifeng.dto.mall.freight.MallShopWareFreight;
import com.shifeng.dto.mall.order.*;
import com.shifeng.mall.controller.BaseController;
import com.shifeng.mall.entity.user.Users;
import com.shifeng.provide.mall.service.*;
import com.shifeng.provide.system.service.SystemService;
import com.shifeng.provide.usercenter.service.BuyAddressService;
import com.shifeng.response.ReqResponse;
import com.shifeng.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongshi on 2017/4/18.
 */
@Controller
@RequestMapping("/my")
public class MyController extends BaseController {
    //购物车服务
    @Resource(name = "mallCartService")
    private MallCartService mallCartService;
//关注商品
    @Resource(name = "mallFollowWareService")
    protected MallFollowWareService mallFollowWareService;
//关注店铺
    @Resource(name = "mallFollowVenderService")
    protected MallFollowVenderService mallFollowVenderService;
  //收货地址
    @Resource(name = "buyAddressService")
    protected BuyAddressService buyAddressService;
    //系统服务
    @Resource(name="systemService")
    private SystemService systemServiceImpl;
    //订单服务
    @Resource(name = "mallOrderService")
    private MallOrderService mallOrderService;
    //运费服务
    @Resource(name = "mallFreightService")
    private MallFreightService mallFreightService;


    /**
     * 加入购物车
     * @param mv
     * @param sku
     * @param pcount
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/joincart")
    public ModelAndView joincart(ModelAndView mv, String sku, int pcount, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse r=mallCartService.addCartWare(user.getuId(),sku,pcount);
        mv.addObject("code",r.getCode());
        mv.setViewName(basePath + "my/joincart.btl");
        return mv;
    }
    /**
     * 加入购物车
     * @param mv
     * @param sku
     * @param pcount
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatecart")
    @ResponseBody
    public ReqResponse updatecart(ModelAndView mv, String sku, int pcount, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse r=mallCartService.updateCartWare(user.getuId(),sku,pcount);
        return r;
    }
    /**
     * 购物车列表
     * @param mv
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cart")
    @ResponseBody
    public ModelAndView cart(ModelAndView mv, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<List<MallCartShopDTO>> r=mallCartService.getCartWareList(user.getuId());

        mv.addObject("carts",r.getData());
        mv.setViewName(basePath + "my/cart.btl");
        return mv;
    }
    /**
     * 购物车下单页面列表
     * @param mv
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cartorder", method= RequestMethod.POST)
    public ModelAndView cartorder(ModelAndView mv, HttpSession session,String[]skucheck,int []goodsNumberInput) throws Exception {
        if(skucheck==null||skucheck.length==0||goodsNumberInput==null||goodsNumberInput.length==0){
            mv.addObject("");
            mv.setViewName("500");
            return mv;
        }
        session.setAttribute("skucheck",skucheck);
        session.setAttribute("goodsNumberInput",goodsNumberInput);
        mv.setViewName(basePath + "my/cartorder.btl");
        return mv;
    }
    /**
     * 购物车下单页面列表
     * @param mv
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cartorderproduct", method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView cartorderproduct(ModelAndView mv, HttpSession session,Integer addressid) throws Exception {
        String[]skucheck= (String[]) session.getAttribute("skucheck");
        int []goodsNumberInput= (int[]) session.getAttribute("goodsNumberInput");
        if(skucheck==null||skucheck.length==0||goodsNumberInput==null||goodsNumberInput.length==0){
            mv.addObject("");
            mv.setViewName("500");
            return mv;
        }
        //session.setAttribute("skucheck","");
        //session.setAttribute("goodsNumberInput","");
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        List<OrderWareDTO>  orderPreloadings=new ArrayList<OrderWareDTO>();
        for (int i = 0; i < skucheck.length; i++) {
            OrderWareDTO orderPreloading=new OrderWareDTO();
            orderPreloading.setSku(skucheck[i]);
            orderPreloading.setPcount(goodsNumberInput[i]);
            orderPreloadings.add(orderPreloading);
        }

        ReqResponse<List<OrderPreviewInfoDTO>> result = mallOrderService.getOrderPreviewInfo(orderPreloadings);

        List provinces = systemServiceImpl.getAllProvince();
        mv.addObject("provinces",provinces);
        if(result.getCode()==0){
            session.setAttribute("OrderPreviewInfodata",result.getData());
            mv.addObject("data",result.getData());
        }
        String provinceId="";
        ReqResponse<MallUserAddressDTO> add=null;
        if(addressid==null) { add = buyAddressService.getDefaultAddress(user.getuId()); }
         else{  add = buyAddressService.getAddressById(user.getuId(),addressid);  }
        if(add.getCode()==0) {
            provinceId=add.getData().getProvince()+"";
        }

        ReqResponse<List<MallShopWareFreight>> addr=mallFreightService.getWareFreights(orderPreloadings,provinceId);

        mv.addObject("freights",addr.getData());
        mv.setViewName(basePath + "my/cartorderproduct.btl");
        return mv;
    }

    /**
     *收货地址列表
     */
    @RequestMapping(value = "/addressList")
    @ResponseBody
    public ModelAndView addressList(ModelAndView mv,MallUserAddressDTO mallUserAddressDTO, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<List<MallUserAddressDTO>> req=buyAddressService.getAddressList(user.getuId());
        if(req.getCode()==0)
            mv.addObject("datas",req.getData());
        mv.setViewName(basePath + "my/cartshopaddress.btl");
        return mv;
    }
    /**
     *增加收货地址
     */
    @RequestMapping(value = "/addaddress")
    @ResponseBody
    public ReqResponse<String> addaddress(MallUserAddressDTO mallUserAddressDTO, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        mallUserAddressDTO.setUid(user.getuId());
        ReqResponse<String> r=buyAddressService.addAddress(mallUserAddressDTO);
        return r;
    }


    /**
     *下单
     */

    @RequestMapping(value = "/joinorder")
    @ResponseBody
    public ModelAndView joinorder(ModelAndView mv,  HttpSession session,Integer addressId,Integer couponId,Integer eCardId,Integer invoiceId,Integer notPutInvoice,String[] remark) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        OrderSettlementDTO orderSettlementInfo=new OrderSettlementDTO();
        orderSettlementInfo.setAddressId(addressId);
        List<OrderPreviewInfoDTO> freights= (List<OrderPreviewInfoDTO>) session.getAttribute("OrderPreviewInfodata");
        if(freights==null){
            mv.setViewName("500");
            return mv;
        }
        List<OrderSettlementShopDTO> shops=new ArrayList<>();
        int i=0;
        for (OrderPreviewInfoDTO opid:freights) {

            OrderSettlementShopDTO ossd=new OrderSettlementShopDTO();
            ossd.setInvoiceId(invoiceId);
            ossd.setShopid(opid.getShopid());
            ossd.setNotPutInvoice(notPutInvoice);
           //订单备注
             ossd.setRemark(remark[i]);


            if(opid.getWareList()!=null&&opid.getWareList().size()>0) {
                List<OrderWareDTO> wares = new ArrayList<>();
                for (OrderPreviewWareInfoDTO opwi:opid.getWareList() ) {
                    OrderWareDTO owd=new OrderWareDTO();
                    owd.setSku(opwi.getSku());
                    owd.setPcount(opwi.getNumber());
                    wares.add(owd);
                }

                ossd.setWares(wares);
            }
            shops.add(ossd);
        }




        ReqResponse<OrderPayConfirmDTO> result = mallOrderService.settlement(user.getuId(),orderSettlementInfo);




        if(result.getCode() == 0){
            //return result.getData();
        }
        mv.setViewName(basePath + "my/ordersubmission.btl");
        return mv;
    }
    /**
     *下单 支付
     */

    @RequestMapping(value = "/payok")
    @ResponseBody
    public ModelAndView payok(ModelAndView mv,MallUserAddressDTO mallUserAddressDTO, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        mv.setViewName(basePath + "my/buysucceeds.btl");
        return mv;
    }



    /**
     *收藏店铺
     */
    @RequestMapping(value = "/joinbookmark")
    @ResponseBody
    public ReqResponse<String> joinbookmark(int shopid, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<String> r=mallFollowVenderService.addFollowVender(user.getuId(),shopid);
        return r;
    }
    /**
     *收藏商品
     */
    @RequestMapping(value = "/joinsku")
    @ResponseBody
    public ReqResponse<String> joinsku(int sku, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<String> r=mallFollowWareService.addFollowWare(user.getuId(),sku);
        return r;
    }
    /**
     *批量收藏商品
     */
    @RequestMapping(value = "/joinskus")
    @ResponseBody
    public ReqResponse<String> joinskus(int sku[], HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<String> r=mallFollowWareService.addFollowWare(user.getuId(),sku);
        return r;
    }
    /**
     *批量删除购物车
     */
    @RequestMapping(value = "/deleteskus")
    @ResponseBody
    public ReqResponse<String> deleteskus(String skus, HttpSession session) throws Exception {
        Users user = (Users)session.getAttribute(Const.MALL_SESSION_USER);
        ReqResponse<String> r=mallCartService.deleteCartWare(user.getuId(),skus);
        return r;
    }
}

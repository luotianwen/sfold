package com.shifeng.mall.product.controller;

import com.shifeng.entity.search.SkuInfo;
import com.shifeng.entity.search.WareSkuInfo;
import com.shifeng.mall.controller.BaseController;
import com.shifeng.mall.product.dto.ColorDTO;
import com.shifeng.mall.product.dto.SpecDTO;
import com.shifeng.mall.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yongshi on 2017/4/11.
 */
@Controller
public class DetailController extends BaseController  {

    @Resource(name = "searchServiceImpl")
    protected SearchService searchService;
    @RequestMapping(value = "detail/{sku}")
    public ModelAndView detail(ModelAndView mv,@PathVariable String sku)throws Exception {
         //sku信息
        WareSkuInfo wareSkuInfo = searchService.getWareSkuInfo(sku);
        if(wareSkuInfo!=null&&wareSkuInfo.getState()==1)
        {
            mv.addObject("detail", wareSkuInfo);
            handle(mv,wareSkuInfo);
            mv.setViewName(basePath + "detail/sku.btl");
        }
        else{
            mv.setViewName(basePath + "sku404.btl");
        }
        return mv;
    }

    private void handle(ModelAndView mv,WareSkuInfo wareSkuInfo){
            List<SkuInfo> skuInfos=searchService.getSkuInfo(wareSkuInfo.getPid()+"");
            List<SpecDTO>specs=new ArrayList<>();
            List<ColorDTO>colors=new ArrayList<>();
            for (SkuInfo skuInfo:skuInfos ) {
                if(skuInfo.getColorid()==wareSkuInfo.getColorid()&&skuInfo.getSpecid()==wareSkuInfo.getSpecid()){
                    ColorDTO cd = new ColorDTO();
                    cd.setId(skuInfo.getSku());
                    cd.setName(skuInfo.getColorName());
                    cd.setImg(skuInfo.getColorPic());
                    if (null == skuInfo.getStocks() || 0 == skuInfo.getStocks()) {
                        cd.setStatus(0);
                    } else {
                        cd.setStatus(1);
                    }
                    cd.setCheck(1);
                    colors.add(cd);
                    SpecDTO sd = new SpecDTO();
                    sd.setId(skuInfo.getSku());
                    sd.setName(skuInfo.getSpecName());
                    if (null == skuInfo.getStocks() || 0 == skuInfo.getStocks()) {
                        sd.setStatus(0);
                    } else {
                        sd.setStatus(1);
                    }
                    sd.setCheck(1);
                    specs.add(sd);
                }
                if(skuInfo.getColorid()!=wareSkuInfo.getColorid()&&skuInfo.getSpecid()==wareSkuInfo.getSpecid()) {
                    ColorDTO cd = new ColorDTO();
                    cd.setId(skuInfo.getSku());
                    cd.setName(skuInfo.getColorName());
                    cd.setImg(skuInfo.getColorPic());
                    if (null == skuInfo.getStocks() || 0 == skuInfo.getStocks()) {
                        cd.setStatus(0);
                    } else {
                        cd.setStatus(1);
                    }
                    colors.add(cd);
                }
                if(skuInfo.getColorid()==wareSkuInfo.getColorid()&&skuInfo.getSpecid()!=wareSkuInfo.getSpecid()) {
                    SpecDTO sd = new SpecDTO();
                    sd.setId(skuInfo.getSku());
                    sd.setName(skuInfo.getSpecName());
                    if (null == skuInfo.getStocks() || 0 == skuInfo.getStocks()) {
                        sd.setStatus(0);
                    } else {
                        sd.setStatus(1);
                    }
                    specs.add(sd);
                }

            }
            Collections.sort(colors, new Comparator<ColorDTO>() {
                        @Override
                        public int compare(ColorDTO colorDTO, ColorDTO t1) {
                            return Integer.parseInt(colorDTO.getId())>(Integer.parseInt(t1.getId()))?1:-1;
                        }
                    }
            );
            Collections.sort(specs, new Comparator<SpecDTO>() {
                        @Override
                        public int compare(SpecDTO colorDTO, SpecDTO t1) {
                            return Integer.parseInt(colorDTO.getId())>(Integer.parseInt(t1.getId()))?1:-1;
                        }
                    }
            );
            mv.addObject("specs", specs);
            mv.addObject("colors", colors);
            mv.addObject("skuInfos", skuInfos);
    }
}

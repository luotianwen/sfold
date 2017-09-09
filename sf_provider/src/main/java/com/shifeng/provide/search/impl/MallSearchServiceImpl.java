package com.shifeng.provide.search.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.shifeng.dao.BaseDao;
import com.shifeng.dto.ware.HotWareDTO;
import com.shifeng.dto.ware.WareDTO;
import com.shifeng.dto.ware.WareSkuDTO;
import com.shifeng.entity.search.SearchParameter;
import com.shifeng.entity.search.SkuInfo;
import com.shifeng.entity.search.Suggest;
import com.shifeng.entity.search.WareSkuInfo;
import com.shifeng.plugin.page.SolrPage;
import com.shifeng.provide.search.MallSearchService;
import com.shifeng.solr.MyHttpSolrClient;
import com.shifeng.util.Const;
import com.shifeng.util.MapTurnBean;
import com.shifeng.util.PinYinUtil;
import com.shifeng.util.SolrUtils;
import com.shifeng.util.redis.RedisTool;

 

/**
 * 
 * @author WinZhong
 *
 */
//@Service("searchServiceImpl")
@Service(timeout=1200000)
public class MallSearchServiceImpl implements MallSearchService{

    @Resource(name = "baseDaoImpl")
    private BaseDao dao;

    @Resource(name = "wareSolrClient")
    private MyHttpSolrClient wareSolrClient;

    @Resource(name = "productSolrClient")
    private MyHttpSolrClient productSolrClient;

    @Resource(name = "suggestSolrClient")
    private MyHttpSolrClient suggestSolrClient;

    @Resource(name = "skuSolrClient")
    private MyHttpSolrClient skuSolrClient;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * 商品搜索
	 * @param searchParameter
	 * @param page
	 * @return
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	/*public SolrPage wareSearch(SearchParameter searchParameter, SolrPage page){
		System.out.println("搜索条件参数："+searchParameter.toString());
		System.out.println("分页参数："+page.toString());

		try {
			// 创建查询条件
			SolrQuery query = new SolrQuery();
			
			query.setQuery("q:" + searchParameter.getKeyword());
			System.err.println("**************《开始搜索，关键字："+searchParameter.getKeyword()+"》******************");
			
			*//**=======设置搜索过滤条件=======*//*
			//商品状态(1在售2下架3删除)
			query.addFilterQuery("state:1");
			
			*//**=======设置搜索分页参数=======*//*
			//从多少条开始
			query.setStart((page.getPageNow()-1)*page.getPageSize());  
			//显示几条
			query.setRows(page.getPageSize());
			
			*//**权重设置**//*
			//加权
			query.set("defType","edismax");
			// 最小匹配
			query.set("mm", 100%50);
			设置需要查询的Field权重   
			 * 在Solr权重的设置中，所有权重标准为1，意思是当权重设置大于1时，
			 * 代表这个字段的权重变大，如果权重设置小于1并且大于0的时候，代表这个字段权重变小 。
			
			query.set("qf","name^5");
			
			*//**=======设置排序=======*//*
			//0：综合；1：销量；2：价格从低到高；3：价格从高到低
			switch (searchParameter.getSort()) {
				case 1:
					//按照销量排序
			   		query.set("sort", "evaluate desc");  
					break;
				case 2:
					//按照价格从低到高排序
			   		query.set("sort", "price asc");
					break;
				case 3:
					//按照价格从高到低排序
			   		query.set("sort", "price desc");
					break;
				default:
					break;
			}
			
			*//**=======设置关键字高亮=======*//*
			if(searchParameter.isHighlight()){
				System.out.println("开启关键词高亮");
			    // 开启高亮组件  
			    query.setHighlight(true); 
			    // 字段开启了高亮
			    query.addHighlightField("name"); 
			    // 以下两个方法主要是在高亮的关键字前后加上html代码 
			    query.setHighlightSimplePre("<font color='red'>"); 
			    query.setHighlightSimplePost("</font>");
			}
			
			//指定文档结果中应返回的 Field 集
			query.set("fl", "name");

			// 查询并返回结果
			QueryResponse queryResponse = wareSolrClient.getHttpSolrClient().query(query,METHOD.POST);
			//获取请求结果
			List<Ware> wareList = (List<Ware>) queryResponse.getBeans(Ware.class);
			
			*//**=======如果开启字段高亮，则获取高亮结果=======*//*
			if(searchParameter.isHighlight()){
			    //获取所有高亮的字段  
			    Map<String,Map<String,List<String>>> highlightMap = queryResponse.getHighlighting();
			    for(Ware ware:wareList){
			        	//获取高亮内容
			            List<String> highlightList = highlightMap.get(ware.getId()).get("name");  
			            if(highlightList!=null && highlightList.size()>0){ 
				           	 //赋值高亮字段
			            	 ware.setHighlightName(highlightList.get(0));
				           	 System.out.println(highlightList.get(0));  
			            }
			    }
			}
			
			//获取并设置总条数 及查询结果
			page.setQueryResult(queryResponse.getResults().getNumFound(),wareList);  
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return page;
	}
*/

	
	/**
	 * 关键词提示
	 * @param keyword
	 * @return
	 * @
	 */
	public List<Suggest> suggest(String keyword) {
		return terms(keyword);
	}
	
	
	/**
	 * 关键词提示包含词频统计
	 * @param keyword
	 * @return
	 * @
	 */
	public List<Suggest> terms(String keyword) {
		
		List<Suggest> sugList = new ArrayList<Suggest>();
		if(keyword == null){
			return sugList;
		}
		sugList = this.suggest(keyword, sugList);
		if(sugList.size()>0){
			return sugList;
		}

		//System.out.println("sugList.size="+sugList.size()); 
		try {
			SolrQuery query = new SolrQuery();
			//转义特殊字符，防止注入
			keyword = ClientUtils.escapeQueryChars(keyword);

	        query.set("q", "*:*");  
	        query.set("qt", "/terms");  
	  
	        query.set("terms", "true");  
	        query.set("terms.fl", "q");  
	          
	        //指定下限  
	 /*   query.set("terms.lower", "背"); //开始的字符  
	      query.set("terms.lower.incl", "true");  
	      query.set("terms.mincount", "1");  
	      query.set("terms.maxcount", "100");  */
	  
	 
	       query.set("terms.regex", keyword+"+.*");  
	       query.set("terms.regex.flag", "case_insensitive");  
	       
	       query.set("terms.limit", "10");  
//	       query.set("terms.upper", ""); //结束的字符  
//	       query.set("terms.upper.incl", "false");  
//	        
//	       query.set("terms.raw", "true");  
	  
	        query.set("terms.sort", "count");//terms.sort={count|index} -如果count，各种各样的条款术语的频率（最高计数第一）。 如果index，索引顺序返回条款。默认是count  
	  
	        // 查询并获取相应的结果！  
	        QueryResponse response = wareSolrClient.getHttpSolrClient().query(query,METHOD.POST);  
	        // 获取相关的查询结果  
	        if (response != null) {  
	            TermsResponse termsResponse = response.getTermsResponse();  
	            if (termsResponse != null) {  
	                Map<String, List<TermsResponse.Term>> termsMap = termsResponse.getTermMap();  
	                for (Map.Entry<String, List<TermsResponse.Term>> termsEntry : termsMap.entrySet()) {  
	                    System.out.println("Field Name: " + termsEntry.getKey());  
	                    List<TermsResponse.Term> termList = termsEntry.getValue();  
	                    for (TermsResponse.Term term : termList) {  
	                        System.out.println(term.getTerm() + " : "+ term.getFrequency());  
	                        Suggest sug = new Suggest(term);
	                        sugList.add(sug);
	                    }  
	                }  
	            }  
	  
	        }  
		} catch (SolrServerException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sugList;
	}
	
	/**
	 * 关键词提示支持拼音中文混合
	 * @param keyword
	 * @param sugList
	 */
	public List<Suggest> suggest(String keyword,List<Suggest> sugList) {
		try {
			keyword = keyword.trim();
			//含有英文字符
			if(keyword.matches(".*[a-zA-z].*")){
				//含有中文字符
				if(keyword.matches(".*[\u4e00-\u9fa5].*")){
					keyword = "("+keyword+"* OR "+ PinYinUtil.getFullSpell(keyword)+"*)";
				}else{
					keyword += "*";
					//含有英文字符
					//keyword = "("+keyword+"* OR "+PinYinUtil.getFirstSpell(keyword)+"* OR " + PinYinUtil.getFullSpell(keyword)+"*)";
				}
			}/*else if(keyword.matches("[0-9]*")){//如果都为数字，匹配是否为sku编号
				
			}*/else{
				keyword += "*";
			}
			System.out.println("keyword:"+keyword);
			// 创建查询参数以及设定的查询参数
			SolrQuery query = new SolrQuery();
			query.setQuery("sug:"+keyword+" AND p_count:[1 TO *]"); 
			//指定文档结果中应返回的 Field 集
			query.set("fl", "keyword,p_count");
			//按结果大小排序
			query.set("sort", "p_count desc");
			// 查询并返回结果
			QueryResponse queryResponse = suggestSolrClient.getHttpSolrClient().query(query,METHOD.POST);
			
			sugList = queryResponse.getBeans(Suggest.class);
			//获取请求结果
			SolrDocumentList solrResultList = queryResponse.getResults();  
			for(int i=0,len=solrResultList.size();i<len;i++){  
			   	 SolrDocument doc = solrResultList.get(i);
			     System.out.println(doc.getFieldValue("keyword")+"\t\t"+doc.getFieldValue("p_count"));  
			 }
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sugList;
	}
	

	
	/**
	 * 商品搜索
	 * @param searchParameter 搜索参数
	 * @param page	分页参数
	 * @return
	 */
	public SolrPage searchWare(SearchParameter searchParameter, SolrPage page) {

		SolrQuery query = new SolrQuery();

		query.setQuery("sq:(" + searchParameter.getKeyword().replaceAll("\\s+", " AND ") + ")");//(休闲 AND 白色)
		// 商品状态(1在售2下架3删除)
		query.addFilterQuery("state:1");

		System.out.println("开启关键词高亮");
		// 开启高亮组件
		query.setHighlight(true);
		// 字段开启了高亮
		query.addHighlightField("highlightWareName");
		// 以下两个方法主要是在高亮的关键字前后加上html代码
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");

		// 从多少条开始
		query.setStart((page.getPageNow() - 1) * page.getPageSize());
		// 显示几条
		query.setRows(page.getPageSize());

		// 设为true，表示结果需要分组
		query.setParam(GroupParams.GROUP, "true");
		// 需要分组的字段
		query.setParam(GroupParams.GROUP_FIELD, "pid");
		// 指定每组结果开始位置/偏移量
		// query.setParam(GroupParams.GROUP_OFFSET, "0");
		// 每组返回多数条结果,默认1
		query.setParam(GroupParams.GROUP_LIMIT, "50");
		query.setParam(GroupParams.GROUP_FORMAT, "grouped");
		// 设为true时，Solr将返回分组数量，默认fasle
		query.setParam(GroupParams.GROUP_TOTAL_COUNT, "true");
		//指定文档结果中应返回的 Field 集
		query.set("fl", "id,pid,highlightWareName,pName,logo,marketprice,price,colorName,specName,colorPic");
        //覆盖schema.xml的defaultOperator（有空格时用"AND"还是用"OR"操作逻辑），一般默认指定。必须大写  
        //query.set("q.op","AND");  
		try {
			// 查询并返回结果
			QueryResponse queryResponse = productSolrClient.getHttpSolrClient().query(query, METHOD.POST);
			GroupResponse groupResponse = queryResponse.getGroupResponse();
			// 获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = queryResponse.getHighlighting();
			System.out.println(highlightMap);
			 //总条数
			long numFound = 0;
			List<GroupCommand> groupList = groupResponse.getValues();
			List<WareDTO> wareList = new ArrayList<WareDTO>();
			for (GroupCommand groupCommand : groupList) {
				numFound = groupCommand.getNGroups();
				List<Group> groups = groupCommand.getValues();
				for (Group group : groups) {
					 SolrDocumentList solrDocumentList = group.getResult();  
					 WareDTO ware = null;
					 List<WareSkuDTO> skuList = null;
					 for (SolrDocument doc : solrDocumentList) {
						 if(null == ware){
			            		ware = new WareDTO();
		            			List<String> highlightWareName =  highlightMap.get(doc.get("id")).get("highlightWareName");
		            			if(null == highlightWareName || highlightWareName.size() == 0){
		            				ware.setpName(doc.get("pName").toString());
		            			}else{
			            			ware.setpName(highlightWareName.get(0));
		            			}	
			            		skuList = new ArrayList<WareSkuDTO>();
			            		WareSkuDTO wareSku = new WareSkuDTO();
								skuList.add(MapTurnBean.mapToBean(doc, wareSku));
								if(null == wareSku.getColorName() || StringUtils.isBlank(wareSku.getColorName())){
									wareSku.setColorPic(doc.get("logo").toString());
								}
			             }else{
			            	 if(null != doc.get("colorName")){
				            	 WareSkuDTO wareSku = new WareSkuDTO();
								 skuList.add(MapTurnBean.mapToBean(doc, wareSku));
								 System.out.println("颜色："+wareSku.getColorName());
			            	 }
			             }
						 
					 }
					 ware.setSkuList(skuList);
					 wareList.add(ware);
				}
			}
			//获取并设置总条数 及查询结果
			page.setQueryResult(numFound,wareList);  
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return page;
	}
	

	
	/**
	 * 获取商品sku详情
	 * @param sku 商品sku
	 * @return
	 */
	public WareSkuInfo getWareSkuInfo(String sku) {
		if(StringUtils.isEmpty(sku)){
			return null;
		}
		String flag="0";
		try{
			flag=RedisTool.get(Const.SKU_CACHE_FLAG);
			}catch (Exception e){
			flag="0";
		}
		if(StringUtils.isEmpty(flag)){
			flag="0";
		}
        if(flag.equals("0")) {
			return getCacheWareSkuInfo(sku);
		}

		String key=String.format(Const.SKU_DETAIL_CACHE,sku);
		String ws=RedisTool.get(key);
		if(StringUtils.isEmpty(ws)){
			return getCacheWareSkuInfo(sku);
		}

		return JSON.parseObject(ws, WareSkuInfo.class);
	}
	private WareSkuInfo getCacheWareSkuInfo(String sku){
		String key=String.format(Const.SKU_DETAIL_CACHE,sku);
		SolrQuery query = new SolrQuery();
		query.setQuery("id:" + SolrUtils.escapeQueryChars(sku));
		// 从多少条开始
		query.setStart(0);
		// 显示几条
		query.setRows(1);
		//指定文档结果中应返回的 Field 集
		query.set("fl", "id,pid,stocks,weight,brandName,marketprice,price,colorid,number,cid,pcid,specid,colorName,specName,starttime,endtime,activitytype,activityprice,activitystocks,imgs,shopid,shopName,shopLogo,brandid,pName,recommend,listing,parameter,state,after_service,freight_master,goods_subtitle,logo,length,width,height,is_seven_return,description,phone_describe");
		try {
			// 查询并返回结果
			QueryResponse queryResponse = skuSolrClient.getHttpSolrClient().query(query, METHOD.POST);
			//System.out.println(queryResponse.getResults());
			//获取请求结果
			List<WareSkuInfo> wareInfoList = (List<WareSkuInfo>) queryResponse.getBeans(WareSkuInfo.class);
			if (null == wareInfoList || wareInfoList.size() == 0) {

			} else {
				WareSkuInfo ws=wareInfoList.get(0);
				String str= JSON.toJSONString(ws);
				RedisTool.set(key,str);
				RedisTool.expire(key,Const.SKU_DETAIL_CACHE_TIME);
				RedisTool.set(Const.SKU_CACHE_FLAG,"1");
				return ws;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<SkuInfo>getSkuInfo(String id){
		{
			if(StringUtils.isEmpty(id)){
				return null;
			}
			String flag="0";
			try{
				flag=RedisTool.get(Const.PRODUCT_CACHE_FLAG);
			}catch (Exception e){
				flag="0";
			}
			if(StringUtils.isEmpty(flag)){
				flag="0";
			}
			if(flag.equals("0")) {
				return getCacheSkuInfo(id);
			}

			String key=String.format(Const.PRODUCT_DETAIL_CACHE,id);
			String ws=RedisTool.get(key);
			if(StringUtils.isEmpty(ws)){
				return getCacheSkuInfo(id);
			}

			return JSON.parseArray(ws, SkuInfo.class);
		}
	}
	/**
	 * 获取商品SKU列表
	 * @param pid 商品id
	 * @return
	 */
	public List<SkuInfo> getCacheSkuInfo(String pid) {
		String key=String.format(Const.PRODUCT_DETAIL_CACHE,pid);
		SolrQuery query = new SolrQuery(); 
	    query.setQuery("pid:"+SolrUtils.escapeQueryChars(pid));
		// 从多少条开始
		query.setStart(0);
		// 显示几条
		query.setRows(200);
		//指定文档结果中应返回的 Field 集
		query.set("fl", "id,pid,stocks,marketprice,price,colorid,specid,colorName,specName,starttime,endtime,activitytype,activityprice,activitystocks,imgs,colorPic");
		try {
			// 查询并返回结果
			QueryResponse queryResponse = skuSolrClient.getHttpSolrClient().query(query,METHOD.POST);
			//System.out.println(queryResponse.getResults());
			//获取请求结果
			List<SkuInfo> skuInfoList = (List<SkuInfo>) queryResponse.getBeans(SkuInfo.class);
			if(null == skuInfoList || skuInfoList.size() == 0){
				
			}else{

				String str= JSON.toJSONString(skuInfoList);
				RedisTool.set(key,str);
				RedisTool.expire(key,Const.PRODUCT_DETAIL_CACHE_TIME);
				RedisTool.set(Const.PRODUCT_CACHE_FLAG,"1");
				return skuInfoList;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 获取店铺最热商品
	 * @param shopid 店铺id
	 * @return
	 */
	public List<HotWareDTO> getShopHotWare(int shopid) {
		SolrQuery query = new SolrQuery(); 
	    query.setQuery("shopid:"+shopid);
		// 从多少条开始
		query.setStart(0);
		// 显示几条
		query.setRows(6);
		//按结果大小排序
		query.set("sort", "salesVolume desc");
		//指定文档结果中应返回的 Field 集
		query.set("fl", "id,pid,highlightWareName,pName,logo,marketprice,price,colorName,specName,colorPic");
		try {
			// 查询并返回结果
			QueryResponse queryResponse = skuSolrClient.getHttpSolrClient().query(query,METHOD.POST);
			//获取请求结果 
			List<HotWareDTO> skuInfoList = (List<HotWareDTO>) queryResponse.getBeans(HotWareDTO.class);
			if(null == skuInfoList || skuInfoList.size() == 0){
				
			}else{
				return skuInfoList;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

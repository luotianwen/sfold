package com.shifeng.provide.mall.dao;

import com.shifeng.dao.BaseDao;
import com.shifeng.dto.mall.MallUserInvoiceDTO;
import com.shifeng.response.ReqResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongshi on 2017/4/21.
 */
@Service("mallUserInvoiceDao")
public class MallUserInvoiceDao {
    @Resource(name = "baseDaoImpl")
    private BaseDao dao;

    public void addInvoice(MallUserInvoiceDTO address, ReqResponse<String> req)throws Exception {

        dao.save("malluserinvoiceMapper.saveMallUserInvoice", address);
        if(address.getId() > 0){
            req.setCode(0);
            req.setMsg("添加用户发票地址成功");
        }else{
            req.setCode(1);
            req.setMsg("添加用户发票地址失败");
        }
    }

    public void deleteInvoice(String user_id, int invoiceId, ReqResponse<String> req) throws Exception{
        int rows = (int) dao.update("malluserinvoiceMapper.deleteMallUserInvoice", new String[]{ invoiceId+""});
        if (rows == 0) {
            req.setCode(1);
            req.setMsg("删除发票地址失败");
        } else {
            req.setCode(0);
            req.setMsg("删除发票地址成功");
        }
    }

    public void getInvoiceById(String user_id, int invoiceId, ReqResponse<MallUserInvoiceDTO> req) throws Exception{
        MallUserInvoiceDTO addres = (MallUserInvoiceDTO)dao.findForObject("malluserinvoiceMapper.findMallUserInvoiceById",  new String[]{invoiceId+""});
        req.setData(addres);
    }

    public void getInvoiceList(String userId, ReqResponse<List<MallUserInvoiceDTO>> req) throws Exception{
        List<MallUserInvoiceDTO> addresList = (List<MallUserInvoiceDTO>)dao.findForList("malluserinvoiceMapper.findAllMallUserInvoice", userId);
        req.setData(addresList);
    }

    public void updateInvoice(MallUserInvoiceDTO invoice, ReqResponse<String> req) throws Exception{
        int rows = (int) dao.update("malluserinvoiceMapper.updateMallUserInvoice", invoice);
        if (rows == 0) {
            req.setCode(1);
            req.setMsg("设置默认发票地址失败");
        } else {
            req.setCode(0);
            req.setMsg("设置默认发票地址成功");
        }
    }
}

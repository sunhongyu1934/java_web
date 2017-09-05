package com.inno.services.impl;

import com.inno.bean.DauBean.InvestBean;
import com.inno.dao.DaoFactory;
import com.inno.dao.InvestDao;
import com.inno.services.InvestService;
import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2017/7/1.
 */
public class InvestServiceImpl implements InvestService {
    InvestDao dao= DaoFactory.getInstance().getInvestDao("com.inno.dao.impl.InvestDaoImpl");
    @Override
    public String selectInv(String year_id, String indu_id, String chain_id, String stage_id) {
        InvestBean invest=dao.selectInv(year_id,indu_id,chain_id,stage_id);
        JSONObject js=JSONObject.fromObject(invest);
        return js.toString();
    }
}

package com.inno.dao;

import com.inno.bean.DauBean.InvestBean;

/**
 * Created by Administrator on 2017/7/1.
 */
public interface InvestDao {
    public InvestBean selectInv(String year_id, String indu_id, String chain_id, String stage_id);
}

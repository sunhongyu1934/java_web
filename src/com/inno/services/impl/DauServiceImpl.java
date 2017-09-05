package com.inno.services.impl;

import com.inno.bean.DauBean.AppXin;
import com.inno.bean.DauBean.QueryResult;
import com.inno.dao.DaoFactory;
import com.inno.dao.DauDao;
import com.inno.services.DauService;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public class DauServiceImpl implements DauService{
    DauDao dao= DaoFactory.getInstance().getDauDao("com.inno.dao.impl.DauDaoImpl");

    @Override
    public List<AppXin> getApp(String limit) {
        return dao.getAll(limit);
    }

    @Override
    public QueryResult getSecondDetail(String firstname) {
        return dao.getSecondDetail(firstname);
    }

    @Override
    public QueryResult getProjectDetail(String firstname,String secondname) {
        return dao.getProjectDetail(firstname,secondname);
    }
}

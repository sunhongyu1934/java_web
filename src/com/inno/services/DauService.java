package com.inno.services;

import com.inno.bean.DauBean.AppXin;
import com.inno.bean.DauBean.QueryResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface DauService {
    public List<AppXin> getApp(String limit);
    public QueryResult getSecondDetail(String firstname);
    public QueryResult getProjectDetail(String firstname, String secondname);
}

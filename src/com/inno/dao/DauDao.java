package com.inno.dao;

import com.inno.bean.DauBean.AppXin;
import com.inno.bean.DauBean.QueryResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface DauDao {
    public List<AppXin> getAll(String limit);
    public QueryResult getSecondDetail(String firstClassId);
    public QueryResult getProjectDetail(String firstClassId, String secondClassId);
}

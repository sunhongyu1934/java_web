package com.inno.dao;

import com.inno.bean.DauBean.Spiderlog;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface InsertDao {
    public int InsertTable(String json);
    public int InsertLog(Spiderlog spiderlog);
}

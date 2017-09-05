package com.inno.bean.DauBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public class InsertBean {
    public TablePro tablePro;
    public static class TablePro{
        public List<String> fields;
        public String table;
    }

    public List<Detail> detail;
    public static class Detail{
        public String field;
        public String content;
    }
}

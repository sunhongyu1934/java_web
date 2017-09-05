package com.inno.bean.DauBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
public class QueryResult {
    private List id_arry;

    private List name_arry;

    private List dau_arry;

    private List new_id_arry;

    private List new_name_arry;

    private List new_dau_arry;

    private List date_time;


    public QueryResult() {
    }

    public List getNew_id_arry() {
        return new_id_arry;
    }

    public void setNew_id_arry(List new_id_arry) {
        this.new_id_arry = new_id_arry;
    }

    public List getNew_name_arry() {
        return new_name_arry;
    }

    public void setNew_name_arry(List new_name_arry) {
        this.new_name_arry = new_name_arry;
    }

    public List getNew_dau_arry() {
        return new_dau_arry;
    }

    public void setNew_dau_arry(List new_dau_arry) {
        this.new_dau_arry = new_dau_arry;
    }

    public List getId_arry() {
        return id_arry;
    }

    public void setId_arry(List id_arry) {
        this.id_arry = id_arry;
    }

    public List getName_arry() {
        return name_arry;
    }

    public void setName_arry(List name_arry) {
        this.name_arry = name_arry;
    }

    public List getDau_arry() {
        return dau_arry;
    }

    public void setDau_arry(List dau_arry) {
        this.dau_arry = dau_arry;
    }

    public List getDate_time() {
        return date_time;
    }

    public void setDate_time(List date_time) {
        this.date_time = date_time;
    }
}

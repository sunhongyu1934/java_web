package com.inno.bean.DauBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
public class InvestBean {
    public List<String> month;
    public List<String> num;
    public List<String> amount;
    public String count_value;


    public String getCount_value() {
        return count_value;
    }

    public void setCount_value(String count_value) {
        this.count_value = count_value;
    }

    public List<String> getMonth() {
        return month;
    }

    public void setMonth(List<String> month) {
        this.month = month;
    }

    public List<String> getNum() {
        return num;
    }

    public void setNum(List<String> num) {
        this.num = num;
    }

    public List<String> getAmount() {
        return amount;
    }

    public void setAmount(List<String> amount) {
        this.amount = amount;
    }
}

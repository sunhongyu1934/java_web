package com.inno.services.impl;

import com.google.gson.Gson;
import com.inno.bean.DauBean.InsertBean;
import com.inno.bean.DauBean.Spiderlog;
import com.inno.dao.DaoFactory;
import com.inno.dao.InsertDao;
import com.inno.services.InsertService;
import com.inno.utils.FileSizeUtils;
import com.inno.utils.Md5Utils;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/27.
 */
public class InsertServiceImpl implements InsertService {
    InsertDao dao= DaoFactory.getInstance().getInsertDao("com.inno.dao.impl.InsertDaoImpl");

    @Override
    public String insertTable(String json) {
        String md5= Md5Utils.getMD5String(json);
        String size= FileSizeUtils.getStringSize(json)+"k";
        if(StringUtils.isEmpty(size)){
            size="0k";
        }
        Gson gson=new Gson();
        InsertBean insert=gson.fromJson(json,InsertBean.class);
        String table=insert.tablePro.table;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String spidertime=simpleDateFormat.format(date);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        String datadate=simpleDateFormat1.format(date);
        int fl= 1;

        Spiderlog sp=new Spiderlog();
        sp.setFile_md5(md5);
        sp.setFile_size(size);
        sp.setFile_name(table);
        sp.setSpider_time(spidertime);
        sp.setData_date(datadate);
        sp.setStore_data_flag(String.valueOf(fl));
        int f=dao.InsertLog(sp);

        String result=null;

        if(fl>0&&f>0){
            result="insert spider success and insert log success";
        }else if(fl>0&&f==0){
            result="insert spider success and insert log error";
        }else if(fl==0&&f==0){
            result="insert all error";
        }

        return result;
    }
}

package com.inno.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/6/27.
 */
public class FileSizeUtils {
    public static String getFileSize(String filepath){
        File f= new File(filepath);
        if (f.exists() && f.isFile()){
            float le=f.length()/1000;
            DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p=decimalFormat.format(le);//format 返回的是字符串
            return p;
        }else{
            return null;
        }
    }

    public static String getStringSize(String str){
        if(StringUtils.isNotEmpty(str)) {
            float le=(float)str.length()/1000;
            DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            if(le<1){
                decimalFormat=new DecimalFormat("0.00");
            }
            String p=decimalFormat.format(le);//format 返回的是字符串
            return p;
        }else{
            return null;
        }
    }
}

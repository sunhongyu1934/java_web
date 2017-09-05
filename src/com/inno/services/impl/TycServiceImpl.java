package com.inno.services.impl;

import com.inno.services.TycService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
public class TycServiceImpl implements TycService {
    @Override
    public void Exec(List<String[]> list,String zhua) {
        List<String> li;
        for(int x=0;x<list.size();x++){
            ProcessBuilder pt=new ProcessBuilder();
            pt.directory(new File("/home/spider/java_spider/implement"));
            li=new ArrayList<String>();
            //li.add("nohup");
            li.add("sh");
            li.add("/home/spider/java_spider/implement/tycquan.sh");
            li.add(list.get(x)[0]);
            li.add(list.get(x)[1]);
            li.add(list.get(x)[2]);
            li.add(list.get(x)[3]);
            li.add(zhua);
            li.add("/data1/spider_log_new/tyclog"+(x+1));
            //li.add("&");
            pt.command(li);
            pt.redirectErrorStream(true);
            try {
                Process ps=pt.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> ReadXls(InputStream in) {
        try {
            HSSFWorkbook hssf=new HSSFWorkbook(in);
            List<String> list=new ArrayList<String>();
            for(int num=0;num<hssf.getNumberOfSheets();num++){
                HSSFSheet sheet=hssf.getSheetAt(num);
                if(sheet==null){
                    continue;
                }
                for(int ro=1;ro<=sheet.getLastRowNum();ro++){
                    HSSFRow row=sheet.getRow(ro);
                    int min=row.getFirstCellNum();
                    int max=row.getLastCellNum();
                    for(int co=min;co<max;co++){
                        HSSFCell cell=row.getCell(co);
                        if(cell==null){
                            continue;
                        }
                        if(StringUtils.isNotEmpty(cell.toString())){
                            list.add(cell.toString());
                        }
                    }
                }
            }
            in.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> ReadXlsx(InputStream in) {
        try {
            XSSFWorkbook work=new XSSFWorkbook(in);
            List<String> list=new ArrayList<String>();
            for(int x=0;x<work.getNumberOfSheets();x++){
                XSSFSheet sheet=work.getSheetAt(x);
                if(sheet==null){
                    continue;
                }
                for(int y=1;y<=sheet.getLastRowNum();y++){
                    XSSFRow row=sheet.getRow(y);
                    int min=row.getFirstCellNum();
                    int max=row.getLastCellNum();
                    for(int z=min;z<max;z++){
                        XSSFCell cell=row.getCell(z);
                        if(cell==null){
                            continue;
                        }
                        if(StringUtils.isNotEmpty(cell.toString())){
                            list.add(cell.toString());
                        }
                    }
                }
            }
            in.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String[]> Readpro(InputStream in) {
        try {
            InputStreamReader reader=new InputStreamReader(in,"UTF-8");
            BufferedReader read=new BufferedReader(reader);
            String info;
            String[] value;
            List<String[]> list=new ArrayList<String[]>();
            while (!((info=read.readLine())==null)){
                value=info.split("\t");
                list.add(value);
            }
            in.close();
            reader.close();
            read.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.inno.services.impl;

import com.inno.dao.DaoFactory;
import com.inno.dao.MonDao;
import com.inno.services.MonService;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public class MonServiceImpl implements MonService {
    MonDao dao= DaoFactory.getInstance().getMonDao("com.inno.dao.impl.MonDaoImpl");
    @Override
    public String select() {
        String html="<html><body><table border=\"1\"><tr><th>爬虫服务</th><th>近一天抓取成功总数/近一天抓取失败总数/平均每个用时</th><th>近三小时抓取成功总数/近三小时抓取失败总数/平均每个用时</th><th>近一小时抓取成功总数/近一小时抓取失败总数/平均每个用时</th><th>近半小时抓取成功总数/近半小时抓取失败总数/平均每个用时</th></tr>";
        List<Map<String,String>> list=dao.select();
        Map<String,String> map1=list.get(0);
        Map<String,String> map2=list.get(1);
        Map<String,String> map3=list.get(2);
        Map<String,String> map4=list.get(3);
        List<String> lists=new ArrayList<String>();
        boolean br=false;
        for(Map.Entry<String,String> entry: map1.entrySet()){
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            String filename=entry.getKey().split("_",2)[0];
            String s4=map4.get(filename+"_success");
            String s44=map4.get(filename+"_error");
            String s2=map2.get(filename+"_success");
            String s22=map2.get(filename+"_error");
            String s3=map3.get(filename+"_success");
            String s33=map3.get(filename+"_error");
            String s1=map1.get(filename+"_success");
            String s11=map1.get(filename+"_error");
            s2=flag(s2);
            s22=flag(s22);
            s3=flag(s3);
            s33=flag(s33);
            s1=flag(s1);
            s11=flag(s11);
            s4=flag(s4);
            s44=flag(s44);

            int p1=flag2(s1,s11);
            int p2=flag2(s2,s22);
            int p3=flag2(s3,s33);
            int p4=flag2(s4,s44);

            String a1= decimalFormat.format((float) 86400 / p1);
            String a2=decimalFormat.format((float) 10800 / p2);
            String a3=decimalFormat.format((float) 3600 / p3);
            String a4=decimalFormat.format((float) 1800 / p4);


            if(Float.parseFloat(a4)>Float.parseFloat(a1)*2){
                html=html+"<tr><td><font color=\"#FF0000\">"+filename+"</font></td><td><font color=\"#FF0000\">"+s1+"/"+s11+"/"+a1+"s</font></td><td>"+s2+"/"+s22+"/"+a2+"s</td><td>"+s3+"/"+s33+"/"+a3+"s</td><td><font color=\"#FF0000\">"+s4+"/"+s44+"/"+a4+"s</font></td></tr>";
                br=true;
                lists.add(filename);
            }else {
                html = html + "<tr><td>" + filename + "</td><td><font color=\"#0000C6\">" + s1 + "</font>/<font color=\"#FF0080\">"+s11+"</font>/" + a1 + "s</td><td><font color=\"#0000C6\">" + s2 + "</font>/<font color=\"#FF0080\">" +s22+"</font>/"+ a2 + "s</td><td><font color=\"#0000C6\">" + s3 + "</font>/<font color=\"#FF0080\">" +s33+"</font>/"+ a3 + "s</td><td><font color=\"#0000C6\">" + s4 + "</font>/<font color=\"#FF0080\">" +s44+"</font>/"+ a4 + "s</td></tr>";
            }
        }
        html=html+"</table>";

        Document doc= Jsoup.parse(html);
        Elements ele=doc.select("table tbody tr");
        int p=0;
        List<String> li=new ArrayList<String>();
        for(Element e:ele){
            if(p>=1){
                for(String s:li){
                    if(e.toString().equals(s)){
                        html=html.replace(s.replace("\n","").replace("> <","><"),"");
                        html=html.replace("</table>","")+s.replace("\n","").replace("> <","><")+"</table>";
                    }
                }
                li.add(e.toString());
            }
            p++;
        }

        if(map1.size()>0&&br) {
            return html+"\n<font color=\"#FF0000\">警告！！！"+lists.toString()+"爬虫服务表，近半小时抓取速率低于近一天抓取速率一倍以上，请注意！！！</font>\n</body></html>";
        }else if(map1.size()>0&&!br){
            return html;
        }else{
            return "当前没有爬虫处于监控状态";
        }
    }

    public static String flag(String key){
        if(StringUtils.isEmpty(key)){
            return "0";
        }else{
            return key;
        }
    }
    public static Integer flag2(String s1,String s2){
        if(Integer.parseInt(s1)+Integer.parseInt(s2)==0){
            return 1;
        }else{
            return Integer.parseInt(s1)+Integer.parseInt(s2);
        }
    }
}

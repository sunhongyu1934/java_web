package Monitor;


import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
public class spider_log extends HttpServlet{
    public static class Spiderlog{
        public String file_md5;
        public String file_size;
        public String file_name;
        public String spider_time;
        public String parse_time;
        public String data_date;
        public String store_data_flag;
        public String source;
    }


    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String type=request.getParameter("type");
        Object json=request.getParameter("json");

        response.setHeader("content-type", "text/html;charset=UTF-8");
        if(json!=null&&StringUtils.isNotEmpty(json.toString())){
            if(StringUtils.isEmpty(type)){
                PrintWriter out=response.getWriter();
                Map<String,String> map=new HashMap<String, String>();
                map.put("statu","error");
                map.put("message","参数缺失,请传入类型");
                JSONObject jsonObject=JSONObject.fromObject(map);
                out.write(jsonObject.toString());
            }else if(StringUtils.isNotEmpty(type)&&type.equals("spider")){
                try {
                    outputChineseByOutputStream(response,type,json);
                } catch (Exception e) {
                    PrintWriter out=response.getWriter();
                    Map<String,String> map=new HashMap<String, String>();
                    map.put("statu","error");
                    map.put("message",e.getMessage());
                    JSONObject jsonObject=JSONObject.fromObject(map);
                    out.write(jsonObject.toString());
                }
            }
        }else{
            PrintWriter out=response.getWriter();
            Map<String,String> map=new HashMap<String, String>();
            map.put("statu","error");
            map.put("message","参数错误,请传入json字符串");
            JSONObject jsonObject=JSONObject.fromObject(map);
            out.write(jsonObject.toString());
        }

    }

    public void outputChineseByOutputStream(HttpServletResponse response,String type,Object json) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, IOException {
        Map<String,String>  map = null;
        response.setHeader("content-type", "text/html;charset=UTF-8");

        if(type.equals("spider")){
            if(json instanceof String){
                Gson gson=new Gson();
                try {
                    Spiderlog spiderlog = gson.fromJson(json.toString(), Spiderlog.class);
                    map=storedata(spiderlog);
                }catch (Exception e){
                    map = new HashMap<String, String>();
                    map.put("statu", "error");
                    map.put("message", "传入json格式错误，请传入正确格式");
                    map.put("json",json.toString());
                }
            }else if(json instanceof List){
                List<String> list=((List<String>) json);
                for(int x=0;x<list.size();x++){
                    Gson gson=new Gson();
                    try {
                        Spiderlog spiderlog = gson.fromJson(list.get(x), Spiderlog.class);
                        map = storedata(spiderlog);
                    }catch (Exception e){
                        map = new HashMap<String, String>();
                        map.put("statu", "error");
                        map.put("message", "传入json格式错误，请传入正确格式");
                        map.put("json",list.get(x));
                    }
                }
            }else {
                map = new HashMap<String, String>();
                map.put("statu", "error");
                map.put("message", "传入json格式错误，请传入正确格式");
                map.put("json",json.toString());
            }
        }
        OutputStream outputStream = response.getOutputStream();
        JSONObject jsonObject=JSONObject.fromObject(map);
        byte[] dataByteArr = jsonObject.toString().getBytes("UTF-8");
        outputStream.write(dataByteArr);
    }


    public Map<String,String> storedata(Spiderlog spiderlog) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://10.44.60.141:3306/dw_online?useUnicode=true&useCursorFetch=true&defaultFetchSize=100?useUnicode=true&characterEncoding=utf-8&tcpRcvBuf=1024000";
        String username="spider";
        String password="spider";
        Class.forName(driver).newInstance();
        Connection con=null;
        try{
            con= DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            while (true){
                con=DriverManager.getConnection(url,username,password);
                if(con!=null){
                    break;
                }
            }
        }

        Map<String,String> map=new HashMap<String, String>();
        boolean flag=true;
        String sql="insert into spider_log(file_md5,file_size,file_name,spider_time,parse_time,data_date,store_data_flag,source) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,spiderlog.file_md5);
        ps.setString(2,spiderlog.file_size);
        ps.setString(3,spiderlog.file_name);
        ps.setString(4,spiderlog.spider_time);
        ps.setString(5,spiderlog.parse_time);
        ps.setString(6,spiderlog.data_date);
        ps.setString(7,spiderlog.store_data_flag);
        ps.setString(8,spiderlog.source);
        try {
            ps.executeUpdate();
        }catch (Exception e){
            flag=false;
            map.put("statu","error");
            map.put("message",e.getMessage());
        }
        if(flag){
            map.put("statu","ok");
            map.put("message","insert success");
        }
        return map;
    }


}

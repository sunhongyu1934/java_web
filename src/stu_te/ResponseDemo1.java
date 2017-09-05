package stu_te;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2017/5/6.
 */
public class ResponseDemo1 extends javax.servlet.http.HttpServlet {

    public static class bean{
        private String cid;
        private String name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);

    }

    public void outputChineseByOutputStream(HttpServletResponse response, int page) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, SQLException {
        /**使用OutputStream输出中文注意问题：
         * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
         * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
         * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
         * 可以通过设置响应头控制浏览器的行为，例如：
         * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
         */
        String driver1="com.mysql.jdbc.Driver";
        String url1="jdbc:mysql://10.44.60.141:3306/dw_online?useUnicode=true&useCursorFetch=true&defaultFetchSize=100?useUnicode=true&characterEncoding=utf-8&tcpRcvBuf=1024000";
        String username="spider";
        String password="spider";
        Class.forName(driver1).newInstance();
        java.sql.Connection con=null;
        try {
            con = DriverManager.getConnection(url1, username, password);
        }catch (Exception e){
            while(true){
                con = DriverManager.getConnection(url1, username, password);
                if(con!=null){
                    break;
                }
            }
        }

        String select="select c_id,company_full_name from it_company_pc";
        String count="select count(1) as aa from ("+select+") a";
        PreparedStatement ps2=con.prepareStatement(count);
        ResultSet rs=ps2.executeQuery();
        String aa=null;
        if(rs.next()){
             aa=rs.getString(rs.findColumn("aa"));
        }
        select=select+" limit "+page;
        PreparedStatement pss=con.prepareStatement(select);
        ResultSet rss=pss.executeQuery();
        Map<Object,Object> map=new HashMap<Object, Object>();
        List<bean> list=new ArrayList<bean>();
        while (rss.next()) {
            bean b=new bean();
            String cid = rss.getString(rss.findColumn("c_id"));
            String cnamez = rss.getString(rss.findColumn("company_full_name"));
            b.setCid(cid);
            b.setName(cnamez);
            list.add(b);
        }
        map.put("name","json");
        map.put("message","ok");
        map.put("data",list);
        map.put("total_rows",aa);
        JSONObject jsonObject=JSONObject.fromObject(map);
        String data=jsonObject.toString();
        OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
        response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        /**
         * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
         * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
         * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
         * 比如： "中"在GB2312的码表上对应的数字是98
         *         "国"在GB2312的码表上对应的数字是99
         */
        /**
         * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
         */
        byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String page= request.getParameter("page");
        if(page!=null&&page.length()>0) {
            int a =0;
            try {
                 a=Integer.parseInt(page);
            }catch (Exception ee){
                response.setHeader("content-type", "text/html;charset=UTF-8");
                PrintWriter out=response.getWriter();
                out.write("请传入数字");
            }
            if(a!=0) {
                try {
                    outputChineseByOutputStream(response, a);//使用OutputStream流输出中文
                } catch (Exception e) {
                    response.setHeader("content-type", "text/html;charset=UTF-8");
                    PrintWriter out=response.getWriter();
                    out.write(e.getMessage());
                }
            }else{
                response.setHeader("content-type", "text/html;charset=UTF-8");
                PrintWriter out=response.getWriter();
                out.write("请别传入0");
            }
        }else{
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.write("请传入参数");
        }

    }
}

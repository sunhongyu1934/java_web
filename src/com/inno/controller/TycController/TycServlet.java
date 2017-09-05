package com.inno.controller.TycController;

import com.inno.services.ServiceFactory;
import com.inno.services.TycService;
import com.inno.utils.TYCProducer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
public class TycServlet extends HttpServlet{
    TycService dau = ServiceFactory.getInstance().getTycService("com.inno.services.impl.TycServiceImpl");
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  //设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = "/data2/tyc";

        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
        factory.setRepository(new File(path));
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024*1024) ;

        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);


        try {
            //可以上传多个文件
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
            List<String> list1=new ArrayList<String>();
            List<String[]> list2=new ArrayList<String[]>();
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            StringBuffer str=new StringBuffer();
            for(FileItem item : list)
            {
                //获取表单的属性名字
                String name = item.getFieldName();

                //如果获取的 表单信息是普通的 文本 信息
                if(item.isFormField())
                {
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                    String value = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                    str.append(value+",");
                }else{
                    /**
                     * 以下三步，主要获取 上传文件的名字
                     */
                    //获取路径名
                    String value = item.getName() ;
                    //索引到最后一个反斜杠
                    int start = value.lastIndexOf("\\");
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                    String filename = value.substring(start+1);

                    //真正写到磁盘上
                    //它抛出的异常 用exception 捕捉

                    //item.write( new File(path,filename) );//第三方提供的

                    //手动写的
                    //OutputStream out = new FileOutputStream(new File(path,filename));

                    InputStream in = item.getInputStream();
                    if(name.equals("公司名")&&filename.substring(filename.indexOf(".")).equals(".xls")){
                        list1=dau.ReadXls(in);
                    }else if(name.equals("公司名")&&filename.substring(filename.indexOf(".")).equals(".xlsx")){
                        list1=dau.ReadXlsx(in);
                    }
                    if(name.equals("参数")){
                        list2=dau.Readpro(in);
                    }
                /*int length = 0 ;
                byte [] buf = new byte[1024] ;

                System.out.println("获取上传文件的总共的容量："+item.getSize());

                // in.read(buf) 每次读到的数据存放在   buf 数组中
                while( (length = in.read(buf) ) != -1)
                {
                    //在   buf 数组中 取出数据 写到 （输出流）磁盘上
                    out.write(buf, 0, length);

                }

                in.close();
                out.close();*/
                }
            }
            String zhua=str.toString().substring(0,str.toString().length()-1);
            if(list1!=null&&list2!=null){
                dau.Exec(list2,zhua);
                TYCProducer ty=new TYCProducer("tyc_zl","10.44.51.90:19092,10.44.152.49:19092,10.51.82.74:19092");
                for(String s:list1){
                    ty.send(s);
                }
                ty.close();
                out.write("<h1><font color=\"#FF0000\">上传完毕，暂不提供监控功能</font></h1>");
            }else{
                out.write("<h1><font color=\"#FF0000\">上传失败</font></h1>");
            }
            out.flush();
            out.close();
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block

            //e.printStackTrace();
        }



    }
}

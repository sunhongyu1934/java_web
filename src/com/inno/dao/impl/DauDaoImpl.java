package com.inno.dao.impl;

import com.inno.bean.DauBean.AppXin;
import com.inno.bean.DauBean.QueryResult;
import com.inno.dao.DauDao;
import com.inno.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/20.
 */
public class DauDaoImpl implements DauDao {

    QueryResult queryResult = new QueryResult();

    @Override
    public List<AppXin> getAll(String limit) {
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "select * from r_china_net_app_detail_stat limit " + limit;
            pt = conn.prepareStatement(sql);
            rs=pt.executeQuery();

            List<AppXin> list=new ArrayList<AppXin>();
            while (rs.next()){
                AppXin s=new AppXin();
                s.setRelated_id(rs.getString(rs.findColumn("related_id")));
                s.setProject_name(rs.getString(rs.findColumn("project_name")));
                s.setSource_type(rs.getString(rs.findColumn("source_type")));
                s.setCount_value(rs.getString(rs.findColumn("count_value")));
                s.setDate_time(rs.getString(rs.findColumn("date_time")));
                s.setLoad_time(rs.getString(rs.findColumn("load_time")));
                s.setClassify_id(rs.getString(rs.findColumn("classify_id")));
                list.add(s);

            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeDB(conn,pt,rs);
        }

        return null;
    }

    @Override
    public QueryResult getSecondDetail(String firstClassId) {
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        try{
            conn=DBManager.getConnection();
            String classify_sql = "select a.second_classify_name  as second_classify_name " +
                    "            ,a.second_classify_id    as second_classify_id   " +
                    "             ,sum(b.count_value) as count_value from" +
                    "      (select second_classify_name" +
                    "              ,classify_id" +
                    "              ,second_classify_id" +
                    "         from r_project_classify " +
                    "        where first_classify_id=?) a" +
                    "  join " +
                    "      (select count_value" +
                    "              ,classify_id " +
                    "         from r_china_net_app_detail_stat " +
                    "        where DATE_FORMAT(date_time,'%Y-%m-%d')=DATE_SUB(CURDATE(),INTERVAL 2 DAY) " +
                    "          and source_type=5) b" +
                    "    on a.classify_id=b.classify_id" +
                    " group by a.second_classify_name" +
                    "          ,a.second_classify_id;";
            String project_sql = "select count_value" +
                    "                    ,date_time" +
                    "         from r_china_net_app_detail_stat" +
                    "        where source_type=5" +
                    "          and related_id=?;";
            List name_arry_list = new ArrayList();
            List dau_arry_list = new ArrayList();
            List id_arry_list = new ArrayList();
            List date_time_list = new ArrayList();



            if (!firstClassId.startsWith("w")) {
                pt = conn.prepareStatement(classify_sql);
                pt.setString(1, firstClassId);
                ResultSet resultSet = pt.executeQuery();
                resultSet.last();
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();
                String[] name_arry = new String[rowCount];
                int[] dau_arry = new int[rowCount];
                String[] id_arry = new String[rowCount];
                int i = 0;
                while (resultSet.next()) {
                    name_arry[i] = resultSet.getString("second_classify_name");
                    dau_arry[i] = resultSet.getInt("count_value");
                    id_arry[i] = resultSet.getString("second_classify_id");
                    name_arry_list.add(name_arry[i]);
                    dau_arry_list.add(dau_arry[i]);
                    id_arry_list.add(id_arry[i]);
                    i++;
                }
                queryResult.setName_arry(name_arry_list);
                queryResult.setDau_arry(dau_arry_list);
                queryResult.setId_arry(id_arry_list);
            } else {
                pt = conn.prepareStatement(project_sql);
                pt.setString(1, firstClassId);
                ResultSet resultSet = pt.executeQuery();
                resultSet.last();
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();
                int[] dau_arry = new int[rowCount];
                String[] date_arry = new String[rowCount];
                int i = 0;
                while (resultSet.next()) {
                    dau_arry[i] = resultSet.getInt("count_value");
                    date_arry[i] = resultSet.getString("date_time");
                    dau_arry_list.add(dau_arry[i]);
                    date_time_list.add(date_arry[i]);
                    i++;
                }
                queryResult.setDau_arry(dau_arry_list);
                queryResult.setDate_time(date_time_list);
            }










            /*pt=conn.prepareStatement(sql);
            rs=pt.executeQuery();
            Map<String,String> map=new HashMap<String, String>();
            StringBuffer strkey=new StringBuffer();
            StringBuffer strvalue=new StringBuffer();
            while (rs.next()){
                String secondname=rs.getString(rs.findColumn("secondname"));
                String sum=rs.getString(rs.findColumn("sum"));
                strkey.append(secondname+",");
                strvalue.append(sum+",");
            }
            map.put("secondname",strkey.toString().substring(0,strkey.toString().length()-1));
            map.put("sum",strvalue.toString().substring(0,strvalue.toString().length()-1));
            return map;*/
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeDB(conn,pt,rs);
        }
        return queryResult;
    }

    @Override
    public QueryResult getProjectDetail(String firstClassId, String secondClassId) {
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        Map<String,String> map=new HashMap<String, String>();
        StringBuffer strkey=new StringBuffer();
        StringBuffer strvalue=new StringBuffer();
        try{
            conn=DBManager.getConnection();
            String sql = "select b.project_name as project_name" +
                    "            ,sum(b.count_value) as count_value" +
                    "            ,b.related_id as related_id" +
                    "  from " +
                    "      (select second_classify_name" +
                    "              ,classify_id" +
                    "              ,second_classify_id" +
                    "         from r_project_classify" +
                    "        where first_classify_id=?" +
                    "          and second_classify_id=?) a" +
                    "  join " +
                    "      (select count_value" +
                    "              ,classify_id" +
                    "              ,project_name" +
                    "              ,related_id\n" +
                    "         from r_china_net_app_detail_stat" +
                    "        where DATE_FORMAT(date_time,'%Y-%m-%d')=DATE_SUB(CURDATE(),INTERVAL 2 DAY) " +
                    "          and source_type=5) b" +
                    "    on a.classify_id=b.classify_id" +
                    " group by b.project_name" +
                    "          ,b.related_id" +
                    "  order by b.count_value desc" +
                    "  limit 10;";


            pt = conn.prepareStatement(sql);
            pt.setString(1, firstClassId);
            pt.setString(2, secondClassId);
            ResultSet resultSet = pt.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            // construct list
            List project_name_list = new ArrayList();
            List list_dau_list = new ArrayList();
            List related_id_list = new ArrayList();
            String[] project_name = new String[rowCount];
            int[] dau_arry = new int[rowCount];
            String[] related_id = new String[rowCount];
            int i = 0;
            while (resultSet.next()) {
                project_name[i] = resultSet.getString("project_name");
                dau_arry[i] = resultSet.getInt("count_value");
                related_id[i] = resultSet.getString("related_id");
                project_name_list.add(project_name[i]);
                list_dau_list.add(dau_arry[i]);
                related_id_list.add(related_id[i]);
                i++;
            }
            queryResult.setNew_name_arry(project_name_list);
            queryResult.setNew_dau_arry(list_dau_list);
            queryResult.setNew_id_arry(related_id_list);



            /*pt=conn.prepareStatement(sql);
            rs=pt.executeQuery();


            while (rs.next()){
                String projectname=rs.getString(rs.findColumn("projectname"));
                String sum=rs.getString(rs.findColumn("sum"));
                strkey.append(projectname+",");
                strvalue.append(sum+",");
            }
            map.put("projectname",strkey.toString().substring(0,strkey.toString().length()-1));
            map.put("sum",strvalue.toString().substring(0,strvalue.toString().length()-1));
            return map;*/
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeDB(conn,pt,rs);
        }
        return queryResult;
    }

}

package com.inno.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/20.
 */
public class DBManager {
    /**
     * @param args
     */
    static String driver;
    static String url;
    static String username;
    static String password;


    static{
        InputStream in=DBManager.class.getClassLoader().getResourceAsStream("resources/db.properties");
        Properties pro=new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver = pro.getProperty("driver");

        url = pro.getProperty("url");

        username = pro.getProperty("username");

        password = pro.getProperty("password");
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        Connection con=null;


        try {
            con= DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;

    }

    public static void closeDB(Connection con,Statement st,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        getConnection();



    }
}

package com.example.backend2.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private final static String url = "jdbc:mysql://10.4.53.32:3306/db63130500217";

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,"63130500217","abcd1234");
            return conn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}

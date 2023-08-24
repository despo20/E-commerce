package com.example.ecommerce;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DBconnection {
    private  final String dbUrl="jdbc:mysql://localhost:3306/e-commerce";
    private   final String username="root";
    private   final String password="";

    private  Statement getStatement(){
     try {
      Connection conn= DriverManager.getConnection(dbUrl,username,password);
       Statement st= conn.createStatement();

     return st;
     }catch (Exception e){
         e.printStackTrace();
     }
        return null;
    }

public ResultSet getQueryTable(String query){
        try {
            Statement st=getStatement();
            return st.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
  return  null;
}

public  int updateDatabase(String query){
    try {
        Statement st=getStatement();
        return st.executeUpdate(query);
    }catch (Exception e){
        e.printStackTrace();
    }
    return  0;
}

    public static void main(String[] args) {
        DBconnection conn=new DBconnection();
        ResultSet rs=conn.getQueryTable("SELECT * FROM custmor;");

        if(rs!=null){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
    }
}

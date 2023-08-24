package com.example.ecommerce;

import java.sql.ResultSet;

public class Login {
    public Customer custmorLogin(String username,String password){
        String loginQuery="SELECT * FROM custmor WHERE email='"+username+"' AND password='"+password+"'";
       DBconnection conn=new DBconnection();
        ResultSet rs= conn.getQueryTable(loginQuery);

       try {
             if(rs.next())return new Customer(rs.getInt("id"),rs.getInt("mobile"),rs.getString("name"),rs.getString("email"));
           }catch (Exception e){
           e.printStackTrace();
       }

        return null;

    }

    public static void main(String[] args) {
        Login login = new Login();
        //boolean a=login.custmorLogin("abc@","12asdfsd");
        Customer cust = login.custmorLogin("A","sdfs");
        System.out.println(cust.getName());
    }
}

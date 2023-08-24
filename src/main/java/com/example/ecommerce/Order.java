package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

    public  static boolean placeOrder(Customer cust,Product pr){
        String Group_Order_Id ="SELECT max(`Group_Order_Id`)+1 id FROM orders";

   DBconnection conn=new DBconnection();
   try {
       ResultSet rs=conn.getQueryTable(Group_Order_Id);
       if(rs.next()){
           String placeorder="INSERT INTO orders (`Group_Order_Id`,`custmor_id`,`product_id`)VALUES("+rs.getInt("id")+","+cust.getId()+","+pr.getId()+")";
           return conn.updateDatabase(placeorder)!=0;
       }
   }catch (Exception e){
       e.printStackTrace();
   }
 return false;
    }

    public  static int placeMultipleOrder(Customer cust, ObservableList<Product>productList){
        String Group_Order_Id ="SELECT max(`Group_Order_Id`)+1 id FROM orders";

        DBconnection conn=new DBconnection();
        try {
            ResultSet rs=conn.getQueryTable(Group_Order_Id);
            int count=0;
            if(rs.next()){
                for(Product pr:productList){
                    String placeorder="INSERT INTO orders (`Group_Order_Id`,`custmor_id`,`product_id`)VALUES("+rs.getInt("id")+","+cust.getId()+","+pr.getId()+")";
               count+=conn.updateDatabase(placeorder);
                }

                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

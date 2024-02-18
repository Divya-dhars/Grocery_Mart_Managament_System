package model;
import java.util.*;

import java.sql.*;
public class AdminModel {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    String res="";
    List<String> p=new ArrayList<>();
    List<String> dealer=new ArrayList<>();
    List<String> order=new ArrayList<>();
    public List<String> displayProducts(){
       try{
         con=DBConnection.getConnection();
         String query="select id,name,price from product";
         st=con.createStatement();
         rs=st.executeQuery(query);
         while (rs.next()) {
            res="id : "+rs.getString(1)+"  productname "+rs.getString(2)+"  price "+rs.getString(3);
        }       
        p.add(res);
       }catch(Exception e){
        System.out.println(e);
       }
       return p;
    }
    public boolean addProduct(String name,String quantity,String price){
        try{
            con=DBConnection.getConnection();
            String query="insert into product(name,quantity,price)values('"+name+"','"+quantity+"','"+price+"')";
            st=con.createStatement();
            st.executeUpdate(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProduct(int id){
        try{
            con=DBConnection.getConnection();
            String query="delete from product where id="+id;
            st=con.createStatement();
            st.executeUpdate(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateQuantity(int id,String q){
        try{
            con=DBConnection.getConnection();
            String query="update product set quantity="+q+" where id="+id;
            st=con.createStatement();
            st.executeUpdate(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePrice(int id,String p){
        try{
            con=DBConnection.getConnection();
            String query="update product set price="+p+" where id="+id;
            st=con.createStatement();
            st.executeUpdate(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<String> displayDealer(){
        try{
          con=DBConnection.getConnection();
          String query="select id,username,email,contact from dealers";
          st=con.createStatement();
          rs=st.executeQuery(query);
          while (rs.next()) {
             res="id : "+rs.getInt(1)+"  username "+rs.getString(2)+"  email "+rs.getString(3) +"  contact "+rs.getString(4);
         }       
         dealer.add(res);
        }catch(Exception e){
         System.out.println(e);
        }
        return dealer;
     }
     public List<String> orderHistory(){
        try{
          con=DBConnection.getConnection();
          String query="select id,pname,quantity,price,email from orders";
          st=con.createStatement();
          rs=st.executeQuery(query);
          while (rs.next()) {
            res="id : "+rs.getInt(1)+"  productname : "+rs.getString(2)+"  quantity : "+rs.getString(3) + " price : "+rs.getString(4)+" email : " +rs.getString(5);
         }       
         order.add(res);
        }catch(Exception e){
         System.out.println(e);
        }
        return order;
     }
}

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
         String query="select * from product";
         st=con.createStatement();
         rs=st.executeQuery(query);
         while (rs.next()) {
            res="id : "+rs.getInt(1)+"  productname "+rs.getString(2)+"  price "+rs.getString(3);
            p.add(res);
        }       
        
       }catch(Exception e){
        System.out.println(e);
       }
       return p;
    }
    public List<String> displayProductsLess(){
       try{
         con=DBConnection.getConnection();
         String query="select * from product where quantity <5";
         st=con.createStatement();
         rs=st.executeQuery(query);
         while (rs.next()) {
            res="id : "+rs.getInt(1)+"  productname "+rs.getString(2)+"  price "+rs.getString(3)+" dealerId "+rs.getString(4);
        }       
        p.add(res);
       }catch(Exception e){
        System.out.println(e);
       }
       return p;
    }
    public boolean addStock(String productId) throws Exception {
        try {
            con = DBConnection.getConnection();
            String query1 = "SELECT * FROM product WHERE id=?";
                pst = con.prepareStatement(query1);
                pst.setString(1, productId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id=rs.getInt("id");
                    String pname=rs.getString("name");
                    String quantity=rs.getString("quantity");
                    String dealerId = rs.getString("dealerId");
                    // Inserting into the stock table
                    String query = "INSERT INTO stock(pid, pname, pquantity, isneed, demail) VALUES (?, ?, ?, ?, ?)";
                    pst = con.prepareStatement(query);
                    pst.setInt(1, id);
                    pst.setString(2, pname);
                    pst.setString(3, quantity);
                    pst.setBoolean(4, true);
                    pst.setString(5, dealerId);
                    pst.executeUpdate();
                    return true;
                }
                else{
                    return false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean addProduct(String name,String quantity,String price){
        try{
            con=DBConnection.getConnection();
            String query1 = "SELECT * FROM dealers where product_name='" + name + "'";
            st=con.createStatement();
            rs=st.executeQuery(query1);
            if(rs.next()){
                String dealerId=rs.getString("email");
                String query="insert into product(name,quantity,price,dealerId)values('"+name+"','"+quantity+"','"+price+"','"+dealerId+"')";
                st=con.createStatement();
                st.executeUpdate(query);
                return true; 
            }
            else{
                return false;
            }
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
             dealer.add(res);
         }       
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
            order.add(res);
         }       
         
        }catch(Exception e){
         System.out.println(e);
        }
        return order;
     }
}

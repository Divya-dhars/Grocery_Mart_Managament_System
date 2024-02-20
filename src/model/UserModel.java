package model;
import java.util.*;
import java.sql.*;
public class UserModel {
    List<String> p=new ArrayList<String>();
    List<String> orderHistory = new ArrayList<>();
    List<String> cartHistory = new ArrayList<>();
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    String res="";
    public List<String> displayProducts(){
       try{
         con=DBConnection.getConnection();
         String query="select id,name,price from product";
         st=con.createStatement();
         rs=st.executeQuery(query);
         while (rs.next()) {
            res="id : "+rs.getString(1)+"  productname "+rs.getString(2)+"  price "+rs.getString(3);
            p.add(res);
        }       
       }catch(Exception e){
        System.out.println(e);
       }
       return p;
    }
     public boolean addToCart(int id,String q,String email) {
      try{
        con=DBConnection.getConnection();
        String query="select name,quantity,price from product where id="+id;
        st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        if(rs.next()){
            String pname=rs.getString("name");
            String price=rs.getString("price");
            int Pprice=Integer.parseInt(q);
            int Q=Integer.parseInt(price);
            String tot_price=String.valueOf(Pprice*Q);
            String add="insert into cart(id,product_name,quantity,tot_price,email)values('"+id+"','"+pname+"','"+q+"','"+tot_price+"','"+email+"')";
            st=con.createStatement();
            st.executeUpdate(add);
            return true;
        }else {
            return false;
        }
      }catch(Exception e){
         e.printStackTrace();
       }
       return false;
    }
    public boolean buyProduct(String id1,String quan,String email) {
        try{
            con=DBConnection.getConnection();
            String query="select name,quantity,price from product where id="+id1;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                // String pname = rs.getString("name");
                int availableQuantity = Integer.parseInt(rs.getString("quantity"));
                int orderedQuantity = Integer.parseInt(quan);
                if(orderedQuantity<=availableQuantity && availableQuantity!=0){
                    int updatedQuantity = availableQuantity - orderedQuantity;
                    String up=String.valueOf(updatedQuantity);
                    String query1="update product set quantity="+up+" where id="+id1;
                    st=con.createStatement();
                    st.executeUpdate(query1);
                    String prname=rs.getString("name");
                    String price=rs.getString("price");
                    int Q=Integer.parseInt(quan);
                    int Pprice=Integer.parseInt(price);
                    String tot_price=String.valueOf(Pprice*Q);
                    String add="insert into orders(id,pname,quantity,price,email)values('"+id1+"','"+prname+"','"+quan+"','"+tot_price+"','"+email+"')";
                    st=con.createStatement();
                    st.executeUpdate(add);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public List<String> viewHistory(String email) {
        try{
            con=DBConnection.getConnection();
            String query = "SELECT * FROM orders WHERE email = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String pname=rs.getString("pname");
                String quantity=rs.getString("quantity");
                String price=rs.getString("price");
                String orderDetails = "Order ID: " + id + " Product Name: " + pname + " Quantity: " + quantity + " Price: " + price;
                orderHistory.add(orderDetails);

            }
        }catch(Exception e){
            System.out.println(e);
        }
        return orderHistory;
    }
    public List<String> viewCart(String email) {
        try{
            con=DBConnection.getConnection();
            String query = "SELECT * FROM cart WHERE email = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String pname=rs.getString("product_name");
                String quantity=rs.getString("quantity");
                String price=rs.getString("tot_price");
                String orderDetails = "Order ID : " + id + " Product Name : " + pname + " Quantity : " + quantity + " Price : " + price;
                cartHistory.add(orderDetails);

            }
        }catch(Exception e){
            System.out.println(e);
        }
        return cartHistory;
    }
    public String displayTotAmount(String email){
        int sum=0;
        try{
            con=DBConnection.getConnection();
            String query1="select sum(tot_price) from cart where email=?";
            pst = con.prepareStatement(query1);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while(rs.next()){
                sum=rs.getInt(1);
            }
            return String.valueOf(sum);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "0";
    }
    public String displayAmount(String id){
        int sum=0;
        try{
            con=DBConnection.getConnection();
            String query1="select price from product where id=?";
            pst = con.prepareStatement(query1);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                sum=rs.getInt(1);
            }
            return String.valueOf(sum);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "0";
    }
    public boolean storeOrderDetails(int id2,String quan1,String email) {
        try{
            con=DBConnection.getConnection();
            String query="select name,quantity,price from product where id="+id2;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                int availableQuantity=Integer.parseInt(rs.getString("quantity"));
                int orderedQuantity=Integer.parseInt(quan1);
                if(orderedQuantity<=availableQuantity && availableQuantity!=0){
                    int updatedQuantity = availableQuantity - orderedQuantity;
                    String query1="update product set quantity="+updatedQuantity+" where id="+id2;
                    st=con.createStatement();
                    st.executeUpdate(query1);
                    String prname=rs.getString("name");
                    String price=rs.getString("price");
                    int Pprice=Integer.parseInt(price);
                    int Q=Integer.parseInt(quan1);
                    String tot_price=String.valueOf(Pprice*Q);
                    String add="insert into orders(id,pname,quantity,price,email)values('"+id2+"','"+prname+"','"+quan1+"','"+tot_price+"','"+email+"')";
                    st=con.createStatement();
                    st.executeUpdate(add);
                    return true;
                }
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public boolean removeAllProductsFromCart(String email){
        try{
            con=DBConnection.getConnection();
            String query="delete from cart where email=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeProduct(int proId,String email){
        try{
            con = DBConnection.getConnection();
            String query = "DELETE FROM cart WHERE id=? AND email=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, proId);
            pst.setString(2, email);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

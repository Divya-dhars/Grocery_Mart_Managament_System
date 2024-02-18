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
        }       
        p.add(res);
       }catch(Exception e){
        System.out.println(e);
       }
       return p;
    }
     public String addToCart(int id,String q,String email) {
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
            System.out.println("Product added to cart successfully");
        }else {
            System.out.println("Product with ID " + id + " does not exist.");
        }
      }catch(Exception e){
        System.out.println(e);
       }
       return "";
    }
    public String buyProduct(int id1,String quan,String email) {
        try{
            con=DBConnection.getConnection();
            String query="select name,quantity,price from product where id="+id1;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                String pname=rs.getString("name");
                String price=rs.getString("price");
                int Pprice=Integer.parseInt(quan);
                int Q=Integer.parseInt(price);
                String tot_price=String.valueOf(Pprice*Q);
                String add="insert into orders(id,pname,quantity,price,email)values('"+id1+"','"+pname+"','"+quan+"','"+tot_price+"','"+email+"')";
                st=con.createStatement();
                st.executeUpdate(add);
                System.out.println("Order placed");
            }else{
                System.out.println("Product with ID " + id1 + " does not exist.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return "";
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
                String orderDetails = "Order ID: " + id + ", Product Name: " + pname + ", Quantity: " + quantity + ", Price: " + price;
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
            String query = "SELECT * FROM orders WHERE email = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            while(rs.next()){
                int id=rs.getInt("id");
                String pname=rs.getString("product_name");
                String quantity=rs.getString("quantity");
                String price=rs.getString("tot_price");
                String orderDetails = "Order ID: " + id + ", Product Name: " + pname + ", Quantity: " + quantity + ", Price: " + price;
                cartHistory.add(orderDetails);

            }
        }catch(Exception e){
            System.out.println(e);
        }
        return cartHistory;
    }
}

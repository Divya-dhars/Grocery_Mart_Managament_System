package model;
import java.util.*;
import java.sql.*;
public class dealerModel {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    List<String> dis=new ArrayList<>();
    String res="";
    public boolean sendStock(int id,String q){
        try{
            con=DBConnection.getConnection();
            String query="update product set quantity="+q+ " where id="+id;
            String query1="delete from stock where pid="+id;
            st=con.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query1);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<String> displayStock(){
        try{
            con=DBConnection.getConnection();
            String query="select * from product";
            st=con.createStatement();
            rs=st.executeQuery(query);
            while(rs.next()){
                res="id : "+rs.getInt(1)+"  productname : "+rs.getString(2)+" quantity : " +rs.getString(3)+"  price : "+rs.getString(4);
            }
            dis.add(res);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dis;
    }
}

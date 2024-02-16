package model;
import java.sql.*;
import model.DBConnection;
class UserModel{
    protected Connection conn=null;
    protected PreparedStatement pst1=null,pst2=null;
    DBConnection db=new DBConnection();
    public boolean isRegistered(String email) throws Exception {
        conn=db.getConnection();
        String query="SELECT * FROM users WHERE email=?";
        pst1=conn.prepareStatement(query);
        pst1.setString(1,email);
        ResultSet rs=pst1.executeQuery();
        return rs.next();
    }
}
public class RegisterModel extends UserModel{
    public boolean registerUser(String username, String email, String password, String role,String contactNumber) throws Exception{
        conn=db.getConnection();
        if(isRegistered(email)){
            System.out.println("User already registered");
            return false;
        }
        else{
            String query1="INSERT INTO users(username,email,password,defaultUser)VALUES(?,?,?,?)";
            String query2="INSERT INTO dealers(username,email,contact)VALUES(?,?,?)";
            pst1=conn.prepareStatement(query1);
            pst2=conn.prepareStatement(query2);
            pst1.setString(1,username);
            pst1.setString(2,email);
            pst1.setString(3,password);
            pst1.setString(4,role);
            pst2.setString(1,username);
            pst2.setString(2,email);
            pst2.setString(3,contactNumber);
            int rows1=pst1.executeUpdate();
            int rows2=pst2.executeUpdate();
            if(rows1>0 && rows2>0){
                System.out.println("User registration successfully");
                return true;
            }
            else{
                System.out.println("User registration failed");
                return false;
            }
        }
    }
    public boolean registerUser(String username, String email, String password, String role,String mobile, String address) throws Exception{   
        conn=db.getConnection();
        if(isRegistered(email)){
            System.out.println("User already registered");
            return false;
        }
        else{
            String query1="INSERT INTO users(username,email,password,defaultUser,mobile,address)VALUES(?,?,?,?,?,?)";
            String query2="INSERT INTO dealers(username,email,mobile)VALUES(?,?,?)";
            pst1=conn.prepareStatement(query1);
            pst2=conn.prepareStatement(query2);
            pst1.setString(1,username);
            pst1.setString(2,email);
            pst1.setString(3,password);
            pst1.setString(4,role);
            pst1.setString(5,mobile);
            pst1.setString(6,address);
            pst2.setString(1,username);
            pst2.setString(2,email);
            pst2.setString(3,mobile);
            int rows1=pst1.executeUpdate();
            int rows2=pst2.executeUpdate();
            if(rows1>0 && rows2>0){
                System.out.println("User registration successfully");
                return true;
            }
            else{
                System.out.println("User registration failed");
                return false;
            }
        }
    }
}
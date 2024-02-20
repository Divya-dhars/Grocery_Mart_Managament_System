package model;
import java.sql.*;
class User{
    protected Connection con=null;
    protected PreparedStatement pst1=null,pst2=null;
    DBConnection db=new DBConnection();
    @SuppressWarnings("static-access")
    public boolean isRegistered(String email) throws Exception {
        con=db.getConnection();
        String query="SELECT * FROM users WHERE email=?";
        pst1=con.prepareStatement(query);
        pst1.setString(1,email);
        ResultSet rs=pst1.executeQuery();
        return rs.next();
    }
}
public class RegisterModel extends User{
    @SuppressWarnings("static-access")
    public boolean registerUser(String username, String email, String password, String role,String contactNumber,String address,String product) throws Exception{
        con=db.getConnection();
        if(isRegistered(email)){
            System.out.println("User already registered");
            return false;
        }
        else{
          try{
            Statement st=con.createStatement();
            String query="INSERT INTO dealers(username,email,contact,product_name)VALUES('"+username+"','"+email+"','"+contactNumber+"','"+product+"')";
            String query1="INSERT INTO users(username,email,password,defaultUser,mobile,address)VALUES('"+username+"','"+email+"','"+password+"','"+role+"','"+contactNumber+"','"+address+"')";
            int inserted=st.executeUpdate(query);
            int inserted1=st.executeUpdate(query1);
            if(inserted==1 && inserted1==1){
                System.out.println("User registration successfully");
                return true;
            }
            else{
                System.out.println("User registration failed");
                return false;
            }
        }catch(Exception e){
            System.out.print(e);
         }
        }
        return false;
    }
    @SuppressWarnings("static-access")
    public boolean registerUser(String username, String email, String password, String role,String mobile, String address) throws Exception{   
        con=db.getConnection();
        if(isRegistered(email)){
            System.out.println("User already registered");
            return false;
        }
        else{
            Statement st=con.createStatement();
            String query1="INSERT INTO users(username,email,password,defaultUser,mobile,address)VALUES('"+username+"','"+email+"','"+password+"','"+role+"','"+mobile+"','"+address+"')";
            int inserted1=st.executeUpdate(query1);
            if(inserted1==1){
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

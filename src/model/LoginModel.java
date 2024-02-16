package model;
import java.sql.*;
public class LoginModel{
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    public int checkUser(String email, String password)throws Exception{
        try{
            conn=DBConnection.getConnection();
            String query="SELECT * FROM users WHERE email=? AND password=?";
            pst=conn.prepareStatement(query);
            pst.setString(1,email);
            pst.setString(2,password);
            rs=pst.executeQuery();
            if(!rs.next()){
                System.out.println("User not registered");
                return 0;
            }
            else{
                    String defaultUser=rs.getString("defaultUser");
                    if(defaultUser.equalsIgnoreCase("admin")){
                        return 3;
                    }
                    else if(defaultUser.equalsIgnoreCase("User")){
                        return 1;
                    }
                    else if(defaultUser.equalsIgnoreCase("dealer")){
                        return 2;
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

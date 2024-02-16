package model;

import java.sql.*;
public class Dbconnection {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/grocerymart";
    private static  final String USERNAME="root";
    private static final String PASSWORD="Divya29*";
    public static Connection getConnetcion(){
        try{
            return DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

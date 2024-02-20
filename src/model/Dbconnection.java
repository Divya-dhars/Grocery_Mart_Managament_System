package model;

import java.sql.*;

public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/grocerymart";
    private static final String root="root";
    private static final String password="Divya29*";

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, root, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
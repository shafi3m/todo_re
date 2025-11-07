package todo_re;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            //  Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

             //Database details (change to your credentials)
            String url = "jdbc:mysql://localhost:3306/todo_re";
            String user = "root";
            String pass = "admin";

            con = DriverManager.getConnection(url, user, pass);
            System.out.println(" Database Connected Successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Database Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }
}


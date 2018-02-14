package model;
import beans.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static Database instance;
    private volatile Connection conn;

    private Database() throws ClassNotFoundException, SQLException {


        String dbUrl = "jdbc:mysql://35.202.50.71:3306/Chatdb";
        conn = DriverManager.getConnection(dbUrl , "mfawzy" , "\\c3d{kBj\\8UqUAny");

    }


    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null)
            instance = new Database();
            
        return instance;
    }
    
    public  Connection getConnection()
    {
            return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

  

    
 
}

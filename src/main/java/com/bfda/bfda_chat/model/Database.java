package com.bfda.bfda_chat.model;


import java.sql.*;
import com.mysql.jdbc.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database {

    private static Database instance;
    private Connection conn;

    private Database() throws ClassNotFoundException, SQLException {


        String dbUrl = "jdbc:mysql://35.202.50.71:3306/Chatdb";

        conn = DriverManager.getConnection(dbUrl , "mfawzy" , "\\c3d{kBj\\8UqUAny");
    }

    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null)
            instance = new Database();

        return instance;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public User getUserObject(String username) throws SQLException {
        User user = new User();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from User where username = ?");
        preparedStatement.setString(1 , username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        {
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
        }
        return user;
    }

    
    public void insertUser(User myUser){
        try {
            PreparedStatement pst = conn.prepareStatement("insert into User (id, name, username, email, password, gender, country, BirthDate, userPicture) values ( ?,  ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, myUser.getId());
            pst.setString(2, myUser.getName());
            pst.setString(3, myUser.getUsername());
            pst.setString(4, myUser.getEmail());
            pst.setString(5, myUser.getPassword());
            pst.setBoolean(6, myUser.getGender());
            pst.setString(7, myUser.getCountry());
            //pst.setDate(8, myUser.getBirthdate());
            pst.setString(9, myUser.getUserPic());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

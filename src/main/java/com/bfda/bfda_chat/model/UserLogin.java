package com.bfda.bfda_chat.model;

import com.bfda.bfda_chat.interfaces.LoginInterface;
import java.security.*;
import java.sql.SQLException;

public class UserLogin implements LoginInterface {

    String userName;
    String password;
    User resultUser;
    Database database;
    public UserLogin(String userName , String password)
    {
        this.userName = userName;
        this.password = password;
        try {
            database = Database.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String userName , String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public void searchForUser() throws SQLException {

        resultUser = database.getUserObject(userName);
    }

    public boolean verifyUser()
    {

        if(resultUser.getUsername().trim().equalsIgnoreCase(userName.toLowerCase())
                && resultUser.getPassword().equalsIgnoreCase(password))
            return true;
        else
            return false;
       }


    public void login() throws SQLException {
        searchForUser();
        if(verifyUser())
            System.out.println("User Accepted");
        else
            System.out.println("User Forbidden");
    }



}

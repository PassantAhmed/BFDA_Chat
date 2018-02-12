package model;

import beans.User;
import interfaces.LoginInterface;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserLogin implements LoginInterface {

    String userName;
    String password;
    User resultUser;
    Database database;
    DatabaseUserOperation serverOperationClass;
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

    //for add user and login
    public void searchForUser() throws SQLException {

        try {
                         resultUser = serverOperationClass.clientAddAnotherClient(userName);
              } 
        catch (RemoteException ex)
        {
                        Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verifyUser()
    {
        if(resultUser.getUsername() == null)
            return false;
        else if(resultUser.getUsername().trim().equalsIgnoreCase(userName.toLowerCase())
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

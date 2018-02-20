package model;

import beans.User;
import interfaces.LoginInterface;
import model.database.Database;
import model.database.DatabaseUserOperation;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserLogin implements LoginInterface {

    String userName;
    String password;


    private User resultUser;
    private DatabaseUserOperation serverOperationClass;

    /**
     *
     * @param userName
     * @param password
    **/
    public UserLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;

        try {
            Database database = Database.getInstance();
            serverOperationClass = new DatabaseUserOperation();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userName
     * @param password
    **/
    public void setUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //for add user and login
    
    /**
     *
     * @throws SQLException
    **/
    public void searchForUser() throws SQLException {

        try {
            resultUser = serverOperationClass.clientAddAnotherClient(userName);
        } catch (RemoteException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verifyUser() {
        if (resultUser.getUsername() == null)
            return false;
        else if (resultUser.getUsername().trim().equalsIgnoreCase(userName.toLowerCase())
                && resultUser.getPassword().equalsIgnoreCase(password))
            return true;
        else
            return false;
    }

    /**
     *
     * @throws SQLException
    **/
    public boolean login() throws SQLException {
        searchForUser();
        if (verifyUser())
            return true;
        else
            return false;
    }

    public User getResultUser() {
        return resultUser;
    }


}

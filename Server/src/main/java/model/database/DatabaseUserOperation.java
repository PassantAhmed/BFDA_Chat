/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import beans.User;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.SqlParser;

/**
 * @author ahmedelgawesh
 */
public class DatabaseUserOperation {

    Database dbClass;
    Connection conn;

    public DatabaseUserOperation() throws SQLException {
        try {
            dbClass = Database.getInstance();
            conn = dbClass.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean clientRegister(User clientData) throws RemoteException {
        try {
            PreparedStatement pst = conn.prepareStatement("insert into User (id, name, username, email, password, gender, country, BirthDate, userPicture) "
                    + "values ( ?,  ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, clientData.getId());
            pst.setString(2, clientData.getName());
            pst.setString(3, clientData.getUsername());
            pst.setString(4, clientData.getEmail());
            pst.setString(5, clientData.getPassword());
            System.out.println(clientData.getGender());
            pst.setBoolean(6, clientData.getGender());
//            pst.setBoolean(6, true);
            pst.setString(7, clientData.getCountry());
            pst.setDate(8, SqlParser.fromLocalToSql(clientData.getBirthdate()));
            pst.setString(9, clientData.getUserPic());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean clientSignHisFlagStatus(beans.User user, boolean clientFlag) throws RemoteException {
        try {
            PreparedStatement pst = conn.prepareStatement("update User set statusFlag=?  where id=?");
            pst.setBoolean(1, clientFlag);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean clientSignHisModeStatus(beans.User user, String clientMode) throws RemoteException {
        try {
            PreparedStatement pst = conn.prepareStatement("update User set statusMode=?  where id=?");
            pst.setString(1, clientMode);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public User clientAddAnotherClient(String anotherClientToAdd) throws RemoteException {
        User user = new beans.User();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from User where username = ?");
            preparedStatement.setString(1, anotherClientToAdd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean isEmailExist(String email) {
        boolean emailExist = false;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from User where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //email is exist
                emailExist = true;
            } else {
                emailExist = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emailExist;
    }

    public boolean isUsernameExist(String username) {
        boolean usernameExist = false;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from User where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //username is exist
                usernameExist = true;
            } else {
                usernameExist = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usernameExist;
    }

    public int clientCreateGroupChat(String groupName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMemberToGroup(int groupID, String whichUserYouWillAdd) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<beans.User> clientSearchForUser(String whichUserWillGet) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getUsersNumber() {
        int usersNo = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select count(*) from User");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usersNo = resultSet.getInt(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersNo;
    }

    public int getStatistics() {
        int statisticsNo = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select count(*) from User where statusMode = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statisticsNo = resultSet.getInt(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUserOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statisticsNo;
    }

}

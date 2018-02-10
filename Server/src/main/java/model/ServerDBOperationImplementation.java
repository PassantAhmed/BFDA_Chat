/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ahmedelgawesh
 */
public class ServerDBOperationImplementation extends UnicastRemoteObject implements serverInterfaces.ServerDatabseOperation
{

                Database dbClass;
                Connection conn;
    public ServerDBOperationImplementation()throws RemoteException
{
    

    try  {
                          dbClass =Database.getInstance();
                          conn=dbClass.getConnection();
            }
            catch (SQLException ex) 
            {
                        Logger.getLogger(ServerDBOperationImplementation.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ClassNotFoundException ex) 
            {
                        Logger.getLogger(ServerDBOperationImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    /*
    public boolean clientLogin(String userName, String password) throws RemoteException 
    {
            return true;
    }
*/
    public boolean clientRegister(User clientData) throws RemoteException 
    {
        
 try {
            PreparedStatement pst = conn.prepareStatement("insert into User (id, name, username, email, password, gender, country, BirthDate, userPicture) values ( ?,  ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, clientData.getId());
            pst.setString(2, clientData.getName());
            pst.setString(3, clientData.getUsername());
            pst.setString(4, clientData.getEmail());
            pst.setString(5, clientData.getPassword());
            pst.setBoolean(6, clientData.getGender());
            pst.setString(7, clientData.getCountry());
            //pst.setDate(8, myUser.getBirthdate());
            pst.setString(9, clientData.getUserPic());
            pst.executeUpdate();
          
            }
            
            catch (SQLException ex) 
            {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
            }
        return true;
    }

    public boolean clientSignHisFlagStatus(boolean clientFlag) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean clientSignHisModeStatus(String clientMode) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

                @Override
    public User clientAddAnotherClient(String anotherClientToAdd) throws RemoteException 
    {
                    User user = new beans.User();

        try {
                    PreparedStatement preparedStatement = conn.prepareStatement("select * from User where username = ?");
                    preparedStatement.setString(1 , anotherClientToAdd);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next())
                    {
                                user.setId(resultSet.getInt(1));
                                user.setName(resultSet.getString(2));
                                user.setUsername(resultSet.getString(3));
                                user.setEmail(resultSet.getString(4));
                                user.setPassword(resultSet.getString(5));
                    }
                    }
                    catch (SQLException ex)
                    {
                                    Logger.getLogger(ServerDBOperationImplementation.class.getName()).log(Level.SEVERE, null, ex);
                     }
                            return user;

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
    
}

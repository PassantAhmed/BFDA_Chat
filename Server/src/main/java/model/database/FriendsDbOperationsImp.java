/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import beans.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.interfaces.FriendsDbOperations;
import serverInterfaces.FriendsDbOperations;

/**
 *
 * @author ahmedelgawesh
 */
public class FriendsDbOperationsImp extends UnicastRemoteObject implements FriendsDbOperations
{
    
    FriendsCrudDB friendsCrud;
    
    public  FriendsDbOperationsImp() throws RemoteException, SQLException 
    {
                friendsCrud= new FriendsCrudDB();
  
   }

    @Override
    public ArrayList<beans.User> searchForUser(String usrInformation) 
    
    {
            String selectStatement = "select id,name,username,email,password,gender,country,birthdate,userPic,status,mode from user where id= '"+usrInformation+"' or username= '"+usrInformation+"'";
            return (ArrayList<User>) friendsCrud.select(selectStatement);
    }
            
   

    @Override
    public boolean sendFriendRequest(int myId,int userId)
    {
            String sqlStatm= "INSERT INTO Friend (userid,friendid,requestflag) VALUES ('"+myId+",'"+userId+"',false)";
            boolean checkOperation = friendsCrud.insert(sqlStatm);
            return checkOperation;
    }

    @Override
    public void approveFriendRequset()
    {
        //user update for the flag to true where id 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refuseFriendRequest()
    {
    //use delete statment    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retrieveAllFriends() 
    {
        //as select but from friend table 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

    
}

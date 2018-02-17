/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.User;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.database.DatabaseUserOperation;
import server.interfaces.UserStatuesChangeInterface;
import model.database.DatabaseUserOperation;
/**
 *
 * @author David Emil
 */
public class UserStatuesChangeImpl extends UnicastRemoteObject implements UserStatuesChangeInterface, Serializable{
    DatabaseUserOperation DBO ;
    
    public UserStatuesChangeImpl() throws RemoteException
    {
    }
    @Override
    public void changeStatues(User user) throws RemoteException {
       
        
        try {
            DBO = new DatabaseUserOperation();

        } catch (SQLException ex) {
            Logger.getLogger(UserStatuesChangeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if(user.getStatus()== false){         
            DBO.clientSignHisFlagStatus(user, true);
        }else{
            DBO.clientSignHisFlagStatus(user, false);
        }
    }
    
}

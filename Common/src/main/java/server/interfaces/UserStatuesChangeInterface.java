/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import beans.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author David Emil
 */
public interface UserStatuesChangeInterface extends Remote {
    
    public void changeStatues(User user) throws RemoteException;
}

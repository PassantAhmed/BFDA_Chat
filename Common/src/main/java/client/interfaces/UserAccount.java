package client.interfaces;

import beans.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserAccount extends Remote{

   /**
     *
     * @param userID
     * @param status
     * @throws RemoteException
     */
    public void updateUserStatus(String userID , String status) throws RemoteException;
    
   /**
     *
     * @param userID
     * @param mode
     * @throws RemoteException
     */
    public void updateUserMode(String userID , String mode) throws RemoteException;
    
   /**
     *
     * @param userID
     * @param Contacts
     * @throws RemoteException
     */
    public void updateUserContactList(String userID , List<User> Contacts) throws RemoteException;

}

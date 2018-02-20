package model.user;

import beans.User;
import client.interfaces.UserAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserAccountImpl extends UnicastRemoteObject implements UserAccount {

    /**
    * 
    * @param controller
    * @throws RemoteException
    **/
    public UserAccountImpl(Object controller) throws RemoteException {

    }

    /**
    * 
    * @throws RemoteException
    **/
    public UserAccountImpl() throws RemoteException {
    }

    /**
    * 
    * @param userID
    * @param status
    **/
    public void updateUserStatus(String userID, String status) {

    }

    /**
    * 
    * @param userID
    * @param mode
    **/
    public void updateUserMode(String userID, String mode) {

    }

    /**
    *
    * @param userID
    * @param Contacts
    **/
    public void updateUserContactList(String userID, List<User> Contacts) {

    }
}

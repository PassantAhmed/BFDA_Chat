package model;

import beans.User;
import clientInterfaces.UserAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserAccountImpl extends UnicastRemoteObject implements UserAccount {

    public UserAccountImpl(Object controller) throws RemoteException {

    }

    protected UserAccountImpl() throws RemoteException {
    }

    public void updateUserStatus(String userID, String status) {

    }

    public void updateUserMode(String userID, String mode) {

    }

    public void updateUserContactList(String userID, List<User> Contacts) {

    }
}

package model.user;

import beans.User;
import client.interfaces.UserAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserAccountImpl extends UnicastRemoteObject implements UserAccount {

    public UserAccountImpl(Object controller) throws RemoteException {

    }

    public UserAccountImpl() throws RemoteException {
    }

    public void updateUserStatus(String userID, String status) {

    }

    public void updateUserMode(String userID, String mode) {

    }

    public void updateUserContactList(String userID, List<User> Contacts) {

    }
}

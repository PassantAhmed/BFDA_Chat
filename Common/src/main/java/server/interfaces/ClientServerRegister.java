package server.interfaces;


import beans.User;
import client.interfaces.ClientObj;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ClientServerRegister extends Remote {

    public void registerUser(ClientObj clientObject) throws RemoteException;
    public void unRegisterUser(ClientObj clientObject)throws RemoteException;

    public User userLogin(String username , String password) throws RemoteException;
    public Boolean newUserRegisteration(User user) throws RemoteException;
}

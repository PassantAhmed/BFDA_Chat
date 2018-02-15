package model;

import beans.User;
import client.interfaces.ClientObj;
import server.interfaces.ClientServerRegister;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Vector;

public class ClientServerRegisterImp extends UnicastRemoteObject implements ClientServerRegister, Serializable {

    public static Vector<ClientObj> clientObject = new Vector<ClientObj>();

    protected ClientServerRegisterImp() throws RemoteException {

    }

    @Override
    public void registerUser(ClientObj clientObject) {
        System.out.print("Registered");
        ClientServerRegisterImp.clientObject.add(clientObject);
    }

    @Override
    public void unRegisterUser(ClientObj clientObject) {
        ClientServerRegisterImp.clientObject.remove(clientObject);
    }


    @Override
    public User userLogin(String username, String password) throws RemoteException {
        UserLogin login = new UserLogin(username , password);
        User user = null;
        try {
            if(login.login())
            {
                user = login.getResultUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public Boolean newUserRegisteration(User user) throws RemoteException {
        return null;
    }
}

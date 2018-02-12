package model;

import clientInterfaces.ClientObj;
import serverInterfaces.ClientServerRegister;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
}

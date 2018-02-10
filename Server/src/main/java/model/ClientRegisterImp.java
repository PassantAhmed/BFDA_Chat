package model;

import clientInterfaces.ClientObj;
import serverInterfaces.ClientRegister;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ClientRegisterImp extends UnicastRemoteObject implements  ClientRegister , Serializable {

    public static Vector<ClientObj> clientObject = new Vector<ClientObj>();

    protected ClientRegisterImp() throws RemoteException {

    }

    @Override
    public void registerUser(ClientObj clientObject) {
        System.out.print("Registered");
        ClientRegisterImp.clientObject.add(clientObject);
    }

    @Override
    public void unRegisterUser(ClientObj clientObject) {
        ClientRegisterImp.clientObject.remove(clientObject);
    }
}

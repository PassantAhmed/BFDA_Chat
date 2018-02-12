package model;

import serverInterfaces.ClientServerRegister;
import serverInterfaces.ServerMessegeSender;
import serverInterfaces.ServerObj;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServerObject extends UnicastRemoteObject implements ServerObj, Serializable {

    private ClientServerRegister clientServerRegister;
    private DatabaseUserOperation databaseUserOperation;
    private ServerMessegeSender serverMessegeSender;

    public ServerObject() throws RemoteException {

        clientServerRegister = new ClientServerRegisterImp();
        databaseUserOperation = new model.DatabaseUserOperation();
        serverMessegeSender = new ServerMessageSenderImplementation();
    }

    public ClientServerRegister getClientServerRegister() {
        return clientServerRegister;
    }


    public DatabaseUserOperation getDatabaseUserOperation() {
        return databaseUserOperation;
    }


    public ServerMessegeSender getServerMessegeSender() {
        return serverMessegeSender;
    }


}

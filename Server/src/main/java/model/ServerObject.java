package model;

import model.database.DatabaseUserOperation;
import serverInterfaces.ClientServerRegister;
import serverInterfaces.ServerMessegeSender;
import serverInterfaces.ServerObj;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


public class ServerObject extends UnicastRemoteObject implements ServerObj, Serializable {

    private ClientServerRegister clientServerRegister;
    private DatabaseUserOperation databaseUserOperation;
    private ServerMessegeSender serverMessegeSender;

    public ServerObject() throws RemoteException, SQLException {

        clientServerRegister = new ClientServerRegisterImp();
        databaseUserOperation = new DatabaseUserOperation();
        serverMessegeSender = new ServerMessageSenderImplementation();
    }

    public ClientServerRegister getClientServerRegister() {
        return clientServerRegister;
    }


    public ServerMessegeSender getServerMessegeSender() {
        return serverMessegeSender;
    }


}

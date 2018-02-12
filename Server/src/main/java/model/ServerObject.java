package model;

import serverInterfaces.ClientServerRegister;
import serverInterfaces.DatabaseUserOperation;
import serverInterfaces.ServerMessegeSender;
import serverInterfaces.ServerObj;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServerObject extends UnicastRemoteObject implements ServerObj ,  Serializable   {

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

    public void setClientServerRegister(ClientServerRegister clientServerRegister) {
        this.clientServerRegister = clientServerRegister;
    }

    public DatabaseUserOperation getDatabaseUserOperation() {
        return databaseUserOperation;
    }

    public void setDatabaseUserOperation(DatabaseUserOperation databaseUserOperation) {
        this.databaseUserOperation = databaseUserOperation;
    }

    public ServerMessegeSender getServerMessegeSender() {
        return serverMessegeSender;
    }

    public void setServerMessegeSender(ServerMessegeSender serverMessegeSender) {
        this.serverMessegeSender = serverMessegeSender;
    }

}

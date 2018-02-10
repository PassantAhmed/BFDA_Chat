package model;

import serverInterfaces.ClientRegister;
import serverInterfaces.ServerDatabseOperation;
import serverInterfaces.ServerMessegeSender;
import serverInterfaces.ServerObj;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServerObject extends UnicastRemoteObject implements ServerObj ,  Serializable   {

    private ClientRegister clientRegister;
    private ServerDatabseOperation serverDatabseOperation;
    private ServerMessegeSender serverMessegeSender;

    public ServerObject() throws RemoteException {

        clientRegister = new ClientRegisterImp();
        serverDatabseOperation = new ServerDBOperationImplementation();
        serverMessegeSender = new ServerMessageSenderImplementation();
    }

    public ClientRegister getClientRegister() {
        return clientRegister;
    }

    public void setClientRegister(ClientRegister clientRegister) {
        this.clientRegister = clientRegister;
    }

    public ServerDatabseOperation getServerDatabseOperation() {
        return serverDatabseOperation;
    }

    public void setServerDatabseOperation(ServerDatabseOperation serverDatabseOperation) {
        this.serverDatabseOperation = serverDatabseOperation;
    }

    public ServerMessegeSender getServerMessegeSender() {
        return serverMessegeSender;
    }

    public void setServerMessegeSender(ServerMessegeSender serverMessegeSender) {
        this.serverMessegeSender = serverMessegeSender;
    }

}

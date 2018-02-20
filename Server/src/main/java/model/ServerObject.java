package model;

import model.database.DatabaseUserOperation;
import model.database.FriendsDbOperationsImp;
import server.interfaces.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


public class ServerObject extends UnicastRemoteObject implements ServerObj, Serializable {

    private ClientServerRegister clientServerRegister;
    private DatabaseUserOperation databaseUserOperation;
    private ServerMessegeSender serverMessegeSender;
    private FriendsDbOperations friendsDbOperations;
    private ServerFileTransferInterfaceImpl serverFileTransferImpl;
    private UserStatuesChangeImpl userStatuesChangeImpl;
    private ServerFriendRequestImpl serverFriendRequest;
   
    /**
     *
     * @throws RemoteException 
     * @throws SQLException
     * @throws ClassNotFoundException
    **/
    public ServerObject() throws RemoteException, SQLException, ClassNotFoundException {

        clientServerRegister = new ClientServerRegisterImp();
        databaseUserOperation = new DatabaseUserOperation();
        serverMessegeSender = new ServerMessageSenderImplementation();
        friendsDbOperations = new FriendsDbOperationsImp();
        serverFileTransferImpl = new ServerFileTransferInterfaceImpl();
        userStatuesChangeImpl = new UserStatuesChangeImpl();
        serverFriendRequest = new ServerFriendRequestImpl();
    }


    @Override
    public ClientServerRegister getClientServerRegister() {
        return clientServerRegister;
    }
    @Override
    public ServerMessegeSender getServerMessegeSender() {
        return serverMessegeSender;
    }
    @Override
    public FriendsDbOperations getFriendsDbOperations() throws RemoteException { return friendsDbOperations; }
    @Override
    public ServerFileTransferInterfaceImpl getServerFileTransfer() { return serverFileTransferImpl; }

    public UserStatuesChangeImpl getUserStatuesChangeImpl() {
        return userStatuesChangeImpl;
    }

    public FriendRequest getServerFriendRequest() { return serverFriendRequest; }
}

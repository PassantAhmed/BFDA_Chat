package server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ServerObj extends Remote {

    public ClientServerRegister getClientServerRegister() throws RemoteException ;
    public ServerMessegeSender getServerMessegeSender()throws RemoteException;
    public FriendsDbOperations getFriendsDbOperations() throws RemoteException;
}

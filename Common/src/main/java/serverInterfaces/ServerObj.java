package serverInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ServerObj extends Remote {

    public ClientServerRegister getClientServerRegister() throws RemoteException ;
    public void setClientServerRegister(ClientServerRegister clientServerRegister) throws RemoteException;
    public DatabaseUserOperation getDatabaseUserOperation()throws RemoteException;
    public void setDatabaseUserOperation(DatabaseUserOperation databaseUserOperation) throws RemoteException;
    public ServerMessegeSender getServerMessegeSender()throws RemoteException;
    public void setServerMessegeSender(ServerMessegeSender serverMessegeSender)throws RemoteException;
}

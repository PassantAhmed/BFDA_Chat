package serverInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ServerObj extends Remote {

    public ClientRegister getClientRegister() throws RemoteException ;
    public void setClientRegister(ClientRegister clientRegister) throws RemoteException;
    public ServerDatabseOperation getServerDatabseOperation()throws RemoteException;
    public void setServerDatabseOperation(ServerDatabseOperation serverDatabseOperation) throws RemoteException;
    public ServerMessegeSender getServerMessegeSender()throws RemoteException;
    public void setServerMessegeSender(ServerMessegeSender serverMessegeSender)throws RemoteException;
}

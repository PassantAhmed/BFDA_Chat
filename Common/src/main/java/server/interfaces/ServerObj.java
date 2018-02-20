package server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ServerObj extends Remote {

   /**
     *
     * @throws RemoteException
     */
    public ClientServerRegister getClientServerRegister() throws RemoteException ;
    
   /**
     *
     * @throws RemoteException
     */
    public ServerMessegeSender getServerMessegeSender()throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public FriendsDbOperations getFriendsDbOperations() throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public ServerFileTransferInterface getServerFileTransfer() throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public UserStatuesChangeInterface getUserStatuesChangeImpl() throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public FriendRequest getServerFriendRequest()throws RemoteException;
}

package client.interfaces;

import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ClientObj extends Remote {

   /**
     *
     * @throws RemoteException
     */
    public ChatHandler getChatHandler() throws RemoteException;

   /**
     *
     * @param chatHandler 
     * @throws RemoteException
     */
    public void setChatHandler(ChatHandler chatHandler) throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public UserAccount getUserAccount()throws RemoteException;
    
   /**
     *
     * @param userAccount 
     * @throws RemoteException
     */
    public void setUserAccount(UserAccount userAccount)throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public ClientFileTransferInterface getClientFileTransfer()throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public User getUserData() throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public ConnectionValidation getConnectionValidation() throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public FriendRequest getClientFriendRequest() throws RemoteException;
}

package client.interfaces;

import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public interface ClientObj extends Remote {


    public ChatHandler getChatHandler() throws RemoteException;
    public void setChatHandler(ChatHandler chatHandler) throws RemoteException;
    public UserAccount getUserAccount()throws RemoteException;
    public void setUserAccount(UserAccount userAccount)throws RemoteException;
    public ClientFileTransferInterface getClientFileTransfer()throws RemoteException;
    public User getUserData() throws RemoteException;
    public ConnectionValidation getConnectionValidation() throws RemoteException;
}

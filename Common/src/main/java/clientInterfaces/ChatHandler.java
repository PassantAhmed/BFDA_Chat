package clientInterfaces;

import beans.Message;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatHandler extends Remote , Serializable{

    public void updateChat(String chatID , Message message)throws RemoteException;
    public void updateAnnouncement(String msg)throws RemoteException;
    public boolean updateConnection() throws RemoteException;

}

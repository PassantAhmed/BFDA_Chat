package clientInterfaces;

import beans.Message;
import beans.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatHandler extends Remote , Serializable{

    public void updateChat(String chatID , Message message)throws RemoteException;
    public void registerChat(String chatID , Vector<String> users) throws RemoteException;
    public void updateAnnouncement(String msg)throws RemoteException;
    public boolean updateConnection() throws RemoteException;

}

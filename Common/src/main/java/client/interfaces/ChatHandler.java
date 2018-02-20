package client.interfaces;

import beans.Group;
import beans.Message;
import beans.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatHandler extends Remote , Serializable{

   /**
     *
     * @param chatID
     * @param message
     * @throws RemoteException
     */
    public void updateChat(String chatID , Message message)throws RemoteException;
    
   /**
     *
     * @param chatID
     * @param users
     * @throws RemoteException
     */
    public void registerChat(String chatID , Vector<String> users) throws RemoteException;
    
   /**
     *
     * @param msg
     * @throws RemoteException
     */
    public void updateAnnouncement(String msg)throws RemoteException;
    
   /**
     *
     * @throws RemoteException
     */
    public boolean updateConnection() throws RemoteException;
 
   /**
     *
     * @param group
     * @throws RemoteException
     */
    public void notifyGroupChat(Group group) throws RemoteException;

}

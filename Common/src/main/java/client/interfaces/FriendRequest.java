package client.interfaces;

import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FriendRequest extends Remote {

   /**
     *
     * @param friendRequester 
     * @throws RemoteException
     */
    public void notifyFriendRequest(User friendRequester) throws RemoteException;
    
   /**
     *
     * @param receiver 
     * @param result 
     * @throws RemoteException
     */
    public void friendRequestResult(User receiver , boolean result) throws RemoteException;
}

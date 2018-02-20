package server.interfaces;

import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface FriendRequest extends Remote {

   /**
     *
     * @param sender
     * @param receiver  
     * @throws RemoteException
     * @throws SQLException
     */
    public void sendFriendRequest(User sender , User receiver) throws RemoteException, SQLException;
    
   /**
     *
     * @param requested 
     * @param requester  
     * @param result
     * @throws RemoteException
     * @throws SQLException
     */
    public void friendRequestResult(User requested , User requester , boolean result) throws RemoteException, SQLException;
}

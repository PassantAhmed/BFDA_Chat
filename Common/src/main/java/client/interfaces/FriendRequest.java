package client.interfaces;

import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FriendRequest extends Remote {

    public void notifyFriendRequest(User friendRequester) throws RemoteException;
    public void friendRequestResult(User receiver , boolean result) throws RemoteException;
}

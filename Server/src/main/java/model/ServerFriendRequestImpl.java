package model;

import beans.User;
import model.database.FriendsDbOperationsImp;
import server.interfaces.FriendRequest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ServerFriendRequestImpl extends UnicastRemoteObject implements FriendRequest {
    protected ServerFriendRequestImpl() throws RemoteException {
    }

    @Override
    public void sendFriendRequest(User requester, User requested) throws RemoteException, SQLException {

        FriendsDbOperationsImp friendsDbOperationsImp = new FriendsDbOperationsImp();
        friendsDbOperationsImp.sendFriendRequest(requester.getId() , requested.getId());

        if(ClientServerRegisterImp.clientObjHashMap.get(requested.getUsername()) != null)
            ClientServerRegisterImp.clientObjHashMap.get(requested.getUsername())
                    .getClientFriendRequest().notifyFriendRequest(requester);

    }

    @Override
    public void friendRequestResult(User requested , User requester , boolean result) throws RemoteException, SQLException {
        FriendsDbOperationsImp friendsDbOperationsImp = new FriendsDbOperationsImp();
        if(result)
        {
            friendsDbOperationsImp.approveFriendRequset(requested.getId() , requester.getId());
            if(ClientServerRegisterImp.clientObjHashMap.get(requester.getUsername()) != null)
                ClientServerRegisterImp.clientObjHashMap.get(requester.getUsername()).getClientFriendRequest()
                .friendRequestResult(requested , result);
        }
        else
        {
            friendsDbOperationsImp.refuseFriendRequest( requested.getId() , requester.getId());
            if(ClientServerRegisterImp.clientObjHashMap.get(requester.getUsername()) != null)
                ClientServerRegisterImp.clientObjHashMap.get(requester.getUsername()).getClientFriendRequest()
                        .friendRequestResult(requested , result);
        }
    }
}

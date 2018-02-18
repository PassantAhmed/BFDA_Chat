package model;

import beans.User;
import client.interfaces.FriendRequest;
import javafx.application.Platform;
import view.controller.ControllerManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientFriendRequestImpl extends UnicastRemoteObject implements FriendRequest{
    protected ClientFriendRequestImpl() throws RemoteException {
    }

    @Override
    public void notifyFriendRequest(User friendRequester) throws RemoteException {
        Platform.runLater(()->{
            ControllerManager.getInstance().getMainController().updateFriendRequests().add(friendRequester);
        });
    }

    @Override
    public void friendRequestResult(User receiver , boolean result) throws RemoteException {
        if(result)
        {

           Platform.runLater(()->{
               ControllerManager.getInstance().getMainController().getFriendList().add(receiver);
               ControllerManager.getInstance().getMainController().updateFriendRequests().remove(receiver);
               ControllerManager.getInstance().getMainController().getReqFriendsListView().refresh();
           });
        }
        else
        {
            Platform.runLater(()->{
            ControllerManager.getInstance().getMainController().updateFriendRequests().remove(receiver);
            ControllerManager.getInstance().getMainController().getReqFriendsListView().refresh();
            });
        }
    }
}

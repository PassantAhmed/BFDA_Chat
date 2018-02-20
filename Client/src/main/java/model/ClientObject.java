package model;

import beans.User;
import client.interfaces.*;
import model.chat.ChatImpl;
import model.filetranfer.ClientFileTransferImpl;
import model.user.UserAccountImpl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public class ClientObject extends UnicastRemoteObject implements ClientObj, Serializable {

    private ChatHandler chatHandler;
    private UserAccount userAccount;
    private ClientFileTransferImpl clientFileTransfer;
    private ConnectionValidationImpl connectionValidation;



    private ClientFriendRequestImpl clientFriendRequest;
    private static User userData;

    public static void setUserData(User userData) {
        ClientObject.userData = userData;
    }
    public static User getUserDataInternal(){return userData;};

    /**
    * 
    * @throws RemoteException 
    **/
    public ClientObject() throws RemoteException {
        chatHandler = new ChatImpl();
        userAccount = new UserAccountImpl();
        clientFileTransfer = new ClientFileTransferImpl();
        connectionValidation = new ConnectionValidationImpl();
        clientFriendRequest = new ClientFriendRequestImpl();
    }

    public ChatHandler getChatHandler() {
        return chatHandler;
    }

    public void setChatHandler(ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    /**
    * 
    * @throws RemoteException 
    **/
    @Override
    public ClientFileTransferInterface getClientFileTransfer() throws RemoteException {
        return clientFileTransfer;
    }

    /**
    * 
    * @throws RemoteException 
    **/
    @Override
    public User getUserData() throws RemoteException {
        return userData;
    }

    /**
    * 
    * @throws RemoteException 
    **/
    @Override
    public ConnectionValidation getConnectionValidation() throws RemoteException {
        return connectionValidation;
    }

    public ClientFriendRequestImpl getClientFriendRequest() {
        return clientFriendRequest;
    }
}

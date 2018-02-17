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
    private static User userData;

    public static void setUserData(User userData) {
        ClientObject.userData = userData;
    }
    public static User getUserDataInternal(){return userData;};


    public ClientObject() throws RemoteException {
        chatHandler = new ChatImpl();
        userAccount = new UserAccountImpl();
        clientFileTransfer = new ClientFileTransferImpl();
        connectionValidation = new ConnectionValidationImpl();
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

    @Override
    public ClientFileTransferInterface getClientFileTransfer() throws RemoteException {
        return clientFileTransfer;
    }

    @Override
    public User getUserData() throws RemoteException {
        return userData;
    }

    @Override
    public ConnectionValidation getConnectionValidation() throws RemoteException {
        return connectionValidation;
    }
}

package model;

import beans.User;
import clientInterfaces.ChatHandler;
import clientInterfaces.ClientObj;
import clientInterfaces.UserAccount;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Muhammed Fawzy on 2/10/2018.
 */
public class ClientObject extends UnicastRemoteObject implements ClientObj , Serializable {

    private ChatHandler chatHandler;
    private UserAccount userAccount;

    public static void setUserData(User userData) {
        ClientObject.userData = userData;
    }
    public static User getUserDataInternal(){return userData;};
    private static User userData;

    public ClientObject() throws RemoteException {
        chatHandler = new ChatImpl();
        userAccount = new UserAccountImpl();
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
    public User getUserData() throws RemoteException {
        return userData;
    }
}

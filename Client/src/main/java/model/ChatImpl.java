package model;

import clientInterfaces.ChatHandler;
import viewcontroller.MainWindowController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ChatImpl extends UnicastRemoteObject implements ChatHandler {

    private static MainWindowController mainController;

    public ChatImpl() throws RemoteException {
    }

    public void setMainController(MainWindowController mainController)
    {
        ChatImpl.mainController = mainController;
    }

    @Override
    public void updateChat(String chatID, String msg, String clientID)
    {

    }

    @Override
    public void updateAnnouncement(String msg)
    {
        mainController.updateAnnounce(msg);
        System.out.print(msg);
    }

    @Override
    public boolean updateConnection() {
        return true;
    }


}

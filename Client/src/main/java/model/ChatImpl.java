package model;

import beans.Message;
import clientInterfaces.ChatHandler;
import viewcontroller.MainController;
import viewcontroller.MainWindowController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ChatImpl extends UnicastRemoteObject implements ChatHandler {

    private static MainController mainController;

    public ChatImpl() throws RemoteException {
    }

    public static void setMainController(MainController mainController)
    {
        ChatImpl.mainController = mainController;
    }

    @Override
    public void updateChat(String chatID, Message message)
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

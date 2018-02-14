package model;

import beans.Message;
import beans.User;
import clientInterfaces.ChatHandler;
import viewcontroller.MainController;
import viewcontroller.MainWindowController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Vector;


public class ChatImpl extends UnicastRemoteObject implements ChatHandler {

    private static MainController mainController;
    private ClientChatFlowControl clientChatFlowControl;
    public ChatImpl() throws RemoteException {
        clientChatFlowControl = new ClientChatFlowControl();
    }

    public static void setMainController(MainController mainController)
    {
        ChatImpl.mainController = mainController;
    }

    @Override
    public void updateChat(String chatID, Message message)
    {
        mainController.updateAnnounce(message.getMessageContent());
    }

    @Override
    public void registerChat(String chatID, Vector<String> users) throws RemoteException {
        System.out.println("Registering");
        ClientChatFlowControl.setChatMap(chatID , users);
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

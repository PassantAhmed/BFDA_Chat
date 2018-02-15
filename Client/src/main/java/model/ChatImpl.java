package model;

import beans.Message;
import client.interfaces.ChatHandler;
import javafx.application.Platform;
import view.controller.ControllerManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


public class ChatImpl extends UnicastRemoteObject implements ChatHandler {

    private ControllerManager mainController;
    public ChatImpl() throws RemoteException {
        this.mainController = ControllerManager.getInstance();
    }





    @Override
    public void updateChat(String chatID, Message message)
    {
        System.out.println(mainController.getWelcomeController() == null);
        if(mainController.getMainController().getCurrentChatID().equals(chatID))
            Platform.runLater(()->{mainController.getMainController().getCurrentChatObserver().add(message);});
    }

    @Override
    public void registerChat(String chatID, Vector<String> users) throws RemoteException {
    }

    @Override
    public void updateAnnouncement(String msg)
    {
        mainController.getMainController().updateAnnounce(msg);
        System.out.print(msg);
    }

    @Override
    public boolean updateConnection() {
        return true;
    }




}

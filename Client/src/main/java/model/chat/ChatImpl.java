package model.chat;

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
        {
            mainController.getMainController().getMsgMap().get(chatID).add(message);
            Platform.runLater(()->{mainController.getMainController().getCurrentChatObserver().add(message);});
        }
        else
        {
            if(mainController.getMainController().getMsgMap().containsKey(chatID))
            {
                mainController.getMainController().getMsgMap().get(chatID).add(message);
            }
            else
            {
                Vector<Message> msgs = new Vector<Message>();
                msgs.add(message);
                mainController.getMainController().getMsgMap().put(chatID , msgs );
            }

        }
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

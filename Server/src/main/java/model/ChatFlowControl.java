package model;

import beans.Message;
import beans.User;
import clientInterfaces.ClientObj;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public class ChatFlowControl {

    public static HashMap<String , List<ClientObj>> chatMap = new HashMap<>();

    public void registerNewChat(String identifier , List<ClientObj> users)
    {
        chatMap.put(identifier , users);
    }

    public void deleteChat(String identifier)
    {
        chatMap.remove(identifier);
    }

    public void sendMsg(String identifier , Message message) throws RemoteException {

        for(ClientObj user : chatMap.get(identifier))
        {
            user.getChatHandler().updateChat(identifier , message);
        }
    }
}

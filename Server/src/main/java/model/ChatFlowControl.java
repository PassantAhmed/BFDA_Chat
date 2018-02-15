package model;

import beans.Message;
import beans.User;
import client.interfaces.ClientObj;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Vector;

public class ChatFlowControl {



    public static HashMap<String , Vector<ClientObj>> chatMap = new HashMap<>();

    public void registerNewChat(String identifier , Vector<String> users) throws RemoteException {

        chatMap.put(identifier , fromUserToClientObject(users));
        notifyNewChatToClients(identifier , chatMap.get(identifier) , users);
    }

    public void deleteChat(String identifier)
    {
        chatMap.remove(identifier);
    }

    public void sendMsg(String identifier , Message message) throws RemoteException {


        for(ClientObj user : chatMap.get(identifier))
        {
            if(isOnline(user))
                user.getChatHandler().updateChat(identifier , message);
        }
    }

    private Vector<ClientObj> fromUserToClientObject(Vector<String> users) throws RemoteException {
        Vector<ClientObj> clientObjs = new Vector<>();
        for(String user : users)
        {
            for(ClientObj obj : ClientServerRegisterImp.clientObject)
            {
                if(obj.getUserData().getUsername().equals(user))
                {
                    clientObjs.add(obj);
                }
            }
        }
        return clientObjs;
    }

    private void notifyNewChatToClients(String chatID , Vector<ClientObj> users , Vector<String> userNames) throws RemoteException {
        for(ClientObj clientObj : users)
        {
            if(isOnline(clientObj))
            {
                System.out.println("Are Online");
                clientObj.getChatHandler().registerChat(chatID , userNames);
            }
        }
    }

    private boolean isOnline(User user) throws RemoteException {
        for(ClientObj clientObj : ClientServerRegisterImp.clientObject)
        {
            if(clientObj.getUserData().getUsername().equals(user.getUsername()))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isOnline(ClientObj user) throws RemoteException {
        return ClientServerRegisterImp.clientObject.contains(user);
    }



}

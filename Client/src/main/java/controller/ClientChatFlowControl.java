package controller;

import beans.Message;
import model.ServerConnection;
import serverInterfaces.ServerObj;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Vector;

public class ClientChatFlowControl {

    private static HashMap<String, Vector<String>> chatMap = new HashMap<>();
    private ServerConnection serverConnection = ServerConnection.getInstance();
    private ServerObj serverObj;

    public ClientChatFlowControl() throws RemoteException {
        serverObj = serverConnection.getRegisteryObject();
    }

    public static void setChatMap(String chatID, Vector<String> users) {
        ClientChatFlowControl.chatMap.put(chatID, users);
    }

    public void getMsg(String chatID, Message message) {
        System.out.println(message.getMessageContent());
    }

    public void sendMsg(String chatID, Message message) throws RemoteException {
        serverObj.getServerMessegeSender().sendMsgToChat(chatID, message);
    }

    public String sendNewChatRequest(String chatID, Vector<String> user) throws RemoteException {
        String existCheck = checkChatExist(user);
        if (existCheck == null) {
            serverObj.getServerMessegeSender().chatStartRequest(chatID, user);
            return chatID;
        }
        return existCheck;
    }

    public String checkChatExist(Vector<String> user) {
        boolean result = false;
        Vector<String> userNames = null;
        for (Vector<String> users : chatMap.values()) {
            if (users.containsAll(user)) {
                userNames = users;
                result = true;
                break;
            }
        }

        if (result) {
            for (String key : chatMap.keySet()) {
                if (chatMap.get(key) == userNames)
                    return key;
            }
        }
        return null;
    }

}

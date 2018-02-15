/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Message;
import client.interfaces.ClientObj;
import com.sun.security.ntlm.Server;
import model.database.DatabaseChatOperation;
import server.interfaces.ClientServerRegister;
import server.interfaces.ServerMessegeSender;
import server.interfaces.ServerObj;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * @author ahmedelgawesh
 */
public class ServerMessageSenderImplementation extends UnicastRemoteObject implements ServerMessegeSender {

//    ChatFlowControl chatFlowControl ;

//    public ServerMessageSenderImplementation() throws RemoteException {
////        chatFlowControl = new ChatFlowControl();
////    }
////
////    @Override
////    public void chatStartRequest(String chatID, Vector<String> users) throws RemoteException {
////        chatFlowControl.registerNewChat(chatID , users);
////    }
////
////    @Override
////    public void sendMsgToChat(String chatID, Message message) throws RemoteException {
////        chatFlowControl.sendMsg(chatID , message);
////    }



    DatabaseChatOperation databaseChatOperation;

    public ServerMessageSenderImplementation() throws SQLException, ClassNotFoundException ,RemoteException {
        databaseChatOperation = new DatabaseChatOperation();
    }


    public String getChatOfClient(String user ,String clientName) throws SQLException {
        return databaseChatOperation.getChatRoomOfClient(user , clientName);
    }

    public void sendMsg(String chatMemberID , Message msg) throws SQLException, RemoteException {
        String msgID = databaseChatOperation.sendMsgtoDatabase(chatMemberID , msg);
        String chatRoomID = databaseChatOperation.getChatRoomForChatMember(chatMemberID);
        Vector<String> chatMembers = databaseChatOperation.chatMembers(msgID);

        System.out.println("Message ID :"+msgID);
        System.out.println("Got Chat Members : " + chatMembers.size());

        for(String chatMember : chatMembers)
        {
            System.out.println(chatMember);
            for(ClientObj obj : ClientServerRegisterImp.clientObject)
            {
                if(obj.getUserData().getUsername().equals(chatMember))
                {
                    obj.getChatHandler().updateChat(chatRoomID , msg);
                }
            }
        }
    }

    public String getChatRoomOfClient(String myName , String clientName) throws SQLException {
        return databaseChatOperation.getChatRoomOfClient(myName , clientName);

    }

    public String getChatMemberID(String userName , String chatRoomID) throws SQLException
    {
        return databaseChatOperation.getChatMemberID(userName , chatRoomID);
    }


}

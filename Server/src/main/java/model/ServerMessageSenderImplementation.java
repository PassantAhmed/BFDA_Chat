/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Group;
import beans.Message;
import client.interfaces.ClientObj;
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

    DatabaseChatOperation databaseChatOperation;

    public ServerMessageSenderImplementation() throws SQLException, ClassNotFoundException ,RemoteException {
        databaseChatOperation = new DatabaseChatOperation();
    }

    public String getChatOfClient(String user ,String clientName) throws SQLException {
        return databaseChatOperation.getChatRoomOfClient(user , clientName);
    }

    public synchronized void sendMsg(String chatMemberID ,String chatRoomID, Vector<String> members ,  Message msg)  {
        new Thread(()->{
            try {
                databaseChatOperation.sendMsgtoDatabase(chatMemberID , msg);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("Got Chat Members : " + members.size());
                ClientObj obj;
                for (String chatMember : members) {
                    obj = ClientServerRegisterImp.clientObjHashMap.get(chatMember);
                    if (obj != null)
                        obj.getChatHandler().updateChat(chatRoomID, msg);
                }
            } catch (RemoteException e) {
                System.out.println("Msg Not Sent !");
            }
        }).start();

    }

    public String getChatRoomOfClient(String myName , String clientName) throws SQLException {
        return databaseChatOperation.getChatRoomOfClient(myName , clientName);

    }

    public String getChatMemberID(String userName , String chatRoomID) throws SQLException {
        return databaseChatOperation.getChatMemberID(userName , chatRoomID);
    }

    public Vector<Message> getAllRoomMessages(String chatRoomID) throws SQLException {
        return databaseChatOperation.getAllRoomMessages(chatRoomID);
    }

    public Vector<Group> getAllGroups(int myID) throws SQLException {
       return databaseChatOperation.getAllGroups(myID);
    }

    public Vector<String> getAllChatMember(String chatID) throws SQLException    {
        return databaseChatOperation.getAllChatMember(chatID);


    }
}

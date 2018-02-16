/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import beans.Group;
import beans.Message;
import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ahmedelgawesh
 */
public interface ServerMessegeSender extends Remote
{
    public String getChatOfClient(String user ,String clientName) throws RemoteException, SQLException;
    public void sendMsg(String chatMemberID ,String chatRoomID , Vector<String> messgaes , Message msg) throws RemoteException, SQLException;
    public String getChatRoomOfClient(String myName , String clientName) throws RemoteException, SQLException;
    public String getChatMemberID(String userName , String chatRoomID) throws SQLException , RemoteException;
    public Vector<Message> getAllRoomMessages(String chatRoomID) throws SQLException , RemoteException;
    public Vector<Group> getAllGroups(int myID) throws SQLException , RemoteException;
    public Vector<String> getAllChatMember(String chatID) throws SQLException , RemoteException;
}

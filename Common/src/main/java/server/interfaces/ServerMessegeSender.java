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
    
   /**
     *
     * @param user
     * @param clientName 
     * @throws RemoteException
     * @throws SQLException
     */
    public String getChatOfClient(String user ,String clientName) throws RemoteException, SQLException;
    
   /**
     *
     * @param chatMemberID 
     * @param chatRoomID 
     * @param messgaes 
     * @param msg
     * @throws RemoteException
     * @throws SQLException
     */
    public void sendMsg(String chatMemberID ,String chatRoomID , Vector<String> messgaes , Message msg) throws RemoteException, SQLException;
    
   /**
     *
     * @param myName 
     * @param clientName 
     * @throws RemoteException
     * @throws SQLException
     */
    public String getChatRoomOfClient(String myName , String clientName) throws RemoteException, SQLException;
    
   /**
     *
     * @param userName 
     * @param chatRoomID 
     * @throws SQLException
     * @throws RemoteException
     */
    public String getChatMemberID(String userName , String chatRoomID) throws SQLException , RemoteException;
    
   /**
     *
     * @param chatRoomID 
     * @throws SQLException
     * @throws RemoteException
     */
    public Vector<Message> getAllRoomMessages(String chatRoomID) throws SQLException , RemoteException;
    
   /**
     *
     * @param myID 
     * @throws SQLException
     * @throws RemoteException
     */
    public Vector<Group> getAllGroups(int myID) throws SQLException , RemoteException;
    
   /**
     *
     * @param chatID 
     * @throws SQLException
     * @throws RemoteException
     */
    public Vector<String> getAllChatMember(String chatID) throws SQLException , RemoteException;
    
   /**
     *
     * @param chatRoomName 
     * @param clients 
     * @throws SQLException
     * @throws RemoteException
     */
    public String createGroupChat(String chatRoomName, Vector<String> clients) throws SQLException , RemoteException;
    
   /**
     *
     * @param users
     * @param group
     * @throws RemoteException
     */
    public void notifyUsersGroupChat(Vector<String> users , Group group) throws RemoteException;

}

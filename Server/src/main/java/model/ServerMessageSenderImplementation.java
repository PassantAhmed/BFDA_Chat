/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Message;
import server.interfaces.ServerMessegeSender;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * @author ahmedelgawesh
 */
public class ServerMessageSenderImplementation extends UnicastRemoteObject implements ServerMessegeSender {

    ChatFlowControl chatFlowControl ;

    public ServerMessageSenderImplementation() throws RemoteException {
        chatFlowControl = new ChatFlowControl();
    }

    @Override
    public void chatStartRequest(String chatID, Vector<String> users) throws RemoteException {
        chatFlowControl.registerNewChat(chatID , users);
    }

    @Override
    public void sendMsgToChat(String chatID, Message message) throws RemoteException {
        chatFlowControl.sendMsg(chatID , message);
    }



}

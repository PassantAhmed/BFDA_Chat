/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverInterfaces;

import beans.Message;
import beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author ahmedelgawesh
 */
public interface ServerMessegeSender extends Remote
{  
          public void chatStartRequest(String chatID , Vector<String> users) throws RemoteException;
          public void sendMsgToChat(String ChatID , Message message) throws RemoteException;

}

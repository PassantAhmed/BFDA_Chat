/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.interfaces;

import beans.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ahmedelgawesh
 */


public interface FriendsDbOperations  extends Remote
{
               //will take string parameter and retrieve objects of users
               public ArrayList<User> searchForUser(String userInformation) throws RemoteException;

               public boolean sendFriendRequest(int myId,int userId) throws RemoteException;
   
               public void approveFriendRequset() throws RemoteException;
    
               public void refuseFriendRequest() throws RemoteException;

               public void retrieveAllFriends() throws RemoteException;
   
               
}

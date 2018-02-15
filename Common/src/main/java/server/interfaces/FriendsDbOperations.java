/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverInterfaces;

import java.beans.Beans;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmedelgawesh
 */


public interface FriendsDbOperations  extends Remote
{
            //will take string parameter and retrieve objects of users
               public ArrayList<beans.User> searchForUser(String userInformation) throws RemoteException;

               public boolean sendFriendRequest(int myId,int userId) throws RemoteException;
   
               public boolean approveFriendRequset(int myId,int userId) throws RemoteException;
    
               public boolean refuseFriendRequest(int myId,int userId) throws RemoteException;

               public ArrayList<beans.User> retrieveAllFriends(int myId) throws RemoteException;
   
               
}

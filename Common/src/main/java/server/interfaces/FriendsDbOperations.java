/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
package server.interfaces;

import beans.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
=======
package serverInterfaces;

import java.beans.Beans;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 0ed57f02f8e82f4b20744160c91591ba26d86b62

/**
 *
 * @author ahmedelgawesh
 */


public interface FriendsDbOperations  extends Remote
{
<<<<<<< HEAD
               //will take string parameter and retrieve objects of users
               public ArrayList<User> searchForUser(String userInformation) throws RemoteException;

               public boolean sendFriendRequest(int myId,int userId) throws RemoteException;
   
               public void approveFriendRequset() throws RemoteException;
    
               public void refuseFriendRequest() throws RemoteException;

               public void retrieveAllFriends() throws RemoteException;
=======
            //will take string parameter and retrieve objects of users
               public ArrayList<beans.User> searchForUser(String userInformation) throws RemoteException;

               public boolean sendFriendRequest(int myId,int userId) throws RemoteException;
   
               public boolean approveFriendRequset(int myId,int userId) throws RemoteException;
    
               public boolean refuseFriendRequest(int myId,int userId) throws RemoteException;

               public ArrayList<beans.User> retrieveAllFriends(int myId) throws RemoteException;
>>>>>>> 0ed57f02f8e82f4b20744160c91591ba26d86b62
   
               
}


package server.interfaces;

import beans.User;

import java.beans.Beans;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ahmedelgawesh
 */


public interface FriendsDbOperations extends Remote {

    //will take string parameter and retrieve objects of users
    public ArrayList<beans.User> searchForUser(String userInformation) throws RemoteException;

    public boolean sendFriendRequest(int myId, int userId) throws RemoteException;

    public boolean approveFriendRequset(int myId, int userId) throws RemoteException;

    public boolean refuseFriendRequest(int myId, int userId) throws RemoteException;

    public ArrayList<beans.User> retrieveAllFriends(int myId) throws RemoteException;

    public ArrayList<User> getAllFriendRequests(int myId) throws RemoteException;

    public boolean isExist(int myId,int userId)throws RemoteException;
    
    public void testShowMessage() throws RemoteException;
    /***********keep away****************/
    public int getIdfromUserName(String userName) throws RemoteException;
 public int selectFriendsFlag(int myID,int userID) throws RemoteException;
    /***********keep away****************/

}

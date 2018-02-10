/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import beans.User;
import java.util.List;
/**
 *
 * @author ahmedelgawesh
 */

// these are the service which the server provide them
public interface ServerDatabseOperation  extends  Remote
{
    //this function for checking auth of user
    //    public beans.User clientLogin(String userName,String password) throws RemoteException;
    
    //its for clients registration
    public boolean clientRegister(User clientData) throws RemoteException;
    
     //its for clients on or off status ~Flag
    public boolean clientSignHisFlagStatus(boolean clientFlag) throws RemoteException;
    
     //its for clients Mode ~ avalibale or away etc ...
    public boolean clientSignHisModeStatus(String clientMode) throws RemoteException;
    
    //its for clients to add new user
    public User clientAddAnotherClient(String anotherClientToAdd) throws RemoteException;
    
    //its for clients to add new chat Group
    public int clientCreateGroupChat(String groupName) throws RemoteException;
    
   //its for clients to addMemberToGroup
    public void addMemberToGroup(int groupID,String whichUserYouWillAdd) throws RemoteException;
    
        //its for clients for searching
    public  List<User>  clientSearchForUser(String whichUserWillGet) throws RemoteException;
    
    
    
}

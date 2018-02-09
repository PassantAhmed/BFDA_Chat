/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
/**
 *
 * @author ahmedelgawesh
 */
public class ServerDBOperationImplementation extends UnicastRemoteObject implements serverInterfaces.ServerDatabseOperation{
public ServerDBOperationImplementation()throws RemoteException{}
    public boolean clientLogin(String userName, String password) throws RemoteException 
    {
            return true;
    }

    public boolean clientRegister(User clientData) throws RemoteException 
    {
        
            return true;
    }

    public boolean clientSignHisFlagStatus(boolean clientFlag) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean clientSignHisModeStatus(String clientMode) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean clientAddAnotherClient(String anotherClientToAdd) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int clientCreateGroupChat(String groupName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMemberToGroup(int groupID, String whichUserYouWillAdd) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<beans.User> clientSearchForUser(String whichUserWillGet) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

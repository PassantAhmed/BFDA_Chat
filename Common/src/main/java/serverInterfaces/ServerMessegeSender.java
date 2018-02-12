/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author ahmedelgawesh
 */
public interface ServerMessegeSender  extends Remote
{  
          public void sendToClients(int recieverID) throws RemoteException;
          public void chatStartRequest(Vector<String> emails) throws RemoteException;
          
}

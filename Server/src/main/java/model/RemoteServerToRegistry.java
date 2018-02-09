/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ahmedelgawesh
 */
public class RemoteServerToRegistry 
{
        
        public  RemoteServerToRegistry()
        {
                try
                {
                        Registry serverRegistry = LocateRegistry.createRegistry(4291);
                        serverRegistry.rebind("serverRegistry", new ServerDBOperationImplementation());
                        serverRegistry.rebind("serverRegistry2", new ServerMessageSenderImplementation());

                }
                catch (RemoteException ReEx)
                {
                        System.out.println(ReEx);
                }
        }
   
        public static void main(String[] args)
        {
            new RemoteServerToRegistry();
        }
        
}

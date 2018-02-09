/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ahmedelgawesh
 */
public class RemoteServerToRegistry 
{
        private Registry serverRegistry;
        private static RemoteServerToRegistry instance;
        public static RemoteServerToRegistry getInstance()
        {
                if(instance == null)
                        instance = new RemoteServerToRegistry();
                return instance;
        }
        private RemoteServerToRegistry()
        {
                try
                {
                        serverRegistry = LocateRegistry.createRegistry(5220);
                        serverRegistry.rebind("serverRegistry", new ServerDBOperationImplementation());
                }
                catch (RemoteException ReEx)
                {
                        System.out.println(ReEx);
                }

        }

        public void stopServer() throws RemoteException, NotBoundException {

                serverRegistry.unbind("serverRegistry");
        }


   

}

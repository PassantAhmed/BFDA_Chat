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
import java.sql.SQLException;

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
                }
                catch (RemoteException ReEx)
                {
                        System.out.println(ReEx);
                }

        }

        public void startServer() throws RemoteException, SQLException {
                serverRegistry.rebind("serverRegistry", new ServerObject());

        }

        public void stopServer() throws RemoteException, NotBoundException {
                serverRegistry.unbind("serverRegistry");
        }

        public String[] boundedObjects() throws RemoteException {
                return serverRegistry.list();
        }




   

}

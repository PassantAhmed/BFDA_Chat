/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.BindException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 * @author ahmedelgawesh
 */
public class RemoteServerToRegistry {

    private Registry serverRegistry;
    private static RemoteServerToRegistry instance;

    /**
     *
     * @throws RemoteException
     */
    public static RemoteServerToRegistry getInstance() throws RemoteException {
        if (instance == null) {
            instance = new RemoteServerToRegistry();
        }
        return instance;
    }

    /**
     *
     * @throws RemoteException
     */
    private RemoteServerToRegistry() throws RemoteException {
        serverRegistry = LocateRegistry.createRegistry(5220);
    }

    /**
     *
     * @throws RemoteException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void startServer() throws RemoteException, SQLException, ClassNotFoundException {
        serverRegistry.rebind("serverRegistry", new ServerObject());
    }

    public void stopServer() {
        new Thread(() -> {
//            try {
            new ConnectionValidation().sendCloseNotify();
//            }
//            catch (RemoteException e) {
//                e.printStackTrace();
//            }
            try {
                serverRegistry.unbind("serverRegistry");
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     *
     * @throws RemoteException
     */
    public String[] boundedObjects() throws RemoteException {
        return serverRegistry.list();
    }

}

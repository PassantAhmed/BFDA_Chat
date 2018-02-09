package model;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerConnection {

    String ipAddress;
    private static ServerConnection instance;
    public static ServerConnection getInstance(String ipAddress)
    {
        if (instance == null)
            instance = new ServerConnection(ipAddress);
        return instance;
    }
    private ServerConnection (String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public boolean establiseConnection()  {
        try {
            Registry reg = LocateRegistry.getRegistry(ipAddress+":"+"4291");
        } catch (RemoteException e) {

            return false;
        }
        return true;
    }



}

package model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerConnection {

    private static String localIpAddress;
    private static ServerConnection instance;
    public static ServerConnection getInstance(String ipAddress)
    {
        if (instance == null)
            instance = new ServerConnection(ipAddress);
        else
        {
            localIpAddress = ipAddress;
        }
        return instance;
    }

    private ServerConnection (String ipAddress)
    {
        this.localIpAddress = ipAddress;
    }

    public boolean establiseConnection()  {
        try {
            Registry reg = LocateRegistry.getRegistry(localIpAddress , 5220);
            Object obj = reg.lookup("serverRegistry");
            System.out.println("Done");
        } catch (RemoteException e) {
            System.out.println(e);
            return false;
        } catch (NotBoundException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }





}

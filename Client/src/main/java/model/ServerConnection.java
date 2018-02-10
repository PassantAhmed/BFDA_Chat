package model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;

public class ServerConnection {

    private static String localIpAddress;
    private static ServerConnection instance;
    Object obj ;
    public static ServerConnection getInstance()
    {
        if (instance == null)
            instance = new ServerConnection();

        return instance;
    }

    private ServerConnection ()
    {

    }

    public Object getRegisteryObject()
    {
        return obj;
    }
    public void setHost(String host){localIpAddress = host;};
    public boolean establiseConnection()  {
        try {
            Registry reg = LocateRegistry.getRegistry(localIpAddress , 5220);
            obj =  reg.lookup("serverRegistry");
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

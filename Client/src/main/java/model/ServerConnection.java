package model;

import server.interfaces.ServerObj;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.interfaces.UserStatuesChangeInterface;

public class ServerConnection {

    private String localIpAddress;
    private static ServerConnection instance;
    private ServerObj obj ;
    private boolean isRegistered;
    public boolean isRegistered() {
        return isRegistered;
    }
    public static ServerConnection getInstance() {
        if (instance == null)
            instance = new ServerConnection();

        return instance;
    }
    public ServerObj getRegisteryObject()
    {
        return obj;
    }
    
    /**
    * 
    * @param host
    **/
    public void setHost(String host){localIpAddress = host;};
    public boolean establiseConnection()  {
        try {
            Registry reg = LocateRegistry.getRegistry(localIpAddress , 5220);
            obj = (ServerObj) reg.lookup("serverRegistry");
            System.out.println("Done");
        } catch (RemoteException e) {
            System.out.println(e);
            return false;
        } catch (NotBoundException e) {
            System.out.println(e);
            return false;
        }
        isRegistered = true;
        return true;

    }









}

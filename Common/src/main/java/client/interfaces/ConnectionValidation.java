package client.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionValidation extends Remote {

    public void closeRequest() throws RemoteException;
}

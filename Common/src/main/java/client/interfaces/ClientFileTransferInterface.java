package client.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientFileTransferInterface extends Remote {

    public void receiveFile() throws RemoteException;
    public void receiveFileParts(boolean lastPart) throws RemoteException;
    public void approveFileTransfer() throws RemoteException;
}

package server.interfaces;

import java.rmi.RemoteException;

public interface ServerFileTransfer {

    public String sendFile(String senderID , String receiverID) throws RemoteException;
    public void sendFileParts() throws RemoteException;

}

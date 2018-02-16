package model;

import server.interfaces.ServerFileTransfer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerFileTransferImpl extends UnicastRemoteObject implements ServerFileTransfer{

    protected ServerFileTransferImpl() throws RemoteException {
    }

    //Function return start if user online and accepted file transfer
    //return off if user if offline
    //return deny if user refused to Receive
    @Override
    public String sendFile(String senderID , String receiverID) throws RemoteException {
        return null;
    }

    @Override
    public void sendFileParts() throws RemoteException {

    }
}

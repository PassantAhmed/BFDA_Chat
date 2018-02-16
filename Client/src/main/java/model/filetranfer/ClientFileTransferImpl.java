package model.filetranfer;

import client.interfaces.ClientFileTransferInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientFileTransferImpl extends UnicastRemoteObject implements ClientFileTransferInterface {

    protected ClientFileTransferImpl() throws RemoteException {

    }

    @Override
    public void receiveFile() throws RemoteException {

    }

    @Override
    public void receiveFileParts(boolean lastPart) throws RemoteException {

    }

    @Override
    public void approveFileTransfer() throws RemoteException {

    }
}

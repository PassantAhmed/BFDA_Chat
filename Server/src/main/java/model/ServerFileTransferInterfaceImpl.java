package model;

import beans.FileObject;
import client.interfaces.ClientObj;
import javafx.stage.FileChooser;
import server.interfaces.ServerFileTransferInterface;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutionException;

public class ServerFileTransferInterfaceImpl extends UnicastRemoteObject implements ServerFileTransferInterface {

    /**
     *
     * @throws RemoteException
     */
    public ServerFileTransferInterfaceImpl() throws RemoteException {
    }

    /**
     *
     * @param senderID
     * @param receiverID
     * @param fileName
     * @throws RemoteException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public synchronized File requestSendFile(String senderID, String receiverID, String fileName) throws RemoteException, ExecutionException, InterruptedException {

        ClientObj clientObj =  ClientServerRegisterImp.clientObjHashMap.get(receiverID);
        File response = null;
        if(clientObj != null)
        {
            response = clientObj.getClientFileTransfer().receiveFile(senderID , receiverID , fileName);
        }
        return response;
    }

    /**
     *
     * @param senderID
     * @param receiverID
     * @param fileObject
     * @param lastPart
     * @throws RemoteException
     * @throws IOException
     */
    @Override
    public synchronized void sendFileParts(String senderID , String receiverID , FileObject fileObject , boolean lastPart) throws RemoteException , IOException {
        ClientObj clientObj =  ClientServerRegisterImp.clientObjHashMap.get(receiverID);
        clientObj.getClientFileTransfer().receiveFileParts(senderID , receiverID , fileObject , lastPart);
    }


}

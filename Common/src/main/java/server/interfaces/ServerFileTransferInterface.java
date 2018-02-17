package server.interfaces;

import beans.FileObject;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public interface ServerFileTransferInterface extends Remote {

    public File requestSendFile(String senderID , String receiverID , String fileName) throws RemoteException, ExecutionException, InterruptedException;
    public void sendFileParts(String senderID , String receiverID , FileObject fileObject) throws IOException;

}

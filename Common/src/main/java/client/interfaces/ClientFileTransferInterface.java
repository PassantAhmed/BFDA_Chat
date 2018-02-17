package client.interfaces;

import beans.FileObject;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public interface ClientFileTransferInterface extends Remote {

    public File receiveFile(String senderID , String receiverID , String filename) throws RemoteException, ExecutionException, InterruptedException;
    public void receiveFileParts(String senderID , String receiverID , FileObject fileObject ,  boolean lastPart) throws IOException;
}

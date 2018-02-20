package client.interfaces;

import beans.FileObject;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public interface ClientFileTransferInterface extends Remote {

   /**
     *
     * @param senderID
     * @param receiverID
     * @param filename
     * @throws RemoteException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public File receiveFile(String senderID , String receiverID , String filename) throws RemoteException, ExecutionException, InterruptedException;
    
   /**
     *
     * @param senderID
     * @param receiverID
     * @param fileObject
     * @param lastPart 
     * @throws RemoteException
     * @throws IOException
     */
    public void receiveFileParts(String senderID , String receiverID , FileObject fileObject ,  boolean lastPart) throws RemoteException , IOException;
}

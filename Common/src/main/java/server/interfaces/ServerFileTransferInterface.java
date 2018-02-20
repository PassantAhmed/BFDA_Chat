package server.interfaces;

        import beans.FileObject;

        import java.io.File;
        import java.io.IOException;
        import java.rmi.Remote;
        import java.rmi.RemoteException;
        import java.util.concurrent.ExecutionException;

public interface ServerFileTransferInterface extends Remote {

    
   /**
     *
     * @param senderID
     * @param receiverID
     * @param fileName  
     * @throws RemoteException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public File requestSendFile(String senderID , String receiverID , String fileName) throws RemoteException, ExecutionException, InterruptedException;
    
   /**
     *
     * @param senderID
     * @param receiverID
     * @param fileObject
     * @param lastPart
     * @throws RemoteException
     * @throws IOException
     */
    public void sendFileParts(String senderID , String receiverID , FileObject fileObject , boolean lastPart) throws RemoteException ,  IOException;

}

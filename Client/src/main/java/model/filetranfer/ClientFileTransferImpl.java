package model.filetranfer;

import beans.FileObject;
import client.interfaces.ClientFileTransferInterface;
import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.FileHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ClientFileTransferImpl extends UnicastRemoteObject implements ClientFileTransferInterface {
    FileHandler fileHandler;
//    File response = null;
    public ClientFileTransferImpl() throws RemoteException {
        fileHandler = new FileHandler();
    }

    @Override
    public synchronized File receiveFile(String senderID, String receiverID, String fileName) throws RemoteException, ExecutionException, InterruptedException {
        File response = null;

        FutureTask query = new FutureTask(() -> getAcceptation(senderID , fileName));
        Platform.runLater(query);
        System.out.println(query.get());
        if((Boolean)query.get())
        {
            query = new FutureTask(() -> getSaveLocation());
            Platform.runLater(query);
            System.out.println(query.get());
            response = (File)query.get();
        }
        return response;
    }

    @Override
    public synchronized void receiveFileParts(String senderID, String receiverID, FileObject fileObject , boolean lastPart) throws IOException {
        if(!lastPart)
            fileHandler.retrieveFile(fileObject);
        else {
            System.out.println();
            fileHandler.mergeFiles(fileHandler.listOfFilesToMerge(fileObject.getLocationToSave()) , fileObject.getFileActualName());
        }
    }

    private File getSaveLocation() {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(null);

        if (file != null) {
            return file;
        }
        return null;
    }

    private boolean getAcceptation(String senderID , String fileName)
    {
        Optional<ButtonType> result;
        result = new Alert(Alert.AlertType.CONFIRMATION, "User " + senderID +
                " want to Send You " + fileName + " File").showAndWait();
        return result.get() == ButtonType.OK;
    }
}
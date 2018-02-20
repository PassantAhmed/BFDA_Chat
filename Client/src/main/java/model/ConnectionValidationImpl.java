package model;

import client.interfaces.ConnectionValidation;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConnectionValidationImpl extends UnicastRemoteObject implements ConnectionValidation {

    /**
    * 
    * @throws RemoteException 
    **/
    public ConnectionValidationImpl() throws RemoteException {
    }

    /**
    * 
    * @throws RemoteException 
    **/
    @Override
    public void closeRequest() throws RemoteException {
        Platform.runLater(()->{
            new Alert(Alert.AlertType.ERROR , "Server is Off , Application Will Exit ... !").showAndWait();
            System.exit(0);
        });
    }


}

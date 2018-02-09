/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.RemoteServerToRegistry;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 *
 * @author Passant
 */
public class SettingsController implements Initializable{

    private RemoteServerToRegistry remoteServerToRegistry;
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void stopServer(ActionEvent actionEvent) throws RemoteException, NotBoundException {
        remoteServerToRegistry.stopServer();
    }

    public void startServer(ActionEvent actionEvent) {
        remoteServerToRegistry = RemoteServerToRegistry.getInstance();

    }
}

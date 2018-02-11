/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.ConnectionValidation;
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

    @FXML private Button startButton;
    @FXML private Button stopButton;
    private boolean isServerRunning = false;
    private RemoteServerToRegistry remoteServerToRegistry;

    public void initialize(URL location, ResourceBundle resources) {
        remoteServerToRegistry = RemoteServerToRegistry.getInstance();
    }

    public void stopServer(ActionEvent actionEvent)  {
        new Thread(()->{
            try {
                remoteServerToRegistry.stopServer();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }).start();
        isServerRunning = false;
        startButton.setDisable(false);
        stopButton.setDisable(true);

    }

    public void startServer(ActionEvent actionEvent) {
        new Thread(()->{
            try {
                remoteServerToRegistry.startServer();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }).start();
        isServerRunning = true;
        checkActiveUsers();
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    private void checkActiveUsers()
    {
        System.out.print(isServerRunning);
        new Thread(()-> {
            while (isServerRunning)
                new ConnectionValidation().checkActiveUsers();
        }).start();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import client.interfaces.ClientObj;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.ClientServerRegisterImp;

import java.rmi.RemoteException;

/**
 *
 * @author Passant
 */
public class AnnouncementsController  {
    @FXML private TextArea announcementText;


    public void sendAnnounce(ActionEvent actionEvent) {
        String msg = announcementText.getText();
        new Thread(()->{
            for(ClientObj obj :  ClientServerRegisterImp.clientObjHashMap.values())
            {
                    try
                    {
                        obj.getChatHandler().updateAnnouncement(msg);
                    }
                    catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }

                }
        }).start(); }
}

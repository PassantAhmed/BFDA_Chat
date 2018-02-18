/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import beans.User;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import view.controller.MainController;

/**
 *
 * @author ahmedelgawesh
 */
public class FriendRequestFormat   extends ListCell<User>{
    private Node parent ;
    private Circle profilePicCircle;
    private Button acceptButton;
    private Button rejectButton;
    private Label name;
    private Image img ;
    private MainController mainController;
    public FriendRequestFormat(MainController mainController)
    {

        this.mainController = mainController;

        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/ReqFriendsListItems.fxml"));
            profilePicCircle = (Circle)parent.lookup("#profilePicCircle");
            name = (Label)parent.lookup("#name");
            acceptButton = (Button)parent.lookup("#acceptButton");
            rejectButton = (Button)parent.lookup("#rejectButton");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty)
        {
            if(item.getGender())
                img = new Image("/styles/Male.png");
            else
                img = new Image("/styles/Female.png");
            profilePicCircle.setFill(new ImagePattern(img));
           
            name.setText(item.getName());
            setGraphic(parent);
//            acceptButton.setOnAction(value);
//            rejectButton.setOnAction(value);
            
        }
        else
        {
            setGraphic(null);
        }


    }


}


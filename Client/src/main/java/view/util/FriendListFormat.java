package view.util;

import beans.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import view.controller.MainController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class FriendListFormat extends ListCell<User> {


    private Node parent ;
    private Circle profilePicCircle;
    private Circle statusCircle;
    private Label name;
    private Label status;
    private Image img ;
    private Label msgCount;
    private MainController mainController;
    public FriendListFormat(MainController mainController)
    {

        this.mainController = mainController;

        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/ListItems.fxml"));
            profilePicCircle = (Circle)parent.lookup("#profilePicCircle");
            statusCircle = (Circle)parent.lookup("#statusCircle");
            name = (Label)parent.lookup("#name");
            status = (Label)parent.lookup("#status");

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
            if(item.getStatus())
                statusCircle.setFill(Color.GREEN);
            else
                statusCircle.setFill(Color.RED);
            name.setText(item.getName());
            status.setText(item.getMode());
            setGraphic(parent);
            parent.setOnMouseClicked(param->{
                try {
                    mainController.setSingleChatRoom(item.getUsername());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
        else
        {
            setGraphic(null);
        }


    }


}

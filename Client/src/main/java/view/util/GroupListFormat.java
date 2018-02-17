package view.util;

import beans.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import view.controller.MainController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class GroupListFormat extends ListCell<Group> {


    private Node parent ;
    private Circle profilePicCircle;
    private Label name;
    private Image img ;
    private MainController mainController;

    public GroupListFormat(MainController mainController)
    {

        this.mainController = mainController;

        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/GroupListItems.fxml"));
            profilePicCircle = (Circle)parent.lookup("#profilePicCircle");
            name = (Label)parent.lookup("#name");
            img = new Image("/styles/groupChat.png");
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    @Override
    protected void updateItem(Group item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty)
        {

            profilePicCircle.setFill(new ImagePattern(img));
            name.setText(item.getGroupName());
            setGraphic(parent);
            parent.setOnMouseClicked(param->{
                try {
                    mainController.setGroupChatRoom(item);

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

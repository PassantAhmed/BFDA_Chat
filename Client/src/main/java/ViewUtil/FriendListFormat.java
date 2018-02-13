package ViewUtil;

import beans.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class FriendListFormat extends ListCell<User> {

    Node parent ;
    private Circle profilePicCircle;
    private Circle statusCircle;
    private Label name;
    private Label status;
    private Image img ;
    public FriendListFormat()
    {
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/ListItems.fxml"));
            profilePicCircle = (Circle)parent.lookup("#profilePicCircle");
            statusCircle = (Circle)parent.lookup("#statusCircle");
            name = (Label)parent.lookup("#name");
            status = (Label)parent.lookup("#status");
            img = new Image("https://www.filmibeat.com/img/220x90x275/popcorn/profile_photos/scarlett-johansson-20141121172716-5935.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        {
            profilePicCircle.setFill(new ImagePattern(img));
            if(item.getStatus())
                statusCircle.setFill(Color.GREEN);
            else
                statusCircle.setFill(Color.RED);
            name.setText(item.getName());
            status.setText(item.getMode());
            setGraphic(parent);
        }
        else
        {
            setGraphic(null);
        }
    }
}

package viewcontroller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.ChatImpl;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;

public class MainWindowController implements Initializable{
    @FXML private Circle profilePicCircle;
    @FXML private TextArea announceArea;
    @FXML private ScrollPane scroll;
    @FXML private Button italicWordBtn;

    public void initialize(URL location, ResourceBundle resources) {

        profilePicCircle.setFill(new ImagePattern(new Image("https://thumb7.shutterstock.com/" +
                "display_pic_with_logo/1833737/493883845/" +
                "stock-photo-woman-profile-face-girl-profile-493883845.jpg")));
        announceArea.setEditable(false);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        italicWordBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(("..\\styles\\bestfriendsdailyapp.png")))));

    }

    public void closeWindow(ActionEvent actionEvent) {

    }

    public void updateAnnounce(String msg)
    {
        Platform.runLater(()->{announceArea.setText(msg);});
    }
}

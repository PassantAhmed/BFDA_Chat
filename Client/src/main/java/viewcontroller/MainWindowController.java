package viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    @FXML private Circle profilePicCircle;
    public void initialize(URL location, ResourceBundle resources) {
        profilePicCircle.setFill(new ImagePattern(new Image("https://thumb7.shutterstock.com/" +
                "display_pic_with_logo/1833737/493883845/stock-photo-woman-profile-face-girl-profile-493883845.jpg")));
    }
}

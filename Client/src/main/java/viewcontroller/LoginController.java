package viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    
    @FXML
    private TextField userNameFieldForLogIn;
    
    @FXML
    private TextField passwordFieldForLogIn;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Hyperlink signupButton;
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

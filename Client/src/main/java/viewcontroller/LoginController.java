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
import model.ServerConnection;
import myutilities.ValidationChecks;
//import model.UserLogin;

public class LoginController implements Initializable {

    @FXML private TextField userNameFieldForLogIn;
    @FXML private TextField passwordFieldForLogIn;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupButton;
    @FXML private Label notValidLbl;

    private ValidationChecks checker = new ValidationChecks();

    @FXML
    private void login(ActionEvent event) {

        String userName = userNameFieldForLogIn.getText();
        String password = passwordFieldForLogIn.getText();

        if (validateLogin(userName, password) == true) {
//            ServerConnection.getInstance().getRegisteryObject().getServerDatabseOperation().

                //data is valid, send username and password to server
        } else {
            // not valid msg
            notValidLbl.setText("invalid username or password");
        }

    }

    private boolean validateLogin(String userName, String password) {
        boolean valid = false;
        if (checker.isUserName(userName) == true && checker.isValidPassword(password) == true) {
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
}

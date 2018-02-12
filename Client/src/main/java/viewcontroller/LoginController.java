package viewcontroller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import beans.User;
import clientInterfaces.ClientObj;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ClientObject;
import model.ServerConnection;
import myutilities.ValidationChecks;
import serverInterfaces.ServerObj;
//import model.UserLogin;

public class LoginController implements Initializable {

    @FXML private TextField userNameFieldForLogIn;
    @FXML private TextField passwordFieldForLogIn;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupButton;
    @FXML private Label notValidLbl;
    ServerConnection serverConnection = ServerConnection.getInstance();
    ServerObj serverObj = serverConnection.getRegisteryObject();

    private ValidationChecks checker = new ValidationChecks();
    public LoginController()
    {
        getServerObject();
    }

    @FXML
    private void login(ActionEvent event) throws RemoteException {

        String userName = userNameFieldForLogIn.getText();
        String password = passwordFieldForLogIn.getText();

        if (validateLogin(userName, password)) {
            verificateUser(userName , password);
        } else {
            // not valid msg
            new Alert(Alert.AlertType.ERROR, "Username or Password Not in Correct Format").show();
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

    private void getServerObject()
    {
        serverConnection = ServerConnection.getInstance();
        serverObj = serverConnection.getRegisteryObject();
    }

    public void verificateUser(String userName , String password) throws RemoteException {
        User loginUserResult = serverObj.getClientServerRegister().userLogin(userName , password);
        if(loginUserResult == null)
        {
            new Alert(Alert.AlertType.ERROR, "Username or Password is Not Valid").show();
        }
        else
        {
            //if Registeration Success , set the Static variable with current User Object
            ClientObject.setUserData(loginUserResult);
            startMainApp();
        }
    }

    private void startMainApp()
    {
        Platform.runLater(()->{

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/MainChatWindow.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("BFDA Chat | Main");
                stage.setScene(scene);
                Stage currentStage = (Stage)loginButton.getScene().getWindow();
                currentStage.close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

}

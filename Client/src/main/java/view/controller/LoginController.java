package view.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import beans.User;
import client.interfaces.ClientObj;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import view.util.ValidationChecks;
import server.interfaces.ServerObj;
//import model.UserLogin;

public class LoginController implements Initializable {

    @FXML private JFXTextField userNameFieldForLogIn;
    @FXML private JFXPasswordField passwordFieldForLogIn;
    @FXML private JFXButton loginButton;
    @FXML private Hyperlink signupButton;
    @FXML private Label notValidLbl;
    private boolean loginResultFlag = false;
    ServerConnection serverConnection = ServerConnection.getInstance();
    ServerObj serverObj = serverConnection.getRegisteryObject();

    private ValidationChecks checker = new ValidationChecks();
    public LoginController() throws RemoteException {

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
        if (checker.isUserName(userName) && checker.isValidPassword(password)) {
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
        User loginUserResult;
        getServerObject();


        if(serverObj != null)
            loginUserResult = serverObj.getClientServerRegister().userLogin(userName , password);
        else
        {
            Platform.runLater(()->{new Alert(Alert.AlertType.ERROR, "Server Is Not Reachable").show();});
            return;
        }
        if(loginUserResult == null)
        {
            new Alert(Alert.AlertType.ERROR, "Username or Password is Not Valid").show();
        }
        else
        {
            //if Registeration Success , set the Static variable with current User Object
            ClientObject.setUserData(loginUserResult);
            loginResultFlag = setNewObjectToServer();

            System.out.println("User Name is : "+loginUserResult.getUsername());

            if(loginResultFlag)
            {
                startMainApp();
            }
            else
            {
                Platform.runLater(()->{new Alert(Alert.AlertType.ERROR, "Login Failed").show();});
            }

        }
    }

    private void startMainApp()
    {
        Platform.runLater(()->{

            Parent root = null;
            try {

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainChat.fxml"));
                root = fxmlLoader.load();
                ControllerManager.getInstance().setMainController(fxmlLoader.getController());
                Scene scene = new Scene(root);
                stage.setTitle("BFDA Chat | Main");
                stage.setScene(scene);
                Stage currentStage = (Stage)loginButton.getScene().getWindow();
                currentStage.close();
                stage.setResizable(false);
                stage.setOnCloseRequest(param->{System.exit(0);});
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public void signUp(ActionEvent actionEvent) {
        Platform.runLater(()->{

            Parent root = null;
            try {

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Register1Scene.fxml"));
                root = fxmlLoader.load();
                ControllerManager.getInstance().setRegisterController1(fxmlLoader.getController());
                Scene scene = new Scene(root);
                stage.setTitle("BFDA Chat | Register");
                stage.setScene(scene);
                Stage currentStage = (Stage)loginButton.getScene().getWindow();
                currentStage.close();
                stage.setResizable(false);
                stage.setOnCloseRequest(param->{System.exit(0);});
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }




    public void connectionEstablishingMode(boolean status)
    {
        if(status)
            Platform.runLater(()-> {
                loginButton.setText("Logging ... ");
                loginButton.setDisable(true);
                userNameFieldForLogIn.setDisable(true);
                passwordFieldForLogIn.setDisable(true);

            });
        else
            Platform.runLater(()-> {
                loginButton.setDisable(false);
                loginButton.setText("Login");
                userNameFieldForLogIn.setDisable(false);
                passwordFieldForLogIn.setDisable(false);
            });
    }

    private boolean setNewObjectToServer()  {

        try {
            ServerObj serverObj = ServerConnection.getInstance().getRegisteryObject();
            serverObj.getClientServerRegister().registerUser(ClientObject.getUserDataInternal().getUsername() , new ClientObject());
        } catch (RemoteException e) {
            return false;
        }
        return true;
    }
}

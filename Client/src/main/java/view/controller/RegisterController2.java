package view.controller;

import beans.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ServerConnection;
import server.interfaces.ServerObj;
import view.util.ValidationChecks;

public class RegisterController2 implements Initializable {

    private ValidationChecks checker = new ValidationChecks();

    @FXML
    private JFXPasswordField passwordid;
    @FXML
    private JFXPasswordField repasswordid;
    @FXML
    private JFXTextField usernameid;
    @FXML
    private ImageView goToPrevPageBtn;

    @FXML
    private Hyperlink hyperloginid;
    @FXML
    private JFXButton submitid;

    ServerConnection serverConnection = ServerConnection.getInstance();
    ServerObj serverObj = serverConnection.getRegisteryObject();

    User user;
    

    public RegisterController2() {
        getServerObject();
    }

    @FXML
    private void registerAction(ActionEvent event) throws RemoteException, IOException {
        boolean genderBoolean;
        user.setUsername(usernameid.getText());
        user.setPassword(passwordid.getText());
        String repassword = repasswordid.getText();

        if (validateUser(user)) {
            //check if passwords matches
            if (user.getPassword().equals(repassword)) {
                //data is valid send user object to server
                boolean success = serverObj.getClientServerRegister().newUserRegisteration(user);
                if (success == true) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
                    Stage stage = new Stage();
                    Parent root = fxmlLoader.load();
                    ControllerManager.getInstance().setLoginController(fxmlLoader.getController());
                    Scene scene = new Scene(root);
                    stage.setTitle("BFDA Chat | Login");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Username already exists...").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please be sure that you worte the confirmation password correct.").show();
            }
        } else{
            new Alert(Alert.AlertType.ERROR, "Check that you follow the following instructions:"
                        + "\n1. Username only contain (a-z), (A-Z), (1-9), and spaces."
                        + "\n2. Password is more than or equal 8 digits." ).show();
        }
    }

    private void getServerObject() {
        serverConnection = ServerConnection.getInstance();
        serverObj = serverConnection.getRegisteryObject();
    }

    private boolean validateUser(User user) {
        boolean isValid = false;

        if (checker.isUserName(user.getUsername()) && checker.isValidPassword(user.getPassword())) {
            isValid = true;
        }

        return isValid;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loginBtn(ActionEvent mouseEvent) {
        Platform.runLater(() -> {

            Parent root = null;
            try {

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
                root = fxmlLoader.load();
                ControllerManager.getInstance().setLoginController(fxmlLoader.getController());
                Scene scene = new Scene(root);
                stage.setTitle("BFDA Chat | Register2");
                stage.setScene(scene);
                Stage currentStage = (Stage) usernameid.getScene().getWindow();
                currentStage.close();
                stage.setResizable(false);
                stage.setOnCloseRequest(param -> {
                    System.exit(0);
                });
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void changeToPrevScene(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Register1Scene.fxml"));
                    root = fxmlLoader.load();
                    ControllerManager.getInstance().setRegisterController1(fxmlLoader.getController());
                    Scene scene = new Scene(root);
                    stage.setTitle("BFDA Chat | Register2");
                    stage.setScene(scene);
                    Stage currentStage = (Stage) usernameid.getScene().getWindow();
                    currentStage.close();
                    stage.setResizable(false);
                    stage.setOnCloseRequest(param -> {
                        System.exit(0);
                    });
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void setUser(User user){
        this.user = new User(user);
        //System.out.println(user.getEmail());
    }

}

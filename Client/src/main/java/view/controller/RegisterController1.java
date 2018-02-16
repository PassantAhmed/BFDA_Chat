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
import java.time.LocalDate;
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

public class RegisterController1 implements Initializable {

    private ValidationChecks checker = new ValidationChecks();

    @FXML
    private JFXComboBox<String> countryid;
    @FXML
    private JFXDatePicker dobid;
    @FXML
    private JFXTextField emailid;
    @FXML
    private JFXTextField fullnameid;
    //@FXML private ToggleGroup genderGroup;
    @FXML
    private JFXToggleButton userGender;
    @FXML
    private ImageView goToNextPageBtn;

    @FXML
    private Label warningMsg;

    @FXML
    private Hyperlink hyperloginid;

    ServerConnection serverConnection = ServerConnection.getInstance();
    ServerObj serverObj = serverConnection.getRegisteryObject();

    User user;

    public RegisterController1() {
        getServerObject();
    }

    private void getServerObject() {
        serverConnection = ServerConnection.getInstance();
        serverObj = serverConnection.getRegisteryObject();
    }

    public void initialize(URL url, ResourceBundle rb) {
        user = new User();
        countryid.getItems().addAll("Egypt", "USA", "Canada");
        countryid.getSelectionModel().select(0);
        dobid.setValue(LocalDate.now());
        
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
                stage.setTitle("BFDA Chat | Register1");
                stage.setScene(scene);
                Stage currentStage = (Stage) emailid.getScene().getWindow();
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

    public void changeToNextScene(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (isFirstScreenValid()) {
                    warningMsg.setText("");
                    user.setEmail(emailid.getText());
                    user.setName(fullnameid.getText());
                    user.setCountry(countryid.getValue());
                    user.setGender(userGender.isSelected());
                    user.setBirthdate(dobid.getValue());

                    Parent root = null;
                    try {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Register2Scene.fxml"));
                        root = fxmlLoader.load();
                        ControllerManager.getInstance().setRegisterController2(fxmlLoader.getController());
                        ControllerManager.getInstance().getRegisterController2().setUser(user);
                        Scene scene = new Scene(root);
                        stage.setTitle("BFDA Chat | Register2");
                        stage.setScene(scene);
                        Stage currentStage = (Stage) emailid.getScene().getWindow();
                        currentStage.close();
                        stage.setResizable(false);
                        stage.setOnCloseRequest(param -> {
                            System.exit(0);
                        });
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    warningMsg.setText("Please be sure that you've entered a valid data");
                    new Alert(Alert.AlertType.ERROR, "Check that you follow the following instructions:\n1. Full name only contain (a-z), (A-Z), and space."
                        + "\n2. Email format is true ex: ahmed@gmail.com"
                        + "\n3. Your age is more than the legal age."
                        + "\n4. And finally you have to choose a country." ).show();
                }
            }
        });
    }

    private boolean isFirstScreenValid() {
        boolean isValid = false;

        if (checker.isName(fullnameid.getText())
                && checker.isEmail(emailid.getText())
                && checker.isLegalAged(dobid.getValue())
                && !checker.isEmptyString(countryid.getValue())) {

            isValid = true;
        }

        return isValid;
    }

    public void changeBtnText(ActionEvent actionEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(userGender.isSelected()){
                    userGender.setText("Male");
                } else if (userGender.isSelected() == false){
                    userGender.setText("Female");
                }
            }
        });
    }

}

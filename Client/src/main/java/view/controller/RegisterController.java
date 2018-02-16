package view.controller;

import beans.User;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ServerConnection;
import server.interfaces.ServerObj;
import view.util.ValidationChecks;

public class RegisterController implements Initializable {
     
    private ValidationChecks checker = new ValidationChecks();
    @FXML private ComboBox<String> countryid;
    @FXML private DatePicker dobid;
    @FXML private TextField emailid;
    @FXML private TextField fullnameid;
    @FXML private ToggleGroup genderGroup;
    @FXML private TextField passwordid;
    @FXML private TextField repasswordid;
    @FXML private TextField usernameid;


    @FXML private Hyperlink hyperloginid;
    @FXML private Button submitid;

    ServerConnection serverConnection = ServerConnection.getInstance();
    ServerObj serverObj = serverConnection.getRegisteryObject();
    
    public RegisterController(){
        getServerObject();
    }
    @FXML
    private void registerAction(ActionEvent event) throws RemoteException, IOException {
        User user = new User();
        boolean genderBoolean;
        user.setUsername(usernameid.getText());
        user.setPassword(passwordid.getText());
        String repassword = repasswordid.getText();
        user.setName(fullnameid.getText());
        user.setEmail(emailid.getText());
        user.setBirthdate(dobid.getValue());
        user.setCountry(countryid.getValue());
        genderBoolean = genderGroup.getSelectedToggle().getUserData().toString() == "male";
        user.setGender(genderBoolean);
        
        if(validateUser(user)){
            //check if passwords matches
            if(user.getPassword().equals(repassword)){
                //data is valid send user object to server
                boolean success = serverObj.getClientServerRegister().newUserRegisteration(user);
                if(success== true){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
                    Stage stage = new Stage();
                    Parent root = fxmlLoader.load();
                    ControllerManager.getInstance().setLoginController(fxmlLoader.getController());
                    Scene scene = new Scene(root);
                    stage.setTitle("BFDA Chat | Login");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Username or Password Already Exists").show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Passwords do Not match").show();
            }
        }
    }
        private void getServerObject()
    {
        serverConnection = ServerConnection.getInstance();
        serverObj = serverConnection.getRegisteryObject();
    }
    private boolean validateUser(User user){
        boolean isValid =false;
        
        if(checker.isName(user.getName())
                && checker.isUserName(user.getUsername())
                && checker.isEmail(user.getEmail())
                && checker.isValidPassword(user.getPassword())){
        
            isValid =true;
        }
            
        return isValid;
    }
    public void initialize(URL url, ResourceBundle rb) {

        countryid.getItems().addAll("Egypt" , "USA" , "Canada");
        // TODO
    }

    public void loginBtn(ActionEvent mouseEvent) {
        Platform.runLater(()->{

            Parent root = null;
            try {

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
                root = fxmlLoader.load();
                ControllerManager.getInstance().setLoginController(fxmlLoader.getController());
                Scene scene = new Scene(root);
                stage.setTitle("BFDA Chat | Register");
                stage.setScene(scene);
                Stage currentStage = (Stage)usernameid.getScene().getWindow();
                currentStage.close();
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

package view.controller;

import beans.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClientObject;
import model.ServerConnection;
import view.util.ValidationChecks;
import server.interfaces.ServerObj;
import view.util.FriendListFormat;

public class NewGroupController implements Initializable {

    @FXML
    private JFXTextField groupName;
    @FXML
    private JFXTextField userMember;

    @FXML
    private JFXButton addMembersBtn;
    @FXML
    private JFXButton createGroupBtn;
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private ListView<User> groupMembersList;

    private ValidationChecks validation;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //groupMembersList.setCellFactory();
    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) groupName.getScene().getWindow();
        stage.close();
    }

    public void createGroup(ActionEvent actionEvent) {
        if (!validation.isEmptyString(groupName.getText())) {

            //TODO
            
            // After doing the creation
            new Alert(Alert.AlertType.INFORMATION,
                    "A new group has been created.").show();
            
            Stage currentStage = (Stage) groupName.getScene().getWindow();
            currentStage.close();

        } else {
            new Alert(Alert.AlertType.ERROR,
                    "You must enter name for your new group...").show();
        }
    }

    public void addMember(ActionEvent actionEvent) {
        if (validation.isUserName(userMember.getText())) {
            addToList(userMember.getText());
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "You must enter a valid username!").show();
        }
    }
    
    public void addToList(String username){
        boolean isFriend = false;
        User friend = new User();
        for(int counter=0; counter<ControllerManager.getInstance().getMainController().getFriendList().size();counter++){
            if(ControllerManager.getInstance().getMainController().getFriendList().get(counter).getUsername().equals(username)){
                isFriend = true;
                friend = new User(ControllerManager.getInstance().getMainController().getFriendList().get(counter));
            }           
        }
        if(isFriend){
            groupMembersList.getItems().add(friend);
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "username you've written is not a friend with you, please write a valid one.").show();
        }
    }

}

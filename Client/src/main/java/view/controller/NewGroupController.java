package view.controller;

import beans.Group;
import beans.User;
import client.interfaces.ClientObj;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

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

    @FXML private JFXTextField groupName;
    @FXML private JFXTextField userMember;
    @FXML private JFXButton addMembersBtn;
    @FXML private JFXButton createGroupBtn;
    @FXML private JFXButton cancelBtn;
    @FXML private ListView<String> groupMembersList;

    private ValidationChecks validation;
    private ServerConnection serverConnection;
    public void initialize(URL url, ResourceBundle rb) {

        validation = new ValidationChecks();
        serverConnection = ServerConnection.getInstance();
    }

    public void cancelCreation(ActionEvent actionEvent) {
        Stage stage = (Stage) groupName.getScene().getWindow();
        stage.close();
    }

    public void createGroup(ActionEvent actionEvent) throws RemoteException {
        if (!validation.isEmptyString(groupName.getText())) {

            //TODO
            Vector<String> users = new Vector<>();
            for(String userName :  groupMembersList.getItems())
            {
                users.add(userName);
            }
            users.add(ClientObject.getUserDataInternal().getUsername());

            try {
                String groupID = serverConnection.getRegisteryObject().getServerMessegeSender().createGroupChat(groupName.getText() ,users);
                if(groupID == null)
                {
                    new Alert(Alert.AlertType.ERROR , "Faild To Create Group").show();
                }
                else
                {
                    Group group = new Group();
                    group.setRoomID(groupID);
                    group.setGroupName(groupName.getText());
                    ControllerManager.getInstance().getMainController().getChatGroupsList().getItems().add(group);
                    users.remove(ClientObject.getUserDataInternal().getUsername());
                    serverConnection.getRegisteryObject().getServerMessegeSender().notifyUsersGroupChat(users , group);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR , "Faild To Create Group").show();
            }
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
    
    private void addToList(String username){

        boolean isFriend = false;
        User friend = new User();
        for(int counter=0; counter<ControllerManager.getInstance().getMainController().getFriendList().size();counter++){
            if(ControllerManager.getInstance().getMainController().getFriendList().get(counter).getUsername().toLowerCase().equals(username.toLowerCase())){
                isFriend = true;
                friend = ControllerManager.getInstance().getMainController().getFriendList().get(counter);
            }           
        }
        if(isFriend){
            groupMembersList.getItems().add(friend.getUsername());
        }
        else if(groupMembersList.getItems().contains(friend))
        {
            new Alert(Alert.AlertType.ERROR,
                    "username Already Added").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR,
                    "username you've written is not a friend with you, please write a valid one.").show();
        }
    }

}

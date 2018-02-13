package viewcontroller;

import ViewUtil.FriendListFormat;
import beans.Message;
import beans.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.ChatImpl;
import model.ClientChatFlowControl;
import model.ClientObject;
import model.ServerConnection;
import serverInterfaces.ServerObj;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class MainController implements Initializable {

    @FXML
    private ListView<User> friendsListView;
    @FXML
    private TextArea announceArea1;
    @FXML
    private Button sendBtn;


    //Temp
    @FXML
    private Label name;
    private String chatID = "mdskrefc";
    //--
    private ClientChatFlowControl clientChatFlowControl;

    public MainController() throws RemoteException {
        clientChatFlowControl = new ClientChatFlowControl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        friendsListView.setCellFactory(param -> new FriendListFormat());
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("Mohamed" + i);
            user.setStatus(false);
            user.setMode("Hello From the Other Side");
            userList.add(user);
        }
        friendsListView.getItems().addAll(userList);
        announceArea1.setEditable(false);
        ChatImpl.setMainController(this);
        name.setText(ClientObject.getUserDataInternal().getUsername());

    }


    public void updateAnnounce(String accouncementString) {
        announceArea1.setText(accouncementString);
    }


    public void sendMessage(ActionEvent actionEvent) throws RemoteException {
        Message message = new Message();
        message.setMessageContent("Hello Dude");
        clientChatFlowControl.sendMsg(chatID, message);
    }

    public void sendBtn(ActionEvent actionEvent) throws RemoteException {
        Vector<String> users = new Vector<>();
        users.add("AhmedAhmed");
        users.add("gamal");
        users.add(ClientObject.getUserDataInternal().getUsername());
        String localchatID = clientChatFlowControl.sendNewChatRequest(chatID, users);
        chatID = localchatID.equals(chatID) ? chatID : localchatID;
    }


}

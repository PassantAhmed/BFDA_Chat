package view.controller;

import controller.ClientChatFlowControl;
import javafx.collections.ObservableList;
import view.util.FriendListFormat;
import beans.Message;
import beans.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.ChatImpl;
import model.ClientObject;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class MainController implements Initializable {

    @FXML private ListView<User> friendsListView;
    @FXML private TextArea announceArea1;
    @FXML private Button sendBtn;
    @FXML private TextField chatField;

    //--Formating Components
    @FXML private Button bold;
    @FXML private Button italic;
    @FXML private ColorPicker fontColorPicker;
    @FXML private ComboBox<String> fontList;
    @FXML private ComboBox<Integer> sizeList;
    //Temp
    @FXML
    private Label name;
    private String chatID = "mdskrefc";
    //--
    private ClientChatFlowControl clientChatFlowControl;

    //--TextFormatFlags
    private boolean isBold = false;
    private boolean isItalic = false;
    private Color fontColor = Color.BLACK;
    private FriendListFormat friendListFormat;
    public MainController() throws RemoteException {
        clientChatFlowControl = new ClientChatFlowControl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        friendListFormat = new FriendListFormat(this);
        friendsListView.setCellFactory(param -> friendListFormat);

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
        formatBarValues();
        formatBarActions();



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

    public void updateTextStyle() {
        FontWeight fontWeight = isBold ? FontWeight.BOLD : FontWeight.NORMAL;
        FontPosture fontPosture = isItalic ? FontPosture.ITALIC : FontPosture.REGULAR;
        chatField.setFont(Font.font(fontList.getValue() ,fontWeight , fontPosture , sizeList.getValue()));
        chatField.setStyle("-fx-text-fill: "+fontColorPicker.getValue().toString().replace("0x" , "#"));
    }

    private void formatBarActions() {
        bold.setOnAction(param->{isBold = !isBold; updateTextStyle();});
        italic.setOnAction(param->{isItalic = !isItalic; updateTextStyle();});
        fontColorPicker.setOnAction(param->{fontColor = fontColorPicker.getValue(); updateTextStyle();});
        fontList.setOnAction(param->{updateTextStyle();});
        sizeList.setOnAction(param->{updateTextStyle();});
    }

    private void formatBarValues() {
        fontList.getItems().addAll(Font.getFontNames());
        fontList.getSelectionModel().select("Arial");
        sizeList.getItems().addAll(11,12,13,14,15,16,17);
        sizeList.getSelectionModel().select(4);
        fontColorPicker.setValue(Color.BLACK);
    }

    public ObservableList getFriendList()
    {
        return friendsListView.getItems();
    }


}

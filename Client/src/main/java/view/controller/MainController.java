package view.controller;

import javafx.collections.ObservableList;
import model.ServerConnection;
import server.interfaces.ServerMessegeSender;
import view.util.ChatBoxFormat;
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
import model.ClientObject;


import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private ListView<User> friendsListView;
    @FXML private ListView<Message> chatBoxListVIew;
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
    private String currentChatID ;
    private String currentChatMemberID ;
    //--


    //--TextFormatFlags
    private boolean isBold = false;
    private boolean isItalic = false;
    private Color fontColor = Color.BLACK;
    private FriendListFormat friendListFormat;
    public MainController() throws RemoteException {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        friendsListView.setCellFactory(param -> new FriendListFormat(this));
        List<User> userList = new ArrayList<>();

            User user = new User();
            user.setName("Mohamed Fawzy");
            user.setUsername("Mohamedfawzy1993");
            user.setStatus(false);
            user.setMode("Hello From the Other Side");
            userList.add(user);

            user = new User();
            user.setName("Ahmed");
            user.setUsername("Ahmedahmed");
            user.setStatus(false);
            user.setMode("Hello From the Other Side");


            userList.add(user);

        chatBoxListVIew.setStyle("-fx-padding: 10 0 0 0;");
        chatBoxListVIew.setCellFactory(param ->  new ChatBoxFormat());
//        chatBoxListVIew.getItems().addAll(messages);

        friendsListView.getItems().addAll(userList);
        announceArea1.setEditable(false);
        name.setText(ClientObject.getUserDataInternal().getUsername());
        formatBarValues();
        formatBarActions();
    }

    public void updateAnnounce(String accouncementString) {
        announceArea1.setText(accouncementString);
    }

    public void sendBtn(ActionEvent actionEvent) throws RemoteException, SQLException {
        ServerMessegeSender serverMessegeSender = ServerConnection.getInstance().getRegisteryObject().getServerMessegeSender();
        Message message = new Message();
        if(!chatField.getText().isEmpty() || chatField.getText() != null)
        {
            message.setMessageContent(chatField.getText());
            message.setMessageFontFamily(fontList.getValue());
            message.setMessageFontSize(sizeList.getValue().toString());
            message.setMessageFontColor(fontColor.toString().replace("0x" , "#"));
            message.setFromUser(ClientObject.getUserDataInternal().getUsername());
            message.setBold(isBold);
            message.setItalic(isItalic);
            message.setMessageDate(LocalDateTime.now());
        }
            serverMessegeSender.sendMsg(currentChatMemberID , message );

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

    public void setCurrentChatID(String userName) throws RemoteException, SQLException {
        ServerMessegeSender serverMessegeSender = ServerConnection.getInstance().getRegisteryObject().getServerMessegeSender();
        currentChatID = serverMessegeSender.getChatRoomOfClient(ClientObject.getUserDataInternal().getUsername() , userName);
        currentChatMemberID = serverMessegeSender.getChatMemberID(ClientObject.getUserDataInternal().getUsername() ,currentChatID);
    }

    public String getCurrentChatID(){ return currentChatID; }

    public List<Message> getCurrentChatObserver()
    {
        return chatBoxListVIew.getItems();
    }




}

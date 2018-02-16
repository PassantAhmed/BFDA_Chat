package view.controller;

import beans.Group;
import client.interfaces.ClientObj;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ServerConnection;
import server.interfaces.ClientServerRegister;
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
import view.util.GroupListFormat;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class MainController implements Initializable {

    @FXML private ListView<User> friendsListView;
    @FXML private ListView<Message> chatBoxListVIew;
    @FXML private ListView<Group> chatGroupsList;
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

    @FXML Pane chatHeader;
    @FXML Pane topSideArea;
    @FXML Pane ChatArea;
    @FXML Pane sideArea;

    //--TextFormatFlags
    private boolean isBold = false;
    private boolean isItalic = false;
    private Color fontColor = Color.BLACK;
    private FriendListFormat friendListFormat;
    private ServerConnection serverConnection;
    private ServerMessegeSender serverMessegeSender;
    private HashMap<String , Vector<Message>> messagesMap =  new HashMap<>();
    private Vector<String> chatMembers = new Vector<>();

    public MainController() throws RemoteException {

        serverConnection = ServerConnection.getInstance();
        serverMessegeSender = serverConnection.getRegisteryObject().getServerMessegeSender();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        friendsListView.setCellFactory(param -> new FriendListFormat(this));
        chatBoxListVIew.setStyle("-fx-padding: 10 0 0 0;");
        chatBoxListVIew.setCellFactory(param ->  new ChatBoxFormat());
        chatGroupsList.setCellFactory(param -> new GroupListFormat(this));
        try {
            updateFriendList();
            updateGroupList();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        announceArea1.setEditable(false);
        name.setText(ClientObject.getUserDataInternal().getUsername());
        formatBarValues();
        formatBarActions();
        showPanes(false);
    }

    public void updateAnnounce(String accouncementString) {
        announceArea1.setText(accouncementString);
    }

    public void sendBtn(ActionEvent actionEvent) throws RemoteException, SQLException {
        ServerMessegeSender serverMessegeSender = ServerConnection.getInstance().getRegisteryObject().getServerMessegeSender();
        Message message = new Message();
        if(!chatField.getText().isEmpty() && chatField.getText() != null) {
            message.setMessageContent(chatField.getText());
            message.setMessageFontFamily(fontList.getValue());
            message.setMessageFontSize(sizeList.getValue().toString());
            message.setMessageFontColor(fontColor.toString().replace("0x", "#"));
            message.setFromUser(ClientObject.getUserDataInternal().getUsername());
            message.setBold(isBold);
            message.setItalic(isItalic);
            message.setMessageDate(LocalDateTime.now());

            new Thread(() -> {

                try {
                    serverMessegeSender.sendMsg(currentChatMemberID,currentChatID ,chatMembers, message);
                } catch (SQLException | RemoteException e) {
                    Platform.runLater(()->{
                        new Alert(Alert.AlertType.ERROR,
                                "Cannot Send Msg"+e.toString()).show();
                    });
                    e.printStackTrace();
                }
            }).start();
        }
        chatField.clear();
    }

    private void updateTextStyle() {
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

    public void setSingleChatRoom(String userName) throws RemoteException, SQLException {

        serverMessegeSender = ServerConnection.getInstance().getRegisteryObject().getServerMessegeSender();
        currentChatID = serverMessegeSender.getChatRoomOfClient(ClientObject.getUserDataInternal().getUsername() , userName);
        currentChatMemberID = serverMessegeSender.getChatMemberID(ClientObject.getUserDataInternal().getUsername() ,currentChatID);
        chatMembers = serverMessegeSender.getAllChatMember(currentChatID);
        setChat(currentChatID);
    }

    public String getCurrentChatID(){ return currentChatID; }

    public List<Message> getCurrentChatObserver()
    {
        return chatBoxListVIew.getItems();
    }

    private void updateFriendList() throws RemoteException {
        friendsListView.getItems().setAll(serverConnection.getRegisteryObject().getFriendsDbOperations()
                .retrieveAllFriends(ClientObject.getUserDataInternal().getId()));
    }

    private void updateGroupList() throws RemoteException, SQLException {
        chatGroupsList.getItems().setAll(serverMessegeSender.getAllGroups(ClientObject.getUserDataInternal().getId()));
    }

    public HashMap<String , Vector<Message>> getMsgMap()
    {
        return messagesMap;
    }

    public void setGroupChatRoom(String groupRoomID) throws SQLException, RemoteException {

        currentChatMemberID = serverMessegeSender.getChatMemberID(ClientObject.getUserDataInternal().getUsername() ,groupRoomID);
        chatMembers = serverMessegeSender.getAllChatMember(groupRoomID);
        setChat(groupRoomID);
    }

    private void setChat(String groupID) {

        currentChatID = groupID;
        if(!messagesMap.containsKey(groupID)){
            System.out.println("Not Contain");
            new Thread(()->{
                try {
                    messagesMap.put(groupID , serverMessegeSender.getAllRoomMessages(groupID));
                    System.out.println("populated");
                    Platform.runLater(()->{chatBoxListVIew.getItems().setAll(messagesMap.get(groupID));});

                } catch (SQLException e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        else
        {
            System.out.println("Vieweing");
            Platform.runLater(()->{chatBoxListVIew.getItems().setAll(messagesMap.get(groupID));});
        }
        showPanes(true);
    }

    private void showPanes(Boolean status) {
       chatHeader.setVisible(status);
       ChatArea.setVisible(status);
    }

    public void saveChat(ActionEvent actionEvent) {
        //System.out.println(XmlMessage.writeXmlFile(ClientObject.getUserDataInternal().getUsername() , "" , messagesMap.get(currentChatID)));
    }

    public void logoutBtn(MouseEvent mouseEvent) throws IOException {
        messagesMap.clear();
        serverConnection.getRegisteryObject().getClientServerRegister()
                .unRegisterUser(ClientObject.getUserDataInternal().getUsername());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        ControllerManager.getInstance().setLoginController(fxmlLoader.getController());
        Scene scene = new Scene(root);
        stage.setTitle("BFDA Chat | Login");
        stage.setScene(scene);
        Stage currentStage = (Stage)friendsListView.getScene().getWindow();
        currentStage.close();
        stage.setResizable(false);
        stage.setOnCloseRequest(param->{System.exit(0);});
        stage.show();
    }
}

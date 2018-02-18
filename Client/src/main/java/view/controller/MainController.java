package view.controller;

import beans.Group;
import client.interfaces.ClientObj;
import controller.FileHandler;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ServerConnection;
import server.interfaces.ClientServerRegister;
import server.interfaces.ServerMessegeSender;
import view.util.ChatBoxFormat;
import view.util.FriendListFormat;
import beans.Message;
import beans.User;
import com.jfoenix.controls.JFXButton;
import server.interfaces.UserStatuesChangeInterface;

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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.image.ImageView;
import server.interfaces.FriendsDbOperations;
import view.util.FriendRequestFormat;
import view.util.Notification;
import xmlfiles.XmlMessage;

public class MainController implements Initializable {

    @FXML private ListView<User> friendsListView;
    @FXML private ListView<Message> chatBoxListVIew;
    @FXML private ListView<Group> chatGroupsList;
    @FXML private ListView<User> reqFriendsListView;
    @FXML private TextArea announceArea1;
    @FXML private ImageView sendBtn;
    @FXML private TextField chatField;
    @FXML private ImageView sendFileBtn;
    
    @FXML private TextField searchTxtField;
    
    @FXML private JFXButton addGroupBtn;

    //--Formating Components
    @FXML private ImageView bold;
    @FXML private ImageView italic;
    @FXML private ColorPicker fontColorPicker;
    @FXML private ComboBox<String> fontList;
    @FXML private ComboBox<Integer> sizeList;

    //Temp
    @FXML
    
    private String currentChatID ;
    private String currentChatMemberID ;
    private String currentChatUser;
    private String chatGroupName;
    //--

    @FXML ComboBox<String> userStatus;
    
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
    private UserStatuesChangeInterface userStatuesChange;
    private ServerMessegeSender serverMessegeSender;
    private HashMap<String , Vector<Message>> messagesMap =  new HashMap<>();
    private Vector<String> chatMembers = new Vector<>();

    public MainController() throws RemoteException {

        serverConnection = ServerConnection.getInstance();
        userStatuesChange = serverConnection.getUserStatuesChangeObject();
        serverMessegeSender = serverConnection.getRegisteryObject().getServerMessegeSender();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
        initStatus();
        friendsListView.setCellFactory(param -> new FriendListFormat(this));
        chatBoxListVIew.setStyle("-fx-padding: 10 0 0 0;");
        chatBoxListVIew.setCellFactory(param ->  new ChatBoxFormat());
        chatGroupsList.setCellFactory(param -> new GroupListFormat(this));
        reqFriendsListView.setCellFactory(param->new FriendRequestFormat(this) );
        try {
            updateFriendList();
            updateGroupList();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        announceArea1.setEditable(false);
        //name.setText(ClientObject.getUserDataInternal().getUsername());
        formatBarValues();
        formatBarActions();
        showPanes(false);
       
            
   new Thread(()->{      
       try {
       ArrayList<User> allFriendRequest = serverConnection.getRegisteryObject().getFriendsDbOperations()
               .getAllFriendRequests(ClientObject.getUserDataInternal().getId());
       for(User user : allFriendRequest)
       {
           updateFriendRequests(user);
       }
       } catch (RemoteException ex) {
           Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
       }
            
            }).start();
        
        
        
        //Check all Clients Activity 
          new Thread(()->{
            try {
                while(true)
                {
                    Thread.sleep(2000);       
                    checkUsersStatus();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void updateAnnounce(String accouncementString) {
        announceArea1.setText(accouncementString);
        
    }
    
    private void initStatus(){
        userStatus.getItems().addAll("Available","Away","Busy");
        if(ClientObject.getUserDataInternal().getMode().equals("Available")){
            userStatus.getSelectionModel().select(0);
        } else if(ClientObject.getUserDataInternal().getMode().equals("Away")){
            userStatus.getSelectionModel().select(1);
        } else {
            userStatus.getSelectionModel().select(2);
        }
    }

    public void sendBtn(Event event) throws RemoteException, SQLException {
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
            message.setToUsers(chatMembers);

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
        bold.setOnMousePressed(param->{isBold = !isBold; updateTextStyle();});
        italic.setOnMousePressed(param->{isItalic = !isItalic; updateTextStyle();});
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

    public ObservableList<User> getFriendList()
    {
        return friendsListView.getItems();
    }

    public void setSingleChatRoom(String userName) throws RemoteException, SQLException {

        serverMessegeSender = ServerConnection.getInstance().getRegisteryObject().getServerMessegeSender();
        currentChatID = serverMessegeSender.getChatRoomOfClient(ClientObject.getUserDataInternal().getUsername() , userName);
        currentChatMemberID = serverMessegeSender.getChatMemberID(ClientObject.getUserDataInternal().getUsername() ,currentChatID);
        chatMembers = serverMessegeSender.getAllChatMember(currentChatID);
        currentChatUser = userName;
        sendFileBtn.setDisable(false);
        setChat(currentChatID);
    }

    public void setGroupChatRoom(Group groupRoomID) throws SQLException, RemoteException {

        chatGroupName = groupRoomID.getGroupName();
        currentChatMemberID = serverMessegeSender.getChatMemberID(ClientObject.getUserDataInternal().getUsername() ,groupRoomID.getRoomID());
        chatMembers = serverMessegeSender.getAllChatMember(groupRoomID.getRoomID());
        sendFileBtn.setDisable(true);
        currentChatUser = groupRoomID.getRoomID();
        setChat(groupRoomID.getRoomID());

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

    public void saveChat(MouseEvent mouseEvent) {
        System.out.println("Calling");
        XmlMessage.writeXmlFile(ClientObject.getUserDataInternal().getUsername() , currentChatUser , messagesMap.get(currentChatID));
        System.out.println("Done");
    }

    public void logoutBtn(MouseEvent mouseEvent) throws IOException {
        messagesMap.clear();
        serverConnection.getRegisteryObject().getUserStatuesChangeImpl()
                .changeStatues(ClientObject.getUserDataInternal());
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

    public void sendFile(MouseEvent mouseEvent){
//          for(User user : friendsListView.getItems())
//          {
//              user.setStatus(false);
//          }
//          friendsListView.refresh();
        File fileDist = getFileToSend();
        if(fileDist == null)
            return ;

        String senderID = ClientObject.getUserDataInternal().getUsername();
        String receiverID = currentChatUser;
        new Thread(()->{
            try {
                File locationToSave = serverConnection.getRegisteryObject().getServerFileTransfer().requestSendFile(senderID , receiverID , fileDist.getName());

                if(locationToSave == null)
                {
                   Platform.runLater(()->{new Alert(Alert.AlertType.ERROR , "User Refused To Receive file").showAndWait();});
                   return;
                }
                else
                {
                    System.out.println(locationToSave);
                    new FileHandler().splitFile(fileDist , senderID , currentChatUser , locationToSave);
                }
            } catch (IOException e) {
                System.out.println(e.toString());
                Platform.runLater(()->{new Alert(Alert.AlertType.ERROR , "Error Happen While Transfering File").showAndWait();});

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public File getFileToSend() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(bold.getScene().getWindow());
        if (selectedFile != null)
            return selectedFile;
        else
           return null;

    }
    
    public void changeStatus(ActionEvent actionEvent){
        if(!userStatus.getValue().equals(ClientObject.getUserDataInternal().getMode())){
            try {
                ClientObject.getUserDataInternal().setMode(userStatus.getValue());
                userStatuesChange.changeModes(ClientObject.getUserDataInternal());
                initStatus();
            } catch (RemoteException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addGroup(){
    
    }
    
/***********keep away****************/

    public void searchBtn(ActionEvent actionEvent) {
                                    ArrayList<beans.User> userID=new ArrayList<>();

            try {
                                    String searchTextInput= searchTxtField.getText().toString();
                                     Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                    FriendsDbOperations friendOperations = ServerConnection.getInstance().getRegisteryObject().getFriendsDbOperations();
                                    userID = friendOperations.searchForUser(searchTextInput);
                                    
                                    if(!userID.isEmpty())
                                    {
                                                alert.setHeaderText("Friends Manger");
                                                alert.setTitle("AddingNewFriend");
                                                alert.setContentText("YouSend a request to : "+searchTextInput);
                                                
                                                int currentUserID=friendOperations.getIdfromUserName(ClientObject.getUserDataInternal().getUsername());
                                                int anotherUserID=friendOperations.getIdfromUserName(searchTextInput);
                                                 System.out.println(currentUserID);
                                                System.out.println(anotherUserID);

                                                friendOperations.sendFriendRequest(currentUserID,anotherUserID);
                                                
                                            
                                    }
                                    else
                                    {
                                                alert.setHeaderText("Friends Manger");
                                                alert.setTitle("AddingNewFriend");
                                                alert.setContentText("Sorry This User NotFound ");
                                    }
               
                                    alert.showAndWait();
                                    
                }
                        catch (RemoteException ex)
                    {
                               ex.printStackTrace();
                    }                    
      }

/***********keep away****************/
        private void checkUsersStatus() {

        for(User user : getFriendList())
        {
            try {
                boolean oldStatus = user.getStatus();
                boolean res = serverConnection.getRegisteryObject().getUserStatuesChangeImpl().checkOnline(user.getUsername());
                user.setStatus(res);
                if(!oldStatus && res)
                    Notification.displayTray(user.getName()+"Is Online" , "User "+user.getName()+" Is Now Online");
            } catch (RemoteException e) {
               Platform.runLater(()->{new Alert(Alert.AlertType.ERROR , "Server is Off , Application Exiting .... !").showAndWait();});
               System.exit(0);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        friendsListView.refresh();
    }
        
        public void updateFriendRequests(User user)
        {
           reqFriendsListView.getItems().addAll(user);
        }
}

package viewcontroller;

import ViewUtil.FriendListFormat;
import beans.User;
import clientInterfaces.ClientObj;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import model.ChatImpl;
import model.ClientObject;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private ListView<User> friendsListView;
    @FXML private TextArea announceArea1;

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
    }


    public void updateAnnounce(String str)
    {
        announceArea1.setText(str);
    }




}

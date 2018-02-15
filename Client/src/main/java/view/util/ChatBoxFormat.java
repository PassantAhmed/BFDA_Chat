package view.util;

import beans.Message;
import beans.User;
import client.interfaces.ClientObj;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.ClientObject;
import view.controller.MainController;

import java.io.IOException;

public class ChatBoxFormat extends ListCell<Message> {


    private Node usedParent;
    private Circle profilePicCircle;
    Text msgText;



    public ChatBoxFormat()
    {

        try {

            FXMLLoader.load(getClass().getResource("/fxml/LeftChatItem.fxml"));
            FXMLLoader.load(getClass().getResource("/fxml/RightChatItem.fxml"));

//
//            statusCircle = (Circle)parent.lookup("#statusCircle");
//            name = (Label)parent.lookup("#name");
//            status = (Label)parent.lookup("#status");
//            msgCount = (Label)parent.lookup("#msgCount");
//            img = new Image("https://www.filmibeat.com/img/220x90x275/popcorn/profile_photos/scarlett-johansson-20141121172716-5935.jpg");
//            parent.setOnMouseClicked(param->{ unReadMsg = 0; updateMsgCount();});


        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        try {
            if(!empty)
            {

                System.out.println(item.getFromUser());
                System.out.println(ClientObject.getUserDataInternal().getUsername());
                System.out.println(item.getFromUser().equals(ClientObject.getUserDataInternal().getUsername()));

                if(item.getFromUser().equals(ClientObject.getUserDataInternal().getUsername()))
                    usedParent = getCurrentParent(true);
                else
                    usedParent = getCurrentParent(false);
                initComponent(usedParent);
                FontWeight fontWeight = item.isBold() ? FontWeight.BOLD : FontWeight.NORMAL;
                FontPosture fontPosture = item.isItalic() ? FontPosture.ITALIC : FontPosture.REGULAR;
                msgText.setFont(Font.font(item.getMessageFontFamily() ,fontWeight , fontPosture , Integer.valueOf(item.getMessageFontSize())));
                msgText.setText(item.getMessageContent());

                setGraphic(usedParent);
                this.setStyle("-fx-background-color: white");
            }
            else
            {
                setGraphic(null);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    private Node getCurrentParent(boolean flag) throws IOException {

        if(flag) return FXMLLoader.load(getClass().getResource("/fxml/RightChatItem.fxml"));
        else return FXMLLoader.load(getClass().getResource("/fxml/LeftChatItem.fxml"));

    }
    private void initComponent(Node usedParent)
    {
        profilePicCircle = (Circle)usedParent.lookup("#profilePicID");
        msgText = (Text)usedParent.lookup("#msgText");
    }
}

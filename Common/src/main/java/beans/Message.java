/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Passant
 */
public class Message {

    private String fromUser;
    private ArrayList<String> toUsers;
    private String messageContent;
    private String messageFontColor;
    private String messageFontFamily;
    private String messageFontSize;
    private Date messageDate;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public ArrayList<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(ArrayList<String> toUsers) {
        this.toUsers = toUsers;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageFontColor() {
        return messageFontColor;
    }

    public void setMessageFontColor(String messageFontColor) {
        this.messageFontColor = messageFontColor;
    }

    public String getMessageFontFamily() {
        return messageFontFamily;
    }

    public void setMessageFontFamily(String messageFontFamily) {
        this.messageFontFamily = messageFontFamily;
    }

    public String getMessageFontSize() {
        return messageFontSize;
    }

    public void setMessageFontSize(String messageFontSize) {
        this.messageFontSize = messageFontSize;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

}

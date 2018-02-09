package clientInterfaces;

import java.io.Serializable;

public interface ChatInterface extends Serializable{

    public void updateChat(String chatID , String msg , String clientID);
    public void updateAnnouncement(String msg);

}

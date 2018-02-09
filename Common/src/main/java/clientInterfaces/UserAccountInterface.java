package clientInterfaces;

import beans.User;

import java.io.Serializable;
import java.util.List;

public interface UserAccountInterface extends Serializable{

    public void updateUserStatus(String userID , String status);
    public void updateUserMode(String userID , String mode);
    public void updateUserContactList(String userID , List<User> Contacts);

}

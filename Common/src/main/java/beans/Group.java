package beans;

import java.io.Serializable;

public class Group implements Serializable {

    private String groupName;
    private String roomID;

    public Group() {
    }

    public Group(String groupName, String roomID) {
        this.groupName = groupName;
        this.roomID = roomID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}

package beans;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean gender;
    private String country;
    private LocalDate birthdate;
    private String userPic;
    private boolean status;
    private String mode;
    private int newMsgCount;


    public User(int id,String name,String username, String email,String password,
                boolean gender,String country,LocalDate birthdate,String userPic,
                boolean status,String mode) {
        this.id = id;
        this.name=name;
        this.username = username;
        this.email= email;
        this.password=password;
        this.gender=gender;
        this.country=country;
        this.birthdate=birthdate;
        this.userPic=userPic;
        this.status=status;
        this.mode=mode;
    }
    
    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.country = user.getCountry();
        this.birthdate = user.getBirthdate();
        this.userPic = user.getUserPic();
        this.status = user.getStatus();
        this.mode = user.getMode();
    }

    public int getNewMsgCount() {
        return newMsgCount;
    }

    public void setNewMsgCount(int newMsgCount) {
        this.newMsgCount = newMsgCount;
    }

    public User() 
    {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }





    
}

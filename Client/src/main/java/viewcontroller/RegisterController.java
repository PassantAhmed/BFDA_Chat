package viewcontroller;

import beans.User;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import myutilities.ValidationChecks;

public class RegisterController implements Initializable {
     
    private ValidationChecks checker = new ValidationChecks();
    @FXML private Spinner<String> countryid;
    @FXML private DatePicker dobid;
    @FXML private TextField emailid;
    @FXML private TextField fullnameid;
    @FXML private ToggleGroup genderGroup;
    @FXML private TextField passwordid;
    @FXML private TextField repasswordid;
    @FXML private TextField usernameid;


    @FXML private Label hyperloginid;
    @FXML private Button submitid;


    
    @FXML
    private void registerAction(ActionEvent event) {
        User user = new User();
        boolean genderBoolean;
        user.setUsername(usernameid.getText());
        user.setPassword(passwordid.getText());
        String repassword = repasswordid.getText();
        user.setName(fullnameid.getText());
        user.setEmail(emailid.getText());
        user.setBirthdate(dobid.getValue());
        user.setCountry(countryid.getValue());

        genderBoolean = genderGroup.getSelectedToggle().getUserData().toString() == "male";
        user.setGender(genderBoolean);
        
        if(validateUser(user)){
            //check if passwords matches
            if(user.getPassword().equals(repassword)){
                //data is valid send user object to server
            }
        }
    }
    
    private boolean validateUser(User user){
        boolean isValid =false;
        
        if(checker.isName(user.getName())
                && checker.isUserName(user.getUsername())
                && checker.isEmail(user.getEmail())
                && checker.isValidPassword(user.getPassword())){
        
            isValid =true;
        }
            
        return isValid;
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

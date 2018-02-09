package viewcontroller;

import beans.User;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import myutilities.ValidationChecks;

public class RegisterController implements Initializable {
     
    ValidationChecks checker = new ValidationChecks();
    
    @FXML
    private ComboBox<String> countryid;

    @FXML
    private TextField dobid;

    @FXML
    private TextField emailid;

    @FXML
    private TextField fullnameid;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private Label hyperloginid;
    
    @FXML
    private TextField passwordid;

    @FXML
    private TextField repasswordid;

    @FXML
    private Button submitid;

    @FXML
    private TextField usernameid;



    
    @FXML
    private void registerAction(ActionEvent event) {
        User user = new User();
        boolean genderBoolean;
        user.setUsername(usernameid.getText());
        user.setPassword(passwordid.getText());
        String repassword = repasswordid.getText();
        user.setName(fullnameid.getText());
        user.setEmail(emailid.getText());
        //user.setBirthdate((Date) dobid.getText());
        user.setCountry(countryid.getValue());
   
        if(genderGroup.getSelectedToggle().getUserData().toString() == "male"){
            genderBoolean = true;
        }else{
            genderBoolean = false;
        }
        
        user.setGender(genderBoolean);
        
        if(validateUser(user)==true){
            //check if passwords matches
            if(user.getPassword() == repassword){
                //data is valid send user object to server
            }
        }
    }
    
    private boolean validateUser(User user){
        boolean isValid =false;
        
        if(checker.isName(user.getName()) == true 
                && checker.isUserName(user.getUsername()) == true
                && checker.isEmail(user.getEmail()) == true
                && checker.isValidPassword(user.getPassword()) == true){
        
            isValid =true;
        }
            
        return isValid;
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

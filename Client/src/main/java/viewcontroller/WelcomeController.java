package viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ServerConnection;
import myutilities.ValidationChecks;

public class WelcomeController implements Initializable {
    
    @FXML private Label errorLabel;
    @FXML private TextField serverIpField;

    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean validateIp(String ipAddress)
    {
        ValidationChecks validationChecks = new ValidationChecks();
        return validationChecks.isIP(ipAddress);
    }
    public void connect(ActionEvent actionEvent) {
        String ipAddress = serverIpField.getText();
        if(!validateIp(ipAddress))
            errorLabel.setText("IP in Not Valid IP");
        else
        {
            connectToServer(ipAddress);
        }

    }

    public void connectToServer(String ipAddress)
    {
        ServerConnection serverConnection = ServerConnection.getInstance(ipAddress);
        if (serverConnection.establiseConnection())
        {
            return;
        }
        else
        {
            errorLabel.setText("Server is Not Reachable");
        }
    }

}



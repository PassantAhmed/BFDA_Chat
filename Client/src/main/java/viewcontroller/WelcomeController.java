package viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ServerConnection;
import myutilities.ValidationChecks;

public class WelcomeController implements Initializable {
    
    @FXML private Label errorLabel;
    @FXML private TextField serverIpField;
    @FXML private Button loginButton;
    
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
            new Thread(()->{connectToServer(ipAddress);}).start();
        }

    }

    public void connectToServer(String ipAddress)
    {
        ServerConnection serverConnection = ServerConnection.getInstance(ipAddress);
        connectionEstablishingMode(true);
        if (serverConnection.establiseConnection())
        {
            Platform.runLater(()->{errorLabel.setText("Connection Establised !");});

        }
        else
        {
            Platform.runLater(()->{errorLabel.setText("Server is Not Reachable");});
        }
        connectionEstablishingMode(false);
    }

    public void connectionEstablishingMode(boolean status)
    {
        if(status)
            Platform.runLater(()-> {
                errorLabel.setText("");
                loginButton.setText("Connecting ... ");
                loginButton.setDisable(true);
                serverIpField.setDisable(true);});
        else
            Platform.runLater(()-> {
                loginButton.setDisable(false);
                loginButton.setText("Connect");
                serverIpField.setDisable(false);});
    }

}



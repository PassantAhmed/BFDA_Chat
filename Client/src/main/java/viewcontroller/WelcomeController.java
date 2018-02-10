package viewcontroller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClientObject;
import model.ServerConnection;
import myutilities.ValidationChecks;
import serverInterfaces.ServerObj;

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
            new Thread(()->{
                try {
                    connectToServer(ipAddress);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public void connectToServer(String ipAddress) throws RemoteException {
        ServerConnection serverConnection = ServerConnection.getInstance();
        serverConnection.setHost(ipAddress);
        connectionEstablishingMode(true);
        if (serverConnection.establiseConnection())
        {
            Platform.runLater(()->{errorLabel.setText("Connection Establised !");});
            ServerObj serverObj = (ServerObj)ServerConnection.getInstance().getRegisteryObject();
            serverObj.getClientRegister().registerUser(new ClientObject());

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



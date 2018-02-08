import BFDA.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import model.UserLogin;

public class MainApp extends Application {
    public static void main(String[] args) {
      launch();
    }

    public void start(Stage primaryStage) throws Exception {

        //UserLogin Test
        //UserLogin userLogin = new UserLogin("mohamedfawzy" , "1234");
        //userLogin.login();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegisterScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public static void main(String[] args) {
      launch();
    }

    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WelcomeScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();


    }
}

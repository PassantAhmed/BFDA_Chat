package BFDA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application
{
    public static void main( String[] args )
    {
            launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashboardScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(param->{System.exit(0);});
        primaryStage.show();
    }

}

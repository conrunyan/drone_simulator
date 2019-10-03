package display;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/Display.fxml"));
        Parent gui = loader.load();

        Scene scene = new Scene(gui);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }
}

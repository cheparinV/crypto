package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ASUS-PC on 16.05.2017.
 */
public class MainApp extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        loader.setController(new MainController());

        Scene scene = new Scene(root, 300, 350);

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
}

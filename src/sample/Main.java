package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Darian Colon
 */
public class Main extends Application {
    /**
     * The starting point of a javaFX program
     *
     * @param primaryStage
     * @return Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller cObject = new Controller();

        Parent root = FXMLLoader.load(getClass().getResource("ProductLayout.fxml"));
        primaryStage.setTitle("Production Line Tracker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}

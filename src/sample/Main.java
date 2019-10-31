package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Date;

/** Allows user to add products to a table/chart.
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

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Production Line Tracker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}

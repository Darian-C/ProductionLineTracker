package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
  /**This part of the program creates the main scene of the program.
     * @author Darian Colon
     * @param primaryStage this is the main scene of the program.
     * @throws Exception this is uses, should and error arise.
     */

  @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductLayout.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();

  }


  public static void main(String[] args) {
    launch(args);
  }

}

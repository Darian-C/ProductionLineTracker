package sample;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;




/**
 * @author Darian Colon
 */
public class Controller implements Initializable {
    /**
     * Button action control
     *
     * @param primaryStage
     * @return Exception
     *///JDBC = Java Database Connectivity
    //Import Package -> Load and Register JDBC Driver ->
    //Establish Connection -> Create Statement -> Execute Query ->
    //Process Result -> Close
    @FXML
    private URL url;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnClick;

    @FXML
    private Text outputText;

    @FXML
    private ChoiceBox<String> Choices;

    @FXML
    public void display(ActionEvent event) {

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/H2";

        final String USER = "";
        final String PASS = "";

        Connection connect = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection to database
            System.out.println("Connecting to database....");
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected!");

            //STEP 3:Statements/Prepared Statement/Calloble Statement
            System.out.println("Inserting...");
            stmt = connect.createStatement();

            //STEP 4:Insert info to table

            //String sqlItems = ("INSERT INTO Product" + "(name, type, manufacturer)" + "VALUES ('AUDIO', 'Apple', 'iPod')");
            String sql =("INSERT INTO Product" + "(name, type, manufacturer)" + "VALUES ('Ipod', 'Audio', 'Apple')");
            //PreparedStatement preparedItem = connect.prepareStatement(sqlItems);
            //preparedItem.setString(1, "Audio");
            //preparedItem.setString(2, "Apple");
            //preparedItem.setString(3, "iPod");
            //preparedItem.executeUpdate();
            stmt.executeUpdate(sql);
            System.out.println(sql);
            System.out.println("Inserted new information.");

            //STEP 5: close
            //preparedItem.close();
            stmt.close();
            connect.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Choices.getItems().add("Apple");
        Choices.getItems().add("Samsung");
        Choices.setValue("Apple");
        Choices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });

    }

}




package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import java.sql.*;
/**
 * @author Darian Colon
 */
public class Controller {
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
    private Button btnClick;

    @FXML
    private Text outputText;

    @FXML
    private ChoiceBox Choices;

    @FXML
    public void display(ActionEvent event) {

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/HR";

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

            //String sqlItems = "INSERT INTO Product" + "VALUES ('AUDIO', 'Apple', 'iPod')";
            stmt.executeUpdate("INSERT INTO Product" + "VALUES ('AUDIO', 'Apple', 'iPod')");
            //PreparedStatement preparedItem = connect.prepareStatement(sqlItems);
            //preparedItem.setString(1, "Audio");
            //preparedItem.setString(2, "Apple");
            //preparedItem.setString(3, "iPod");
            //preparedItem.executeUpdate();


            System.out.println("Inserted new information.");

            //STEP 5: close
            //preparedItem.close();
            stmt.close();
            connect.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}



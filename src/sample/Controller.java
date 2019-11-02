package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * Structure of the program and storage data.
 *
 * @author Darian Colon
 */
public class Controller implements Initializable {
    /**
     * @param Controller
     * JDBC = Java Database Connectivity
     * Import Package -> Load and Register JDBC Driver ->
     * Establish Connection -> Create Statement -> Execute Query ->
     * Process Result -> Close
     */

    @FXML
    private URL url;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnClick;

    @FXML
    private Text outputText;

    @FXML
    private TextField Name;

    @FXML
    private TextField Manu;

    @FXML
    private TableView<Product> Table;

    @FXML
    private TableColumn<?, ?> nameT;

    @FXML
    private TableColumn<?, ?> manuT;

    @FXML
    private TableColumn<?, ?> typeT;

    @FXML
    private ChoiceBox<String> Choices;

    @FXML
    private ComboBox<String> Combo;

    @FXML
    private TextArea prodLog;

    /**
     * The Above code uses the FXML names for combo box,
     * choice box, buttons, TableView, etc.
     *
     * @param event The code below Connects to the database,
     *              gives an action to the FXML objects,
     *              and allows the user interact with the program.
     */
    @FXML
    public void display(ActionEvent event) {
        String Prod = toString();
        prodLog.appendText(Prod);

        String pT = Choices.getValue();
        String pM = Manu.getText();
        String pN = Name.getText();


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
            String sql0 = ("INSERT INTO Product" + "(Name, Type, Manufacturer)" + "VALUES (?, ?, ?)");
            PreparedStatement preparedItem = connect.prepareStatement(sql0);
            preparedItem.setString(1, pN);
            preparedItem.setString(2, pT);
            preparedItem.setString(3, pM);
            preparedItem.executeUpdate();
            String sqlviewp = "select * From product";
            ResultSet rs = stmt.executeQuery(sqlviewp);
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Type = rs.getString("Type");
                String Manufacturer = rs.getString("Manufacturer");
                System.out.println(Name + Type + Manufacturer);
            }

            //STEP 5: close
            //preparedItem.close();
            stmt.close();
            connect.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    //ChoiceBox population
    void btnClick(ActionEvent event) {
        System.out.println("Inserted new information.");
    }

    ObservableList<Product> productLine = FXCollections.observableArrayList();

    /**
     * @param location
     * @param resources The below code populates combo box with values 1-10.
     *                  Allows user to enter values into the combo box.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Combo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        Combo.setEditable(true);
        Combo.getSelectionModel().selectFirst();
        Choices.getItems().addAll("Audio", "Visual", "AudioMobile", "VisualMobile");

        nameT.setCellValueFactory(new PropertyValueFactory("name"));
        manuT.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        typeT.setCellValueFactory(new PropertyValueFactory("type"));
        Table.setItems(productLine);

        productLine.addAll();

    }


}




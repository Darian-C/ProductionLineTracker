package sample;

//import apple.laf.JRSUIConstants;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;



/**
 * This is where all th control for the actions between
 *the user and the program are located. Also stores data to the data babse.
 * @author Darian Colon
 */
public class Controller implements Initializable {
  /**JDBC = Java Database Connectivity.
     * Import Package -> Load and Register JDBC Driver ->.
     * Establish Connection -> Create Statement -> Execute Query ->.
     * Process Result -> Close.
     * @param Controller controls the majority of the functionality of the program.
     */
  Connection connect = null;
  ObservableList<Product> productLine;
  @FXML
  private URL url;

  @FXML
    private ResourceBundle resources;

  @FXML
    private ListView<Product> lvProducts;

  @FXML
    private Button btnAddProduct;

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
    private ChoiceBox<ItemType> Choices;

  @FXML
    private ComboBox<String> Combo;

  @FXML
    private Button btnRecordProduct;

  @FXML
    private TextArea prodLog;

  /**The Above code uses the FXML names for combo box, choice box, buttons, TableView, etc.
     * @param
     */
  public void connection() {
        try {
            // JDBC driver name and database URL
            final String JDBC_DRIVER = "org.h2.Driver";
            final String DB_URL = "jdbc:h2:./res/H2";

            Properties prop = new Properties();
            prop.load(new FileInputStream("res/properties"));

            final String USER = "";
            final String PASS = prop.getProperty("password");

            try {
                // STEP 1: Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //STEP 2: Open a connection to database
                System.out.println("Connecting to database....");
                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected!");

                //stmt.close();



            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The below code takes the name, manufacturer, and ItemType
     * from the choice box that the user inputs and adds it to the
     * tableview on the product tab
     *
     * @param event
     */

    @FXML
    void addProduct(ActionEvent event) {
        Product p = new Widget(Name.getText(), Manu.getText(), Choices.getValue());
        connection();

        System.out.println("Inserting...");


        //STEP 4:Insert info to table
        String sql0 = ("INSERT INTO Product" + "(Name, Type, Manufacturer)" + "VALUES (?, ?, ?)");
        PreparedStatement preparedItem = null;
        try {
            preparedItem = connect.prepareStatement(sql0);

            preparedItem.setString(1, p.getName());
            preparedItem.setString(2, p.getType().code);
            preparedItem.setString(3, p.getManufacturer());
            preparedItem.executeUpdate();

            preparedItem.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productLine.clear();

        try {
            loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
//Error needs work Here!!!
    @FXML
    void recordProduct(ActionEvent event) {
        prodLog.appendText(String.valueOf(productLine));
        System.out.println("Inserting...");
        connection();
        ProductionRecord p = new ProductionRecord(0,0,"0", new java.util.Date(System.currentTimeMillis()));
        //STEP 4:Insert info to table
        String sql1 = ("INSERT INTO PRODUCTIONRECORD" + "(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
                + "VALUES (?, ?, ?, ?)");
        PreparedStatement preparedItem = null;
        try {
            preparedItem = connect.prepareStatement(sql1);

            preparedItem.setInt(1, p.getProductionNumber());
            preparedItem.setInt(2, p.getProductID());
            preparedItem.setString(3, p.getSerialNumber());
            //preparedItem.setDate(4,p.getProdDate());
            preparedItem.executeUpdate();

            preparedItem.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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

        Choices.getItems().addAll(ItemType.values());

        productLine = FXCollections.observableArrayList();
        nameT.setCellValueFactory(new PropertyValueFactory("name"));
        manuT.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        typeT.setCellValueFactory(new PropertyValueFactory("type"));
        Table.setItems(productLine);

        lvProducts.setItems(productLine);

        try {
            loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadProduct() throws SQLException {
        connection();
        Statement stmt = null;
        stmt = connect.createStatement();
        String sqlviewp = "select * From PRODUCT";
        ResultSet rs = stmt.executeQuery(sqlviewp);
        while (rs.next()) {
           String Name = rs.getString("Name");
           String Type = rs.getString("Type");
           String Manufacturer = rs.getString("Manufacturer");
           ItemType t = ItemType.AUDIO;
            if(Type.equals("AU")){
                t = ItemType.AUDIO;
            }
            if(Type.equals("VI")){
                t = ItemType.VISUAL;
            }
            if(Type.equals("AM")){
                t = ItemType.AUDIO_MOBILE;
            }
            if(Type.equals("VM")){
                t = ItemType.VISUAL_MOBILE;
            }
           Product q = new Widget(Name, Manufacturer, t);
           productLine.add(q);
        }
    }
}




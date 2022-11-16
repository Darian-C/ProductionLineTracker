package sample;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductionTabsController {

    private Connection conn = null;
    private Statement stmt = null;


    private ObservableList<Product> olProductLine;

    private int countOfAudio = 0;
    private int countOfAudioMobile = 0;
    private int countOfVisual = 0;
    private int countOfVisualMobile = 0;

    private final ArrayList<ProductionRecord> productionLog = new ArrayList<>();

    @FXML
    private Label lblProductLineMessage;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtManufacturer;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnRecordProduction;

    @FXML
    private TextArea taProductLine;

    @FXML
    private TextArea taViewProd;

    @FXML
    private ComboBox<String> cbQuantity;


    @FXML
    private ChoiceBox<ItemType> cbType;

    @FXML
    private TableView<Product> tvProductLine;

    @FXML
    private ListView<Product> lvProductLine;


    public void initialize() {


        for (int i = 1; i <= 10; i++) {
            cbQuantity.getItems().add(String.valueOf(i));
        }

        cbQuantity.setEditable(true);

        cbQuantity.getSelectionModel().selectFirst();


        for (ItemType type : ItemType.values()) {
            cbType.getItems().add(type);
        }

        cbType.getSelectionModel().selectFirst();




        olProductLine = FXCollections.observableArrayList();

        setupProductLineTable();
        lvProductLine.setItems(olProductLine);
        loadProductList();
        loadProductionLog();
    }

    private void testEmployee() {

        String name = "Tim";

        String password = "aBcd!";
        Employee employee = new Employee(name, password);
        System.out.println(employee);
    }

    private void initializeDatabase() {
        final String jdbcDriver = "org.h2.Driver";
        final String dbUrl = "jdbc:h2:./res/ProdDB";

        Properties prop = new Properties();
        String pass = "";
        try {
            prop.load(new FileInputStream("res/properties"));
            pass = prop.getProperty("password");
        } catch (Exception ex) {
            System.out.println("Error opening file " + ex);
            Alert a = new Alert(Alert.AlertType.ERROR, "Error retrieving database password "
                    + "from properties file.", ButtonType.OK);
            a.show();
        }


        final String user = "";


        System.out.println("Attempting to connect to database");
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbUrl, user, pass);
            stmt = conn.createStatement();
            System.out.println("Successfully connected to database!");
        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "Error connecting to database",
                    ButtonType.OK);
            a.show();
        }
    }

    private void closeDatabase() {
        System.out.println("Attempting to close database.");
        try {
            stmt.close();
            conn.close();
            System.out.println("Database closed.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "Error closing database.");
            a.show();
        }
    }

    private static void playerDriver() {
        AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
                "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");

        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction",
                new Screen("720x480", 40, 22),
                MonitorType.LCD);
        ArrayList<MultimediaControl> productList = new ArrayList<>();
        productList.add(newAudioProduct);
        productList.add(newMovieProduct);
        for (MultimediaControl p : productList) {
            System.out.println(p);
            p.play();
            p.stop();
            p.next();
            p.previous();
        }
    }

   @FXML
    void addProduct(ActionEvent event) {
        lblProductLineMessage.setText("");
        if (txtProductName.getText().length() > 0) {
            if (txtManufacturer.getText().length() > 0) {
                if (cbType.getValue() != null) {
                    System.out.println("Adding Product");

                    ItemType itemType = cbType.getValue();
                    String prodMan = txtManufacturer.getText();
                    String prodName = txtProductName.getText();
                    initializeDatabase();
                    final String sql = "INSERT INTO Product(type, manufacturer, name) VALUES (?, ?, ?)";

                    try {
                        PreparedStatement ps = null;
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, itemType.code);
                        ps.setString(2, prodMan);
                        ps.setString(3, prodName);
                        ps.executeUpdate();

                        System.out.println("Product added to database.");

                        loadProductList();

                        ps.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        closeDatabase();
                    }
                    txtManufacturer.setText("");
                    txtProductName.setText("");
                } else {
                    lblProductLineMessage.setText("Item Type must be set");
                    cbType.requestFocus();
                }
            } else {
                lblProductLineMessage.setText("Manufacturer cannot be empty");
                txtManufacturer.requestFocus();
            }

        } else {
            lblProductLineMessage.setText("Product name cannot be empty");
            txtProductName.requestFocus();
        }

    }

    private void setupProductLineTable() {

        TableColumn<Product, String> productIdCol = new TableColumn<>("Product ID");
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> productNameCol = new TableColumn<>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Product, String> manufacturerCol = new TableColumn<>("Manufacturer");
        manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<Product, String> itemTypeCol = new TableColumn<>("Item Type");
        itemTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        tvProductLine.setItems(olProductLine);
        tvProductLine.getColumns().addAll(productIdCol, productNameCol, manufacturerCol, itemTypeCol);

    }

    private void loadProductList() {
        initializeDatabase();
        try {
            String sql = "SELECT id, name, type, manufacturer FROM Product";
            ResultSet rs = stmt.executeQuery(sql);
            olProductLine.clear();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String typeCode = rs.getString("type");
                ItemType type;
                switch (typeCode) {
                    case "AU":
                        type = ItemType.AUDIO;
                        break;
                    case "VI":
                        type = ItemType.VISUAL;
                        break;
                    case "AM":
                        type = ItemType.AUDIO_MOBILE;
                        break;
                    case "VM":
                        type = ItemType.VISUAL_MOBILE;
                        break;
                    default:
                        type = ItemType.AUDIO;
                        System.out.println("Bad Type");
                }
                String manufacturer = rs.getString("manufacturer");

                Product addedProduct = new Widget(id, name, manufacturer, type);

                olProductLine.add(addedProduct);

            }

            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeDatabase();
    }
    @FXML
    void recordProduction(ActionEvent event) {
        System.out.println("in recordProduction");
        Product selectedProduct = lvProductLine.getSelectionModel().getSelectedItem();

        Integer numProduced = Integer.parseInt(cbQuantity.getValue());

        ArrayList<ProductionRecord> productionRun = new ArrayList<>();

        int typeCount = 0;
        switch (selectedProduct.getType().code) {
            case "AU":
                typeCount = countOfAudio;
                countOfAudio += numProduced;
                break;
            case "VI":
                typeCount = ++countOfVisual;
                countOfVisual += numProduced;
                break;
            case "AM":
                typeCount = ++countOfAudioMobile;
                countOfAudioMobile += numProduced;
                break;
            case "VM":
                typeCount = ++countOfVisualMobile;
                countOfVisualMobile += numProduced;
                break;
            default:
                System.out.println("Bad Type for item type in load production log");
        }
        for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
            ProductionRecord pr = new ProductionRecord(selectedProduct, typeCount++);
            productionRun.add(pr);
        }


        addToProductionDB(productionRun);
        loadProductionLog();
        showProduction();

    }

    private void showProduction() {
        StringBuilder fieldContent = new StringBuilder();

        String productName = "";
        int id;

        for (ProductionRecord pr : productionLog) {
            id = pr.getProductID();

            for (Product product : olProductLine) {
                if (product.getId() == id) {
                    productName = product.getName();
                    break;
                }
            }

            fieldContent.append("Prod. Num: ").append(pr.getProductionNum())
                    .append(" Product Name: ")
                    .append(productName).append(" Serial Num: ").append(pr.getSerialNum()).append(" Date: ")
                    .append(pr.getProdDate()).append("\n");

        }
        taViewProd.setText(fieldContent.toString());
    }

    private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
        initializeDatabase();
        try {
            System.out.println("Inserting production records in database.");
            Timestamp ts;
            PreparedStatement ps = null;
            for (ProductionRecord pr : productionRun) {
                ts = new Timestamp(pr.getProdDate().getTime());
                final String sql = "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
                        + " VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, pr.getProductID());
                ps.setString(2, pr.getSerialNum());
                ps.setTimestamp(3, ts);
                ps.executeUpdate();
                System.out.println("Record inserted in database.");
                ps.close();
            }
            System.out.println("Completed inserting production records!");
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
    }

    private void loadProductionLog() {
        System.out.println("loadProductionLog!");
        initializeDatabase();
        try {


            productionLog.clear();
            countOfAudio = 0;
            countOfVisualMobile = 0;
            countOfVisual = 0;
            countOfAudioMobile = 0;

            String sql = "SELECT * FROM PRODUCTIONRECORD";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int prodNum = rs.getInt("production_num");
                int id = rs.getInt("product_id");
                String serial = rs.getString("serial_num");
                Date prodDate = rs.getDate("date_produced");

                ProductionRecord pr = new ProductionRecord(prodNum, id, serial, prodDate);
                productionLog.add(pr);
                switch (serial.substring(3, 5)) {
                    case "AU":
                        countOfAudio++;
                        break;
                    case "VI":
                        countOfVisual++;
                        break;
                    case "AM":
                        countOfAudioMobile++;
                        break;
                    case "VM":
                        countOfVisualMobile++;
                        break;
                    default:
                        System.out.println("Bad Type for item type in load production log");
                }
            }
            rs.close();
            showProduction();
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "Error loading production log",
                    ButtonType.OK);
            a.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        closeDatabase();
    }
    @FXML
    private TextArea taEmployee;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtPassword;

    @FXML
    void createEmployee(ActionEvent event) {
        String name = txtEmpName.getText();
        String password = txtPassword.getText();
        Employee employee = new Employee(name, password);
        taEmployee.clear();
        taEmployee.appendText(employee.toString());

    }
}
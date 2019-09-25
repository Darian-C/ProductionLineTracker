package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
/**
 * @author Darian Colon
 */
public class Controller {
    /**
     * Button action control
     *
     * @param primaryStage
     * @return Exception
     */
//How do i print to the screen after the button in the Product Line tab
//called Add Product is clicked.
    @FXML
    private Button btnClick;

    @FXML
    private Text outputText;

    @FXML
    public void display(ActionEvent event) {
        System.out.println("Clicked");
        }
    }


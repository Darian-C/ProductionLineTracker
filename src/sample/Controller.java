package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
//How do i print to the screen after the button in the Product Line tab
//called Add Product is clicked.
    @FXML
    private Button btnClick;

    @FXML
    private Text outputText;

    @FXML
    public void display(ActionEvent event) {
        if(event.getSource()==btnClick){
            System.out.println("Clicked");
        }
    }

}
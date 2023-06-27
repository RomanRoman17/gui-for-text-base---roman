import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class mainmenuController {
    @FXML
    private Button productsButton;

    //these methods are used to move to different scenes when their respective buttons are clicked

    public void browseProducts(ActionEvent event) throws Exception {
        App.setRoot("categorys");
    }

    public void checkout(ActionEvent event) throws Exception {
        App.setRoot("creditCheck");
    }

    public void cart(ActionEvent event) throws Exception {
        App.setRoot("viewcart");
    }
}

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;

public class cartController implements Initializable{
    static ArrayList<Product> cart = categoriesController.cart;
    static HashMap<Product, Integer> cartMap = new HashMap<Product, Integer>();
    @FXML
    private VBox vboxCart;
    @Override
    //this method is called when the fxml file is loaded and gets the cart from the categories controller and displays it in a listview
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Text> listView = new ListView<Text>();
        countCart();
        double total = 0;
        for (Product product : cartMap.keySet()) {
            Text txt = new Text("Product: " + product.getName() + " Quantity: " + cartMap.get(product) + " Total Price: " + product.getPrice() * cartMap.get(product));
            listView.getItems().add(txt);
            total += product.getPrice() * cartMap.get(product);
        }
        Text txt = new Text("Total: " + total);
        listView.getItems().add(txt);
        vboxCart.getChildren().add(listView);
    }

    private static void countCart() {
        // Loop through the cart and add the products to a hashmap. The key is the product and the value is the quantity, this is used to track the quantity of each product in the cart.
        for (Product product : cart) {
            if (cartMap.containsKey(product)) {
                cartMap.put(product, cartMap.get(product) + 1);
            }
            else{
                cartMap.put(product, 1);
            }
        }
    }
    public void backtoMain(ActionEvent event) throws Exception {
        App.setRoot("mainmenu");
    }

}

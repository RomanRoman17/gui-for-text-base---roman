import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class creditCardController {
    @FXML private TextField creditcardBox;
    @FXML private Button purchaseButton;

    private static void countCart() {
        // Loop through the cart and add the products to a hashmap. The key is the product and the value is the quantity, this is used to track the quantity of each product in the cart.
        for (Product product : cartController.cart) {
            if (cartController.cartMap.containsKey(product)) {
                cartController.cartMap.put(product, cartController.cartMap.get(product) + 1);
            }
            else{
                cartController.cartMap.put(product, 1);
            }
        }
    }
    
    @FXML
    private void purchase(ActionEvent e) throws Exception {
        //this method is called when the purchase button is clicked and it displays a success message and the products that were purchased only if the credit card number is valid
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        Text text = new Text("PURCHASE SUCCESSFUL");
        Text text2 = new Text("Thank you for shopping with Romazon!");
        Text text3 = new Text("--------------------------");
        Text text4 = new Text("YOU PURCHASED:");
        text.styleProperty().set("-fx-font: 25 arial;");
        text2.styleProperty().set("-fx-font: 25 arial;");
        text3.styleProperty().set("-fx-font: 25 arial;");
        text4.styleProperty().set("-fx-font: 25 arial;");
        vbox.getChildren().add(text);
        vbox.getChildren().add(text2);
        vbox.getChildren().add(text3);
        vbox.getChildren().add(text4);
        ListView<Text> listView = new ListView<Text>();
        double total = 0;
        for (Product product : cartController.cartMap.keySet()) {
            Text txt = new Text("Product: " + product.getName() + " Quantity: " + cartController.cartMap.get(product) + " Total Price: " + product.getPrice() * cartController.cartMap.get(product));
            listView.getItems().add(txt);
            total += product.getPrice() * cartController.cartMap.get(product);
        }
        double total2 = total *1.13;
        Text txt = new Text("Total: " + total2);
        listView.getItems().add(txt);
        Button b = new Button();
        b.setText("Done");
        b.setOnAction(event -> {
            System.exit(0);
        });
        vbox.getChildren().addAll(listView, b);
        stage.setScene(scene);
        try {
            Long.parseLong(creditcardBox.getText());
            if (creditcardBox.getText().length() == 16) {
                stage.show(); 
            }
            else {
                System.out.println("Invalid credit card number");
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Invalid credit card number");
            return;
        }
    }

    public void backtoMain(ActionEvent event) throws Exception {
        App.setRoot("mainmenu");
    }
}
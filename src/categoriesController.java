import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class categoriesController implements Initializable {
    static ArrayList<Product> cart = new ArrayList<Product>();

    private static ArrayList<Product> productByCategory(String category) throws FileNotFoundException {
        //this method gets the products from the products.txt file and returns an arraylist of products
        Scanner input = new Scanner(new File("src/products.txt"));
        ArrayList<Product> products = new ArrayList<Product>();
        while (input.hasNextLine()) {
            if (input.nextLine().contains(category)) {
                // Splitting the products.txt file into price, manufacturer and and name then
                // using the product class to create a new product.
                String name = input.nextLine().split("=")[1].replaceAll("^\\s+", "");
                double price = Double.parseDouble(input.nextLine().split("=")[1].replaceAll("^\\s+", ""));
                String manufacturer = input.nextLine().split("=")[1].replaceAll("^\\s+", "");
                ImageView image = new ImageView(input.nextLine().split("=")[1].replaceAll("^\\s+", ""));
                products.add(new Product(name, manufacturer, price, image));
            }
        }
        return products;
    }

    @FXML
    private ScrollPane listContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this product list is used to display the products in the treeview in the brosw tab and is the main method used during shopping.
        HBox list = new HBox();
        list.setSpacing(30);
        TreeView<String> gaming = new TreeView<String>();
        TreeView<String> groceries = new TreeView<String>();
        TreeView<String> office = new TreeView<String>();
        // set the name of the root node
        gaming.setRoot(new TreeItem<String>("Gaming"));
        groceries.setRoot(new TreeItem<String>("Groceries"));
        office.setRoot(new TreeItem<String>("Office"));
        try{
            ArrayList<Product> gamingProducts = productByCategory("Gaming");
            ArrayList<Product> groceryProducts = productByCategory("Groceries");
            ArrayList<Product> officeProducts = productByCategory("Office");
            for (Product product : gamingProducts) {
                TreeItem<String> item = new TreeItem<String>("");
                item.setGraphic(createProductButton(product));
                gaming.getRoot().getChildren().add(item);
            }
            for (Product product : groceryProducts) {
                TreeItem<String> item = new TreeItem<String>("");
                item.setGraphic(createProductButton(product));
                groceries.getRoot().getChildren().add(item);
            }
            for (Product product : officeProducts) {
                TreeItem<String> item = new TreeItem<String>("");
                item.setGraphic(createProductButton(product));
                office.getRoot().getChildren().add(item);
            }
            list.getChildren().addAll(gaming, groceries, office);
            listContainer.setContent(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Button createProductButton(Product product) {
        VBox infoContainer = new VBox();
        infoContainer.setSpacing(10);
        infoContainer.getChildren().addAll(
            new Text(product.getName()),
            new Text(product.getManufacturer()),
            new Text("" + product.getPrice())
            
        );
        Button button = new Button();
        button.setGraphic(infoContainer);

        button.setOnAction(e ->
            addProductToCart(product)
        );
        return button;
    }
    

    private void addProductToCart(Product product) {
        // this method makes a popup to get the qunatity of the product the product from the user and asks if they would like to add or remove the product from the cart.
        Stage stage = new Stage();
        stage.setTitle("Quantity");

        VBox container = new VBox();
        container.setSpacing(10);
        TextField quantity = new TextField();
        Button submit = new Button("Add to cart");

        container.getChildren().addAll(new Label("Quantity"), quantity, submit, product.getImage());
        Scene scene = new Scene(container, 1600, 800);
        stage.setScene(scene);
        stage.show();
        submit.setOnAction(e -> {
            for (int i = 0; i < Integer.parseInt(quantity.getText()); i++) {
                cart.add(product);
            }
            stage.close();
        });
    }
    

    public void backtoMain(ActionEvent event) throws Exception {
        App.setRoot("mainmenu");
    }
}
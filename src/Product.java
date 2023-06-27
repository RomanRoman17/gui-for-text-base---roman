import javafx.scene.image.ImageView;

public class Product {
    //this class is used to create a product object which is used all throughout the code in many ways.
    private String name;
    private double price;
    private String manufacturer;
    private ImageView image;
    

    public Product(String name, String manufacturer, double price, ImageView image) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.image = image;
        
    }

    public ImageView getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }
}

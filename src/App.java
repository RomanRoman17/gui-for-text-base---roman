// MADE BY ROMAN KHATATBEH DECEMBER 2022 | 



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        stage = arg0;
        scene = new Scene(loadfxml("loginRomazon"));
        arg0.setScene(scene);
        arg0.show();

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }

    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadfxml(fxml));
    }

    public static Parent loadfxml(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
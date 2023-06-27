import java.io.FileWriter;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class signupController {

    @FXML
    private TextField userMake;
    @FXML
    private TextField passMake;
    @FXML
    private Button backtoLogin;
    @FXML
    private Button submitNewAcc;

    public void backtoLogin(ActionEvent event) throws Exception {
        //this method is used to move to the login page
        App.setRoot("loginRomazon");
    }

    public void submitNewAcc(ActionEvent event) throws Exception {
        //this method is used to create a new account and add it to the user.txt file
        String user = userMake.getText();
        String pass = passMake.getText();
        if (user.equals("") || pass.equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
        } else {
            FileWriter fw = new FileWriter("src/user.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("\n\n" + user + "\n" + pass);
            pw.close();
            App.setRoot("loginRomazon");
        }
    }

}
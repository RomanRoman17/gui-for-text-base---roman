import java.io.File;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class loginController{
    @FXML private TextField emailTextBox;
    @FXML private TextField passwordTextBox;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupMake;

    @FXML
    private void signupClick(ActionEvent e) throws Exception {
        //this method is used to move to the signup
        App.setRoot("signup");
    }

    @FXML
    private void login(ActionEvent e) throws Exception {
        //this method is used to check if the user has entered a correct username and passowrd combination from the user.txt file
        File file = new File("src/user.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals(emailTextBox.getText())) {
                String password = scanner.nextLine();
                if (password.equals(passwordTextBox.getText())) {
                    System.out.println("Login successful");
                    App.setRoot("mainmenu");
                } else {
                    System.out.println("Login failed");
                }
            }
        }
        scanner.close();
    }

}


package SCD2_2021_T3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;


import java.io.IOException;


public class Login extends Controller {

    public Login() {
    }

    @FXML 
    private Button check;
    @FXML 
    private Label error;
    @FXML 
    private TextField username;
    @FXML 
    private TextField token; 

    public void login(ActionEvent event) throws IOException {
        boolean success = user.login(username.getText(), token.getText());

        if (!success) {
            error.setText("Error: " + user.getLastStatus());
        } else {
            // Switch windows
            changeScene("/control.fxml");
        }
    }

    public void register(ActionEvent event) throws IOException {
        boolean success = user.newUser(username.getText());

        if (!success) {
            error.setText("Error: " + user.getLastStatus());
        } else {
            // Switch windows
            changeScene("/control.fxml");
        }
    }

    public void checkServer(ActionEvent event) throws IOException {
        boolean success = user.checkStatus();

        if (success) {
            check.setText("Server Online!");
        } else {
            check.setText("Server Offline!");
        }
    }
}
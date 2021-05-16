package SCD2_2021_T3;

import SCD2_2021_T3.containers.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
// import javafx.scene.container.*;
import javafx.scene.control.*;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import org.json.simple.JSONObject;

import java.io.IOException;


public class Control extends Controller{

    @FXML private Button logout;
    @FXML private TextArea details;
    @FXML private Label display;
    @FXML private Label error;
    @FXML private Label credits;

    // Loans

    @FXML private Button availableLoans;
    @FXML private Button loanStart;
    @FXML private Button currentLoans;
    @FXML private Button loanPay;
    @FXML private ComboBox availableLoansList;
    @FXML private ComboBox currentLoansList;

    // Ships

    @FXML private Button availableShips;
    @FXML private Button shipBuy;
    @FXML private Button ownedShips;
    @FXML private Button fuelBuy;
    @FXML private ComboBox availableShipsList;
    @FXML private ComboBox fuelShip;
    @FXML private TextField fuelAmount;

    // Marketplace

    @FXML private Button marketplace;
    @FXML private Button goodsBuy;
    @FXML private Button goodsSell;
    @FXML private ComboBox buyFromShip;
    @FXML private TextField buyType;
    @FXML private TextField sellType;
    @FXML private TextField buyAmount;
    @FXML private TextField sellAmount;

    // Flight Plan
    
    @FXML private Button nearby;
    @FXML private Button flightplanStart;
    @FXML private Button flightplanView;
    @FXML private TextField destination;
    @FXML private ComboBox travelShip;
    

    public Control() {
    }

    public void init() {
        details.setText("Username: " + user.getUsername() + "\nToken: " + user.getToken());
        details.setEditable(false);
        // display.setEditable(false);
        updateCredits();
        error.setText("");
    }

    public void updateCredits() {
        credits.setText("Credits: " + String.valueOf(user.getCredits()));
    }


    public void logout(ActionEvent event) throws IOException {
        this.user = new Interface(new OnlineAPI());
        changeScene("/login.fxml");
    }
    
    public void showLoans(ActionEvent event) throws IOException {
        String message = user.availableLoans();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        // System.out.println(user.availableLoans());

        for (String s : user.getAvailableLoans()) {
            if (!availableLoansList.getItems().contains(s)) {
                availableLoansList.getItems().add(s);
            }
        }
    }

    public void startLoan(ActionEvent event) throws IOException {
        String message = user.startLoan((String) availableLoansList.getValue());

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        for (String[] s : user.getCurrentLoans()) {
            currentLoansList.getItems().add(s[0]);
        }

        updateCredits();
    }

    public void viewCurrentLoans(ActionEvent event) throws IOException {
        String message = user.currentLoans();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }
    }

    public void payLoan(ActionEvent event) throws IOException {
        String message = user.payLoan((String) currentLoansList.getValue());

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        updateCredits();
    } 

    public void showAvailableShips(ActionEvent event) throws IOException {
        String message = user.availableShips();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        // System.out.println(user.availableLoans());

        for (String s : user.getAvailableShips()) {
            if (!availableShipsList.getItems().contains(s)) {
                availableShipsList.getItems().add(s);
            }
        }
    }

    public void buyShip(ActionEvent event) throws IOException {
        if ((String) availableShipsList.getValue() == null) {
            error.setText("Error: Please select a value");
            return;
        }

        String message = user.buyShip((String) availableShipsList.getValue());

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        for (String[] s : user.getCurrentShips()) {
            fuelShip.getItems().add(s[0]);
            buyFromShip.getItems().add(s[0]);
            travelShip.getItems().add(s[0]);
        }

        updateCredits();
    }

    public void seeOwnedShips(ActionEvent event) throws IOException {
        String message = user.currentShips();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }
    }  
    
    public void buyFuel(ActionEvent event) throws IOException {
        int amount;

        try {
            amount = Integer.parseInt(fuelAmount.getText());
        } catch (NumberFormatException e) {
            error.setText("Error: Amount entered should be an integer");
            return;
        }

        String id = null;
        for (String[] s : user.getCurrentShips()) {
            if (s[0] == fuelShip.getValue()) {
                id = s[1];
            }
        }

        String message = user.buyFuel(amount, id);

        display.setText(message);

        updateCredits();

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        } 
    }  

    public void viewMarketplace(ActionEvent event) throws IOException {
        String shipDetails = (String) buyFromShip.getValue();
        if (shipDetails == null) { 
            error.setText("Error: Please select a ship to view its location");
            return;
        }

        String[] spl = shipDetails.split(":");

        

        String message = user.viewMarketplaceDetails(spl[1]);

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }

        
    }
    public void buyGoods(ActionEvent event) throws IOException {
        int amount;

        try {
            amount = Integer.parseInt(buyAmount.getText());
        } catch (NumberFormatException e) {
            error.setText("Error: Amount entered should be an integer");
            return;
        }

        String id = null;
        for (String[] s : user.getCurrentShips()) {
            if (s[0] == buyFromShip.getValue()) {
                id = s[1];
            }
        }

        String message = user.buyGoods(buyType.getText(), amount, id);

        display.setText(message);

        updateCredits();

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        } 
    }
    public void sellGoods(ActionEvent event) throws IOException {
        int amount;

        try {
            amount = Integer.parseInt(sellAmount.getText());
        } catch (NumberFormatException e) {
            error.setText("Error: Amount entered should be an integer");
            return;
        }

        String id = null;
        for (String[] s : user.getCurrentShips()) {
            if (s[0] == buyFromShip.getValue()) {
                id = s[1];
            }
        }

        String message = user.sellGoods(buyType.getText(), amount, id);

        display.setText(message);

        updateCredits();

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        } 
    }

    public void listNearbyLocations(ActionEvent event) throws IOException {
        String message = user.nearbyLocations();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }
    }
    public void startFlightPlan(ActionEvent event) throws IOException {
        String id = null;
        for (String[] s : user.getCurrentShips()) {
            if (s[0] == travelShip.getValue()) {
                id = s[1];
            }
        }

        String message = user.startFlightPlan(id, destination.getText());

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }
    }
    public void viewFlightPlan(ActionEvent event) throws IOException {
        String message = user.viewFlightPlan();

        display.setText(message);

        if (message == "") {
            error.setText("Error: " + user.getLastStatus());
            return;
        }
    }

}
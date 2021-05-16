package SCD2_2021_T3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.*;



public class App extends Application {

    public static void main(String[] args) {
        launch(args);
        /**
        // APIFacade api = new OnlineAPI();

        // Response r;

        // String token = "43791c9d-6638-4c43-b8c9-9f86e91b119b";
        // String username = "backpackfrog4";
        // String shipID = "ckopagwef1881281ds60qjsmmw6";

        // Interface user = new Interface(api);
        
        // boolean result = user.newUser("backpackfrog1231239");
        // System.out.println(String.valueOf(result));
        // System.out.println(user);

        // System.out.println(user.checkStatus());

        // r = api.checkStatus();
        // r = api.claimUsername("backpackfrog8");
        // System.out.println(r.message());

        // r = api.login("43791c9d-6638-4c43-b8c9-9f86e91b119b", "backpackfrog4");

        // System.out.println(r.message());

        // r = api.getLoans(token);
        // System.out.println(r.message());

        // r = api.takeLoan(token, username, "STARTUP");
        // System.out.println(r.message());
        
        // r = api.seeActiveLoans(token, username);
        // System.out.println(r.message());

        // r = api.getShips(token, "MK-I");
        // System.out.println(r.message());

        // r = api.buyShip(token, username, "OE-PM-TR", "JW-MK-I");
        // System.out.println(r.message());

        // r = api.seeOwnedShips(token, username);
        // System.out.println(r.message());
        // r = api.shipInfo(token, username, "ckopagwef1881281ds60qjsmmw6");
        // System.out.println(r.message());

        // r = api.purchaseFuel(token, username, shipID, 20);
        // System.out.println(r.message());
        
        // r = api.viewMarketplaceDetails(token, "OE-PM-TR");
        // System.out.println(r.message());

        // r = api.purchaseGoods(token, username, shipID, "METALS", 30);
        // System.out.println(r.message());

        // r = api.getNearbyLocations(token, "OE", "PLANET");
        // System.out.println(r.message());

        // r = api.createFlightPlan(token, username, shipID, "OE-PM");
        // System.out.println(r.message());

        // r = api.viewFlightPlan(token, username, "ckopbobv99493581ds63dyb28qu");
        // System.out.println(r.message());

        // r = api.sellGoods(token, username, shipID, "METALS", 30);
        // System.out.println(r.message());

        // r = api.payLoan(token, username, "ckopa8fw8473531ds6r25v4bta");
        // System.out.println(r.message());

        */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Space Traders");

        List<String> args = getParameters().getRaw();


        APIFacade api = null;

        // System.out.println(args.get(0));

        if (args.size() == 0) {
            api = new OnlineAPI();
        }

        if (args.get(0).equals("offline")) {
            System.out.println("offline selected");
            api = new OfflineAPI();
        } else if (args.get(0).equals("online")) {
            System.out.println("online selected");

            api = new OnlineAPI();
        }
        Interface user = new Interface(api);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/login.fxml"));
        VBox vbox = (VBox) loader.load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        Login loginPage = loader.getController();
        loginPage.setUser(user);
        loginPage.setStage(primaryStage);

        primaryStage.show();
    }
}

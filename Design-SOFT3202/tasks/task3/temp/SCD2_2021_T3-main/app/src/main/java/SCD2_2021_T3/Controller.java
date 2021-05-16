package SCD2_2021_T3;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


import java.io.IOException;



public abstract class Controller extends VBox {

    protected Interface user;
    protected Stage stg;

    // public void setUser(Interface user) {
    //     this.user = user;
    // }

    public void setUser(Interface user) {
        this.user = user;
    }

    public void setStage(Stage stg) {
        this.stg = stg;
    }

    public void init() {
        return;
    }

    public void changeScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

        Stage stage = new Stage();
        stage.setTitle("Space Traders");
        stage.setScene(new Scene(loader.load()));

        Controller control = loader.getController();
        control.setUser(this.user);
        control.setStage(stage);
        control.init();

        stg.close();

        stage.show();
    }
}
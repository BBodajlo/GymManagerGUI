package com.example.gymmanagergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Control;
import java.io.IOException;
/**
 * This class launches and displays the gym manager application; it's feature includes adding members, supervising
 * valid member information, management of the members in the database, check members into fitness classes,
 * load members, and load class schedules.
 * The application can print out sorted members in the database by name, location,
 * and expiration dates.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class GymManagerMain extends Application {

    public static Stage stg;

    /**
     * Start the display of the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Parent root = FXMLLoader.load(GymManagerMain.class.getResource("GymManagerView" +
                ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setResizable(false);
        stage.setTitle("Gym Manager");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}


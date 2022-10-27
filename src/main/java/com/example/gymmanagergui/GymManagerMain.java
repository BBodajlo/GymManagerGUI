package com.example.gymmanagergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GymManagerMain extends Application {

    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
       // Parent root = FXMLLoader.load(GymManagerMain.class.getResource("GymManagerView" +
       //         ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 395, 650);
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


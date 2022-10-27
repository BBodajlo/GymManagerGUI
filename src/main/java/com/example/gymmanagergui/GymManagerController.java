package com.example.gymmanagergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.gymmanagergui.GymManagerMain.stg;


public class GymManagerController {

    GymManager gymManager = new GymManager();


    @FXML
    private Label welcomeText;
    @FXML
    private Button addMenuButton;

    public void memberAddMenu(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(25, 25, 25, 25));

        TextField tf1 = new TextField();
        tf1.setPromptText("Enter the first name");
        tf1.setFocusTraversable(false);
        TextField tf2 = new TextField();
        tf2.setPromptText("Enter the last name");
        tf2.setFocusTraversable(false);
        TextField tf3 = new TextField();
        tf3.setPromptText("Enter the date of birth");
        tf3.setFocusTraversable(false);
        TextField tf4 = new TextField();
        tf4.setPromptText("Enter the location");
        tf4.setFocusTraversable(false);

        TextField result = new TextField();
        result.setEditable(false);
        result.setPrefWidth(250);
        result.setPrefHeight(200);

        Label l1 = new Label("First Name:");
        Label l2 = new Label("last Name:");
        Label l3 = new Label("Date of Birth:");
        Label l4 = new Label("Location:");

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Standard");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Family");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Premium");
        rb3.setToggleGroup(group);

        pane.add(l1, 0, 1);
        pane.add(tf1, 1, 1);
        pane.add(l2, 0, 2);
        pane.add(tf2, 1, 2);
        pane.add(l3, 0, 3);
        pane.add(tf3, 1, 3);
        pane.add(l4, 0, 4);
        pane.add(tf4, 1, 4);
        pane.add(rb1, 0, 5);
        pane.add(rb2, 0, 6);
        pane.add(rb3, 0, 7);
        pane.add(result, 1, 15);


        Button genericButton = new Button("Add");
        pane.add(genericButton, 0, 10);

        Scene scene = new Scene(pane, 375, 500);
        stage.setTitle("Add Member");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        genericButton.setOnAction(e -> {
            String tier;

            if(rb1.isSelected()){
                tier = "A";
            } else if (rb2.isSelected()) {
                tier = "AF";
            }
            else{
                tier = "AP";
            }
            //*gymManager.addTieredMember( tf1.getText(), tf2.getText(), tf3.getText(),tier, tf4.getText());

        });
    }

    public void removeMemberMenu(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(25,25,25,25));

        TextField tf1 = new TextField();
        tf1.setPromptText("Enter the first name");
        tf1.setFocusTraversable(false);
        TextField tf2 = new TextField();
        tf2.setPromptText("Enter the last name");
        tf2.setFocusTraversable(false);
        TextField tf3 = new TextField();
        tf3.setPromptText("Enter the date of birth");
        tf3.setFocusTraversable(false);

        TextField result = new TextField();
        result.setEditable(false);
        result.setPrefWidth(270);
        result.setPrefHeight(250);

        Label l1 = new Label("First Name:");
        Label l2 = new Label("last Name:");
        Label l3 = new Label("Date of Birth:");

        pane.add(l1, 0, 1);
        pane.add(tf1, 1, 1);
        pane.add(l2, 0, 2);
        pane.add(tf2, 1, 2);
        pane.add(l3, 0, 3);
        pane.add(tf3, 1, 3);
        pane.add(result, 1, 20);


        Button genericButton = new Button("Remove");
        pane.add(genericButton, 0, 9);

        Scene scene = new Scene(pane, 375, 550);
        stage.setTitle("Remove Member");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void closeProgram(ActionEvent event) throws IOException {
        stg.close();
    }

}


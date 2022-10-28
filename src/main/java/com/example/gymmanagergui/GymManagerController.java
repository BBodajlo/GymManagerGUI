package com.example.gymmanagergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import java.io.IOException;

import static com.example.gymmanagergui.GymManagerMain.stg;


public class GymManagerController {

    private MemberDatabase memberList;

    private ClassSchedule fitnessClassDatabase;

    GymManager gymManager = new GymManager();
    String holder[] = new String[6];

    @FXML
    private Label welcomeText;
    @FXML
    private StackPane menuScreens;
    @FXML
    private Pane menuScreenButtons;
    @FXML
    private BorderPane mainMenu;
    @FXML
    private Button menuLoadMembers;
//Start Screen
    @FXML
    private Pane startScreen;
    @FXML
    private Button startButton;
//Add member menu variables----------------------------

    @FXML
    private BorderPane memberAddMenu;
    @FXML
    private Button addMenuButton;
    @FXML
    private Button addMenuBackButton;
    @FXML
    private TextField addMenuFirstName;
    @FXML
    private TextField addMenuLastName;
    @FXML
    private TextField addMenuLocation;
    @FXML
    private DatePicker addMenuDOB;
    @FXML
    private RadioButton addMemberStandard;
    @FXML
    private RadioButton addMemberFamily;
    @FXML
    private RadioButton addMemberPremium;
    @FXML
    private Button addMemberButton;
    @FXML
    private TextArea addMemberTextArea;


//--------------------------------------------


//Load Member Area
    @FXML
    private SplitPane loadMembersScreen;

    @FXML
    public void initialize()
    {
        memberList = new MemberDatabase();
    }

    @FXML
    public void startGymManager(){
        startScreen.setVisible(false);
        menuScreenButtons.setVisible(true);
        menuScreens.setVisible(true);
    }

//Main Menu ----------------------------------------------------------------------------
    @FXML
    public void memberAddMenu(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        memberAddMenu.setVisible(true);

    }
    @FXML
    public void goToLoadMembers(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadMembersScreen.setVisible(true);

    }
//Add Member Menu------------------------------------------------------------------------
    public void addMember(MouseEvent event) throws IOException{
        Member newPerson = null;
        String firstName = null;
        String lastName = null;
        Date dob = null;
        Location location = null;
        try{
            firstName = addMenuFirstName.getText();
            lastName = addMenuLastName.getText();
        }
        catch(NullPointerException e)
        {
            addMemberTextArea.appendText("A field was left blank\n");
            return;
        }
        try{
            dob = new Date(addMenuDOB.getValue().toString());
        }
        catch(NullPointerException e)
        {
            addMemberTextArea.appendText("Date of Birth was invalid\n");
            return;
        }
        try{
            location = Location.setLocation(addMenuLocation.getText());
        }
        catch(NullPointerException e){
            addMemberTextArea.appendText("Location was invalid\n");
            return;

        }


        try{
            if(addMemberStandard.isSelected()) {
                newPerson = new Member(firstName, lastName, dob, location);
            }
            else if(addMemberFamily.isSelected()) {
                newPerson = new Family(firstName, lastName, dob, location);
            }
            else if(addMemberPremium.isSelected()) {
                newPerson = new Premium(firstName, lastName, dob, location);
            }
            if(newPerson == null)
                throw new NullPointerException();
        }
        catch (NullPointerException e)
        {
            addMemberTextArea.appendText("Membership type was not selected\n");
            return;
        }

        if(dob.isValid() && dob.isAdult() && memberList.find(newPerson) == -1){
            memberList.add(newPerson);
            addMemberTextArea.appendText(String.format("%s %s has been added\n",
                    firstName, lastName));
        }
        else if(!dob.isPastButNotTodayOrPresent()) {
            addMemberTextArea.appendText("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " cannot be today or a future date!\n");
        }

        else if(!dob.isAdult()) {
            addMemberTextArea.appendText("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " must be 18 or older to join!\n");
        }
        else if(memberList.find(newPerson) != -1) {
            addMemberTextArea.appendText(String.format("%s %s is already in " +
                    "the data base\n", firstName, lastName));
        }
        else if(!dob.isValid()) {
            addMemberTextArea.appendText(("DOB " + dob.getDate() + " Invalid " +
                    "calendar date!\n"));
        }



    }

    public void enterStringMessage(MouseEvent event) throws IOException{
        addMemberTextArea.appendText("Enter your first name\n");

    }
    @FXML
    public void addBackToMenu(MouseEvent event) throws IOException {
    menuScreenButtons.setVisible(true);
    memberAddMenu.setVisible(false);
    addMenuLastName.clear();
    addMenuFirstName.clear();
    addMenuLocation.clear();
    addMenuDOB.getEditor().clear();
    addMemberStandard.setSelected(false);
    addMemberFamily.setSelected(false);
    addMemberPremium.setSelected(false);


}
//----------------------------------------------------------------------------------------

//Load Member Screen-------------------------------------------------------------------------
    @FXML
    public void loadMemBackToMenu(MouseEvent e){
    menuScreenButtons.setVisible(true);
    loadMembersScreen.setVisible(false);

}
    public void loadMembers(MouseEvent event){

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


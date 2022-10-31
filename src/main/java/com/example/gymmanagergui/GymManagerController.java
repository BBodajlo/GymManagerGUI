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

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

import static com.example.gymmanagergui.GymManagerMain.stg;


public class GymManagerController {

    private MemberDatabase memberList;

    private ClassSchedule fitnessClassDatabase;

    GymManager gymManager = new GymManager();
    private boolean membersListLoaded;
    private boolean classesListLoaded;

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
    @FXML
    private Button menuLoadClasses;
    @FXML
    private Button removeMemberButton;
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

//Remove Member Area
    @FXML
    private BorderPane memberRemoveMenu;

    @FXML
    private TextField removeMenuFirstName;
    @FXML
    private TextField removeMenuLastName;
    @FXML
    private DatePicker removeMenuDOB;
    @FXML
    private Button removeButton;
    @FXML
    private TextArea removeMemberTextArea;
    @FXML
    private Button removeMenuBackButton;

//--------------------------------------------------------------------------

//Load Member Area
    @FXML
    private SplitPane loadMembersScreen;
    @FXML
    private Button loadMemberButton;
    @FXML
    private TextArea loadMemberTextArea;
    @FXML
    private Button loadMembersBackButton;
    //------------------------------------------------------

    //Load Classes Area
    @FXML
    private SplitPane loadClassesScreen;
    @FXML
    private Button loadClassesButton;
    @FXML
    private TextArea loadClassesTextArea;
    @FXML
    private Button loadClassesBackButton;

// ----------------------------------------------------------------------
    @FXML
    public void initialize()
    {
        memberList = new MemberDatabase();
        fitnessClassDatabase = new ClassSchedule();
        membersListLoaded = false;
        classesListLoaded = false;
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
    public void goToRemoveMenu(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        memberRemoveMenu.setVisible(true);

    }
    @FXML
    public void goToLoadClasses(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadClassesScreen.setVisible(true);

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
    addMemberTextArea.clear();


}
//----------------------------------------------------------------------------------------

    //Remove Member Code ---------------------------------------------------------------------------
    @FXML
    public void removeBackToMenu(MouseEvent e){
        menuScreenButtons.setVisible(true);
        removeMemberTextArea.clear();
        memberRemoveMenu.setVisible(false);
        removeMenuFirstName.clear();
        removeMenuLastName.clear();
        removeMenuDOB.getEditor().clear();



    }
    @FXML
    public void removeMember(MouseEvent event) throws IOException {
        Member personToRemove = null;
        String firstName = null;
        String lastName = null;
        Date dob = null;

        try{
            firstName = removeMenuFirstName.getText();
            lastName = removeMenuLastName.getText();
        }
        catch(NullPointerException e)
        {
            removeMemberTextArea.appendText("A field was left blank\n");
            return;
        }
        try{
            dob = new Date(removeMenuDOB.getValue().toString());
        }
        catch(NullPointerException e)
        {
            removeMemberTextArea.appendText("Date of Birth was invalid\n");
            return;
        }
        personToRemove = new Member(firstName, lastName,dob);
        boolean isInList = true;
        for(Member m: memberList.getMlist())
        {
            if(m != null && personToRemove.equals(m))
            {
                memberList.remove(m);
                removeMemberTextArea.appendText(firstName + " " + lastName + " " +
                        "removed.\n");
                isInList = false;
                break;
            }
        }
        if(isInList) {
            removeMemberTextArea.appendText(firstName + " " + lastName + " is " +
                    "not in the database\n");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------
//Load Member Screen-------------------------------------------------------------------------
    @FXML
    public void loadMemBackToMenu(MouseEvent e){
    menuScreenButtons.setVisible(true);
    loadMemberTextArea.clear();
    loadMembersScreen.setVisible(false);


}
    public void loadMembers(MouseEvent event) throws IOException{

        FileChooser file = new FileChooser();
        file.setTitle("Select File");
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = file.showOpenDialog(stage);

        if(!membersListLoaded) {
            try {
                memberList.addMember(sourceFile);
                loadMemberTextArea.appendText("------------------------------------------------------------\n");
                for (Member member : memberList.getMlist()) {
                    if (member != null)
                        loadMemberTextArea.appendText(member.toString()+"\n");
                    loadMemberTextArea.appendText("------------------------------------------------------------\n");
                }

                loadMemberTextArea.appendText("Member list loaded successfully!\n");
                membersListLoaded = true;
            } catch (FileNotFoundException e) {
                loadMemberTextArea.appendText("No file was found");
                return;
            }
            catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e)
            {
                loadMemberTextArea.appendText("File not formatted correctly\n");
            }

        }
        else {
            loadMemberTextArea.appendText("Member list already loaded!\n");
        }


    }

    //Load Classes Code
    @FXML
    public void loadClassesBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadClassesTextArea.clear();
        loadClassesScreen.setVisible(false);
    }
    public void loadClasses(MouseEvent event) throws IOException{

        FileChooser file = new FileChooser();
        file.setTitle("Select File");
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = file.showOpenDialog(stage);

        if(!classesListLoaded) {
            try {
                fitnessClassDatabase.loadClasses(sourceFile);
                loadClassesTextArea.appendText(fitnessClassDatabase.displaySchedule()+"\n");
                loadClassesTextArea.appendText("Class list loaded successfully!\n");
                classesListLoaded = true;
            } catch (FileNotFoundException e) {
                loadClassesTextArea.appendText("No file was found");
                return;
            }
            catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e)
            {
                loadClassesTextArea.appendText("File not formatted correctly\n");
            }

        }
        else {
            loadClassesTextArea.appendText("Class list already loaded!\n");
        }


    }

//------------------------------------------------------------------------------------------------






    public void closeProgram(ActionEvent event) throws IOException {
        stg.close();
    }

}


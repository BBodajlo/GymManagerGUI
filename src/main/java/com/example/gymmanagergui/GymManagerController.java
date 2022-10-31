package com.example.gymmanagergui;

import javafx.collections.FXCollections;
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
import java.util.Arrays;

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
    @FXML
    private Button menuInfoButton;
    @FXML
    private Button menuRegularCheckInButton;
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

//Check In Regular Area

    @FXML
    private BorderPane checkInScreen;
    @FXML
    private TextField checkInFirstName;
    @FXML
    private TextField checkInLastName;
    @FXML
    private ComboBox locationComboBox;
    @FXML
    private ComboBox instructorComboBox;
    @FXML
    private RadioButton checkInRegCardio;
    @FXML
    private RadioButton checkInRegPilates;
    @FXML
    private RadioButton checkInRegSpinning;
    @FXML
    private Button checkInBackButton;
    @FXML
    private DatePicker checkInDOB;
    @FXML
    private Button checkInButton;
    @FXML
    private TextArea checkInTextArea;






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

//Information Member Area---------------------------------------------------------

    @FXML
    private ComboBox loadMenuMemInfoCombo;
    @FXML
    private final String[] printMethods = {"Print", "Print By Fee", "Print By County", "Print By Name",
            "Print By Expiration"};
    @FXML
    private BorderPane loadingMenu;
    @FXML
    private Tab loadMenuMemInfoTab;
    @FXML
    private Tab loadMenuClassInfoTab;
    @FXML
    private TextArea loadMenuMemTextArea;


//--------------------------------------------------------------------

//Information Class Area-----------------------------------

    @FXML
    private ComboBox loadMenuClassInfoCombo;

    @FXML
    private final String[] printClasses = {"Schedule"};
    @FXML
    private TextArea loadMenuClassTextArea;
    @FXML
    private Button informationClassBackButton;



//-----------------------------------------------------------------------------------


    @FXML
    public void initialize()
    {
        memberList = new MemberDatabase();
        fitnessClassDatabase = new ClassSchedule();
        membersListLoaded = false;
        classesListLoaded = false;
       loadMenuMemInfoCombo.setItems(FXCollections.observableArrayList(printMethods));
       loadMenuClassInfoCombo.setItems(FXCollections.observableArrayList(printClasses));
       //locationComboBox.setItems(FXCollections.observableArrayList(Location.values()));
       instructorComboBox.setItems(FXCollections.observableArrayList(Instructor.values()));

       // System.out.println(Location.values());
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
    public void goToCheckIn(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        checkInScreen.setVisible(true);

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
    @FXML
    public void goToInfo(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadingMenu.setVisible(true);

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

//Check In Code----------------
    @FXML
    public void checkInToMenu(MouseEvent e){
        menuScreenButtons.setVisible(true);
        checkInScreen.setVisible(false);

    }

    @FXML
    public void checkInInstructorComboBoxMethod(ActionEvent e)
    {

        if(instructorComboBox.getValue().equals(Instructor.EMMA))
        {
            locationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.EMMA)));
        }
        if(instructorComboBox.getValue().equals(Instructor.DAVIS))
        {
            locationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.DAVIS)));
        }
        if(instructorComboBox.getValue().equals(Instructor.DENISE))
        {
            locationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.DENISE)));
        }
        if(instructorComboBox.getValue().equals(Instructor.JENNIFER))
        {
            locationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.JENNIFER)));
        }
        if(instructorComboBox.getValue().equals(Instructor.KIM))
        {
            locationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.KIM)));
        }
    }


    @FXML
    public void checkInMember(MouseEvent a)
    {
        String fitnessClassType = null;
        String instructor = null;
        String location =null;
        String firstName = null;
        String lastName =null;
        Date dob = null;
        String[] person = new String[7];
        try{
            firstName = checkInFirstName.getText();
            lastName = checkInLastName.getText();
            person[4] = firstName;
            person[5] = lastName;
        }
        catch(NullPointerException e)
        {
            checkInTextArea.appendText("Name fields are incomplete");
        }

        try{
            dob = new Date(checkInDOB.getValue().toString());
            person[6] = checkInDOB.getValue().toString();
        }
        catch(NullPointerException  | ArrayIndexOutOfBoundsException e)
        {
            checkInTextArea.appendText("Date field is incorrect");
        }
        try{
            instructor = instructorComboBox.getValue().toString();
            location = locationComboBox.getValue().toString();
            person[2] = instructor;
            person[3]= location;
        }
        catch(NullPointerException e)
        {
            checkInTextArea.appendText("Instructor or Location field is blank");
        }
        try{
            if(checkInRegPilates.isSelected())
            {
                fitnessClassType = "Pilates";
                person[1] = fitnessClassType;
            }
            else if(checkInRegCardio.isSelected())
            {
                fitnessClassType = "Cardio";
                person[1] = fitnessClassType;
            }
            else if(checkInRegSpinning.isSelected())
            {
                fitnessClassType = "Spinning";
                person[1] = fitnessClassType;
            }
            else {
                throw new NullPointerException();
            }

        }
        catch(NullPointerException e)
        {
            checkInTextArea.appendText("No class type was selected");
        }

        checkInTextArea.appendText(fitnessClassDatabase.checkIn(person, false, memberList, fitnessClassDatabase));

    }

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

//Information Member Text Code
// -------------------------------------------------------------------------
    @FXML
    public void informationMemBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadingMenu.setVisible(false);
        loadMenuMemTextArea.clear();
    }


    @FXML
    public void infoMenuPrintMethod(ActionEvent e)
    {
       // {"Print", "Print By Fee", "Print By County", "Print By Name",
        //        "Print By Expiration"};
        //loadMenuMemTextArea.appendText(loadMenuMemInfoCombo.getValue().toString()+ "\n");
        if(loadMenuMemInfoCombo.getValue().toString().equals("Print"))
        {
            loadMenuMemTextArea.clear();
            loadMenuMemTextArea.appendText("\t--Printing by Order Added--");
            loadMenuMemTextArea.appendText(memberList.print());
        }
        if(loadMenuMemInfoCombo.getValue().toString().equals("Print By Fee"))
        {
            loadMenuMemTextArea.clear();
            loadMenuMemTextArea.appendText("\t--Printing by Fee--");
            loadMenuMemTextArea.appendText(memberList.printWFee());
        }
        if(loadMenuMemInfoCombo.getValue().toString().equals("Print By County"))
        {
            loadMenuMemTextArea.clear();
            loadMenuMemTextArea.appendText("\t--Printing by County--");
            loadMenuMemTextArea.appendText(memberList.printByCounty());
        }
        if(loadMenuMemInfoCombo.getValue().toString().equals("Print By Name"))
        {
            loadMenuMemTextArea.clear();
            loadMenuMemTextArea.appendText("\t--Printing by Name--");
            loadMenuMemTextArea.appendText(memberList.printByName());
        }
        if(loadMenuMemInfoCombo.getValue().toString().equals("Print By Expiration"))
        {
            loadMenuMemTextArea.clear();
            loadMenuMemTextArea.appendText("\t--Printing by Expiration--");
            loadMenuMemTextArea.appendText(memberList.printByExpiration());
        }
    }


//-----------------------------------------------------------------------------------------------

//
    @FXML
    public void informationClassBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadingMenu.setVisible(false);
        loadMenuClassTextArea.clear();
    }

    @FXML
    public void infoMenuClassMethod(ActionEvent e)
    {
        if(loadMenuClassInfoCombo.getValue().toString().equals("Schedule"))
        {
            loadMenuClassTextArea.clear();
            loadMenuClassTextArea.appendText(fitnessClassDatabase.displaySchedule());
        }
    }




    public void closeProgram(ActionEvent event) throws IOException {
        stg.close();
    }

}


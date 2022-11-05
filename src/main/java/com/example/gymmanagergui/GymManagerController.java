package com.example.gymmanagergui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import static com.example.gymmanagergui.GymManagerMain.stg;

/**
 * GymManagerController controls of the graphic user interface
 * for the gym manager application. It manages the action of the buttons and layouts of
 * the application. There are menus such as main, member, check in, drop, loading, and information
 * that are being managed.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class GymManagerController {

    private MemberDatabase memberList;

    private ClassSchedule fitnessClassDatabase;

    private boolean membersListLoaded;
    private boolean classesListLoaded;

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
    private ComboBox<Location> addMenuLocationCombo;
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
// ------------------------------------------------------------------------------
    //Drop In Regular Area

    @FXML
    private BorderPane dropOutScreen;
    @FXML
    private TextField dropOutFirstName;
    @FXML
    private TextField dropOutLastName;
    @FXML
    private ComboBox dropLocationComboBox;
    @FXML
    private ComboBox dropInstructorComboBox;
    @FXML
    private RadioButton dropOutRegCardio;
    @FXML
    private RadioButton dropOutRegPilates;
    @FXML
    private RadioButton dropOutRegSpinning;
    @FXML
    private Button dropOutBackButton;
    @FXML
    private DatePicker dropOutDOB;
    @FXML
    private Button dropOutButton;
    @FXML
    private TextArea dropOutTextArea;
    @FXML
    private CheckBox dropOutGuestButton;





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
    @FXML
    private CheckBox checkInGuestButton;

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
    private Button loadMenuLoadScheduleButton;

    @FXML
    private final String[] printClasses = {"Schedule"};
    @FXML
    private TextArea loadMenuClassTextArea;
    @FXML
    private Button informationClassBackButton;



//-----------------------------------------------------------------------------------

    /**
     * Initializes the application, by creating empty databases for members and fitness classes.
     */
    @FXML
    public void initialize()
    {
        memberList = new MemberDatabase();
        fitnessClassDatabase = new ClassSchedule();
        membersListLoaded = false;
        classesListLoaded = false;
        addMenuLocationCombo.setItems(FXCollections.observableArrayList(Location.values()));
       loadMenuMemInfoCombo.setItems(FXCollections.observableArrayList(printMethods));
       dropInstructorComboBox.setItems(FXCollections.observableArrayList(Instructor.values()));
       //locationComboBox.setItems(FXCollections.observableArrayList(Location.values()));
       instructorComboBox.setItems(FXCollections.observableArrayList(Instructor.values()));


       // System.out.println(Location.values());
    }

    /**
     * Used by the "Start Gym Manager" button to show the main menu.
     */
    @FXML
    public void startGymManager(){
        startScreen.setVisible(false);
        menuScreenButtons.setVisible(true);
        menuScreens.setVisible(true);
    }


//Main Menu ----------------------------------------------------------------------------
    /**
     * Used by the "Member's add" button to show the add member menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void memberAddMenu(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        memberAddMenu.setVisible(true);

    }
    /**
     * Used by the "Member's Remove" button to show the remove member menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToRemoveMenu(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        memberRemoveMenu.setVisible(true);

    }
    /**
     * Used by the "Check in" button to show the check in menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToCheckIn(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        checkInScreen.setVisible(true);
        checkInTextArea.appendText("Please select an instructor first to determine valid " +
                "locations\n");

    }
    /**
     * Used by the "Loading's Classes" button to show the load class menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToDropOut(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        dropOutScreen.setVisible(true);
        dropOutTextArea.appendText("Please select an instructor first to determine valid " +
                "locations\n");

    }
    /**
     * Used by the "Load's Classes" button to show the load classes menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToLoadClasses(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadClassesScreen.setVisible(true);

    }
    /**
     * Used by the "Loading's Member" button to show the load member menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToLoadMembers(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadMembersScreen.setVisible(true);

    }
    /**
     * Used by the "Info Page" button to show the info page menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void goToInfo(MouseEvent event) throws IOException {

        menuScreenButtons.setVisible(false);
        loadingMenu.setVisible(true);

    }
//Add Member Menu------------------------------------------------------------------------
    /**
     * Invoked by the "Add" button in the add member menu; which adds a member
     * based on the information provided in the text fields. Information includes
     * first name, last name, location, date of birth, and type of membership.
     * @param event When the user clicks on the button.
     */
    public void addMember(MouseEvent event) throws Exception {
        Member newPerson = null;
        String firstName = null;
        String lastName = null;
        Date dob = null;
        Location location = null;
        try{
            firstName = addMenuFirstName.getText();
            lastName = addMenuLastName.getText();
            if(addMenuFirstName.getText().equals("") || addMenuLastName.getText().equals(""))
            {
                throw new NullPointerException();
            }
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
            location = addMenuLocationCombo.getSelectionModel().getSelectedItem();
            if(location == null)
                throw new NullPointerException();
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

        if(!checkIsChar(firstName))
        {
            addMemberTextArea.appendText("First name cannot consist of numbers\n");
            return;
        }
        if(!checkIsChar(lastName))
        {
            addMemberTextArea.appendText("Last name cannot consist of numbers\n");
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
    private static boolean checkIsChar(String fName)
    {

        int numOfStrings = 0;
        char[] name = fName.toCharArray();

                for(int i = 0; i < name.length; i++)
                {
                    if(Character.isDigit(name[i]))
                        return false;
                }
                return true;






    }
    /**
     * Notifies the user to enter their first name, when they hover over
     * the first name text field.
     * @param event When the user hovers over the text field.
     */
    public void enterStringMessage(MouseEvent event) throws IOException{
        addMemberTextArea.appendText("Enter your first name\n");

    }
    /**
     * Used by the "Back" button in the add member menu to go back to
     * the main menu.
     * @param event When the user clicks on the button.
     */
    @FXML
    public void addBackToMenu(MouseEvent event) throws IOException {
    menuScreenButtons.setVisible(true);
    memberAddMenu.setVisible(false);
    addMenuLastName.clear();
    addMenuFirstName.clear();
    addMenuLocationCombo.getSelectionModel().select(0);
    addMenuDOB.getEditor().clear();
    addMemberStandard.setSelected(false);
    addMemberFamily.setSelected(false);
    addMemberPremium.setSelected(false);
    addMemberTextArea.clear();


}
//----------------------------------------------------------------------------------------

    //Remove Member Code ---------------------------------------------------------------------------
    /**
     * Used by the "Back" button in the remove member menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void removeBackToMenu(MouseEvent e){
        menuScreenButtons.setVisible(true);
        removeMemberTextArea.clear();
        memberRemoveMenu.setVisible(false);
        removeMenuFirstName.clear();
        removeMenuLastName.clear();
        removeMenuDOB.getEditor().clear();

    }

    /**
     * Invoked by the "Remove" button in the remove member menu; which removes a member
     * based on the information provided in the text fields. Information includes
     * first name, last name, and date of birth.
     * @param event When the user clicks on the button.
     */
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
        try {
            for (Member m : memberList.getMlist()) {
                if (m != null && personToRemove.equals(m)) {
                    memberList.remove(m);
                    removeMemberTextArea.appendText(firstName + " " + lastName + " " +
                            "removed.\n");
                    isInList = false;
                    break;
                }
                //System.out.println(memberList.printByName());
            }
        }
        catch(NullPointerException f)
        {
            removeMemberTextArea.appendText("No such person\n");
        }
        if(isInList) {
            removeMemberTextArea.appendText(firstName + " " + lastName + " is " +
                    "not in the database\n");
            return;
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

//Check In Code----------------
    /**
     * Used by the "Back" button in the check in menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void checkInToMenu(MouseEvent e){
        menuScreenButtons.setVisible(true);
        checkInScreen.setVisible(false);
        checkInFirstName.clear();
        checkInLastName.clear();
        checkInDOB.getEditor().clear();
        checkInRegCardio.setSelected(false);
        checkInRegSpinning.setSelected(false);
        checkInRegPilates.setSelected(false);
        checkInGuestButton.setSelected(false);

    }
    /**
     * Used by the check in instructor combo box to select the
     * name of the fitness instructors.
     * @param e When the user clicks on the combo box.
     */
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

    /**
     * Invoked by the "Check in" button in the check in menu; which checks in a member
     * to a fitness class based on the information provided in the text fields. Information includes
     * first name, last name, date of birth, instructor, location, guest, and class type.
     * @param a When the user clicks on the button.
     */
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
            checkInTextArea.appendText("Name fields are incomplete\n");
            return;
        }

        try{
            dob = new Date(checkInDOB.getValue().toString());
            dob.getDate();
            person[6] = checkInDOB.getValue().toString();
        }
        catch(NullPointerException  | ArrayIndexOutOfBoundsException e)
        {
            checkInTextArea.appendText("Date field is incorrect\n");
            return;
        }
        try{
            instructor = instructorComboBox.getValue().toString();
            location = locationComboBox.getValue().toString();
            person[2] = instructor;
            person[3]= location;
        }
        catch(NullPointerException e)
        {
            checkInTextArea.appendText("Instructor or Location field is blank\n");
            return;
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
            checkInTextArea.appendText("No class type was selected\n");
            return;
        }
        try {
            checkInTextArea.appendText(fitnessClassDatabase.checkIn(person, checkInGuestButton.isSelected(),
                    memberList,
                    fitnessClassDatabase));
        }
        catch(NullPointerException f)
        {
            checkInTextArea.appendText("A field was not filled out correctly\n");
            return;
        }

    }

    /**
     * Used by the "Back" button in the drop out menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void dropOutToMenu(MouseEvent e){
        menuScreenButtons.setVisible(true);
        dropOutScreen.setVisible(false);
        dropOutFirstName.clear();
        dropOutLastName.clear();
        dropOutDOB.getEditor().clear();
        dropOutRegCardio.setSelected(false);
        dropOutRegSpinning.setSelected(false);
        dropOutRegPilates.setSelected(false);
        dropOutGuestButton.setSelected(false);

    }

    /**
     * Used by the check in instructor combo box to select the
     * name of the fitness instructors.
     * @param e When the user clicks on the combo box.
     */
    @FXML
    public void dropOutInstructorComboBoxMethod(ActionEvent e)
    {

        if(dropInstructorComboBox.getValue().equals(Instructor.EMMA))
        {
            dropLocationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.EMMA)));
        }
        if(dropInstructorComboBox.getValue().equals(Instructor.DAVIS))
        {
            dropLocationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.DAVIS)));
        }
        if(dropInstructorComboBox.getValue().equals(Instructor.DENISE))
        {
            dropLocationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.DENISE)));
        }
        if(dropInstructorComboBox.getValue().equals(Instructor.JENNIFER))
        {
            dropLocationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.JENNIFER)));
        }
        if(dropInstructorComboBox.getValue().equals(Instructor.KIM))
        {
            dropLocationComboBox.setItems(FXCollections.observableArrayList(fitnessClassDatabase.getInstructorLocations(Instructor.KIM)));
        }
    }

    /**
     * Invoked by the "Drop out" button in the drop out menu; which drops a member
     * from fitness class based on the information provided in the text fields. Information includes
     * first name, last name, date of birth, instructor, location, guest, and class type.
     * @param a When the user clicks on the drop out button.
     */
    @FXML
    public void dropOutMember(MouseEvent a)
    {
        String fitnessClassType = null;
        String instructor = null;
        String location =null;
        String firstName = null;
        String lastName =null;
        Date dob = null;
        String[] person = new String[7];
        try{
            firstName = dropOutFirstName.getText();
            lastName = dropOutLastName.getText();
            person[4] = firstName;
            person[5] = lastName;
        }
        catch(NullPointerException e)
        {
            dropOutTextArea.appendText("Name fields are incomplete\n");
            return;
        }

        try{
            dob = new Date(dropOutDOB.getValue().toString());
            dob.getDate();
            person[6] = dropOutDOB.getValue().toString();
        }
        catch(NullPointerException  | ArrayIndexOutOfBoundsException e)
        {
            dropOutTextArea.appendText("Date field is incorrect\n");
            return;
        }
        try{
            instructor = dropInstructorComboBox.getValue().toString();
            location = dropLocationComboBox.getValue().toString();
            person[2] = instructor;
            person[3]= location;
        }
        catch(NullPointerException e)
        {
            dropOutTextArea.appendText("Instructor or Location field is blank\n");
            return;
        }
        try{
            if(dropOutRegPilates.isSelected())
            {
                fitnessClassType = "Pilates";
                person[1] = fitnessClassType;
            }
            else if(dropOutRegCardio.isSelected())
            {
                fitnessClassType = "Cardio";
                person[1] = fitnessClassType;
            }
            else if(dropOutRegSpinning.isSelected())
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
            dropOutTextArea.appendText("No class type was selected\n");
            return;
        }
        try {
            dropOutTextArea.appendText(fitnessClassDatabase.dropOut(person,
                    dropOutGuestButton.isSelected(),
                    memberList,
                    fitnessClassDatabase));
        }
        catch(NullPointerException f)
        {
            dropOutTextArea.appendText("A field was not filled out correctly\n");
            return;
        }

    }
    //Load Member Screen-------------------------------------------------------------------------
    /**
     * Used by the "Back" button in the load member menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void loadMemBackToMenu(MouseEvent e){
    menuScreenButtons.setVisible(true);
    loadMemberTextArea.clear();
    loadMembersScreen.setVisible(false);


}
    /**
     * Used by the "Load Members" button in the load member menu; which takes a text file of members
     * and load them into the database. The system will notify you if the file was loaded or not.
     * @param event When the user clicks on the button.
     */
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
            catch(NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e)
            {
                loadMemberTextArea.appendText("File not formatted correctly\n");
            }

        }
        else {
            loadMemberTextArea.appendText("Member list already loaded!\n");
        }


    }
    //Load Classes Code
    /**
     * Used by the "Back" button in the load classes menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void loadClassesBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadClassesTextArea.clear();
        loadClassesScreen.setVisible(false);
    }
    /**
     * Used by the "Load classes" button in the load classes menu; which takes a text file of fitness class
     * and load them into the database. The system will notify you if the file was loaded or not.
     * @param event When the user clicks on the button.
     */
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
            catch(NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e)
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
    /**
     * Used by the "Back" button of the member info tab in the information menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void informationMemBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadingMenu.setVisible(false);
        loadMenuMemTextArea.clear();
    }

    /**
     * Invoked by the combo box of member info in the information menu to
     * select the printing method to print out the members. The methods in the combo box
     * are print as is, by fee, by county, by name, and , by expiration.
     * @param e When the user clicks on the combo box.
     */
    @FXML
    public void infoMenuPrintMethod(ActionEvent e)
    {
       // {"Print", "Print By Fee", "Print By County", "Print By Name",
        //        "Print By Expiration"};
        //loadMenuMemTextArea.appendText(loadMenuMemInfoCombo.getValue().toString()+ "\n");
        try {
            if (loadMenuMemInfoCombo.getValue().toString().equals("Print")) {
                loadMenuMemTextArea.clear();
                loadMenuMemTextArea.appendText("\t--Printing by Order Added--");
                loadMenuMemTextArea.appendText(memberList.print());
            }
            if (loadMenuMemInfoCombo.getValue().toString().equals("Print By Fee")) {
                loadMenuMemTextArea.clear();
                loadMenuMemTextArea.appendText("\t--Printing by Fee--");
                loadMenuMemTextArea.appendText(memberList.printWFee());
            }
            if (loadMenuMemInfoCombo.getValue().toString().equals("Print By County")) {
                loadMenuMemTextArea.clear();
                loadMenuMemTextArea.appendText("\t--Printing by County--");
                loadMenuMemTextArea.appendText(memberList.printByCounty());
            }
            if (loadMenuMemInfoCombo.getValue().toString().equals("Print By Name")) {
                loadMenuMemTextArea.clear();
                loadMenuMemTextArea.appendText("\t--Printing by Name--");
                loadMenuMemTextArea.appendText(memberList.printByName());
            }
            if (loadMenuMemInfoCombo.getValue().toString().equals("Print By Expiration")) {
                loadMenuMemTextArea.clear();
                loadMenuMemTextArea.appendText("\t--Printing by Expiration--");
                loadMenuMemTextArea.appendText(memberList.printByExpiration());
            }
        }
        catch(NullPointerException f)
        {
            loadMemberTextArea.appendText("No member list is loaded!\n");
        }
    }


//-----------------------------------------------------------------------------------------------

//Information classes code
    /**
     * Used by the "Back" button of the class info tab in the information menu to go back to
     * the main menu.
     * @param e When the user clicks on the button.
     */
    @FXML
    public void informationClassBackToMenu(MouseEvent e) {
        menuScreenButtons.setVisible(true);
        loadingMenu.setVisible(false);
        loadMenuClassTextArea.clear();
    }
    /**
     * Invoked by the combo box of the class info tab in the information menu to
     * display the class schedules.
     * @param e When the user clicks on the combo box.
     */
    @FXML
    public void infoMenuClassMethod(ActionEvent e)
    {
        try{
                loadMenuClassTextArea.clear();
                loadMenuClassTextArea.appendText(fitnessClassDatabase.displaySchedule());

        }
        catch(NullPointerException | IndexOutOfBoundsException f)
        {
            loadClassesTextArea.appendText("No class schedule is loaded!\n");
        }

    }

//-----------------------------------------------------------------------------------------
    /**
     * Closes out of the Gym Manager application.
     * @param event When the user clicks on the Exit button
     */
    public void closeProgram(ActionEvent event) throws IOException {
        stg.close();
    }

}


package com.example.gymmanagergui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberInputStream;
import java.util.Scanner;

/**
 * GymManager accepts inputs from the command line, its features include
 * supervising valid member information, management of the members in the database,
 * and check members into fitness classes.
 * The object can print out sorted members in the database by name, location,
 * and expiration dates. GymManager will display a continues message while running
 * and will terminate once the user inputs Q.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class GymManager
{
    private MemberDatabase memberList;

    private ClassSchedule fitnessClassDatabase;
    /**
     * Continuously take user inputs from the command line
     * and manage members, member database, and fitness classes
     * based off valid commands.
     * Valid commands are A, P, R, PC, PD, PN, S, C, D, and Q.
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Scanner fileInput = new Scanner(System.in);
        System.out.println("Gym Manager running...");
        memberList = new MemberDatabase();
        fitnessClassDatabase = new ClassSchedule();
        String[] holder = new String[7];
        System.out.println("Please press enter to complete " +
                "your inputs");
        boolean loadMembers = false;
        boolean loadSchedule = false;
        do{
            while(input.hasNextLine())
            {
                holder = input.nextLine().split(" ");
                try {
                if(holder[0].equals("LM") || holder[0].equals("LS"))
                {
                    fileInput = loadInput(holder);
                        if(holder[0].equals("LM") && !loadMembers) {
                            loadMembers = true;
                            while(fileInput.hasNextLine()) {
                                holder = fileInput.nextLine().split(" ");
                                addMember(holder, loadMembers);
                            }
                            printList();
                        }
                        else if(loadMembers && loadSchedule)
                        {
                            System.out.println("Member list was already loaded!");
                        }
                        else if(holder[0].equals("LS") && !loadSchedule){
                            loadSchedule = true;
                            while(fileInput.hasNextLine()) {
                                holder = fileInput.nextLine().split(" ");
                                if(!holder[0].equals(""))
                                    loadClasses(holder);
                            }
                            loadClassesSchedule();
                        }


                        else if(loadSchedule)
                        {
                            System.out.println("Class Schedule was already loaded!");
                        }
                }
                else {
                    checkInput(holder);
                }

                if(holder[0].equals("Q"))
                    break;
            }
                catch(Exception FileNotFoundException)
                {
                    System.out.println("No file was found from LM or LS");
                }
}
            input = new Scanner(System.in);
        }while(!holder[0].equals("Q"));
    }


    /**
     * Processes the input line-by-line checking the first value to
     * make a decision
     * @param holder holds the input in a string array parsed by a
     *               whitespace
     */
    private void checkInput(String[] holder) throws ArrayIndexOutOfBoundsException
    {
        try{
            if(!(holder.length < 4) && (holder[0].equals("A") || holder[0].equals("AF") || holder[0].equals("AP"))){
                if(checkInputSize(holder) == 6){
                addMember(holder);}
                else{addTieredMember(holder);}
            }
            else if (holder[0].equals("P")) {
                this.printList();
            }
            else if (holder[0].equals("PF")) {
                this.printListOfFees();
            }
            else if(!(holder.length < 3) && holder[0].equals("R")) {
                removeFromList(holder);
            }
            else if(holder[0].equals("PC")) {
                printByCounty();
            }
            else if(holder[0].equals("PN")) {
                printByName();
            }
            else if(holder[0].equals("PD")) {
                printByExpiration();
            }
            else if(holder[0].equals("S")) {
                displaySchedule();
            }
            else if(!(holder.length < 4) && (holder[0].equals("C") || holder[0].equals("CG"))){
                if(holder[0].equals("C"))
                {
                    checkIn(holder, false);
                }
                else {
                    checkIn(holder, true);
                }
            }
            else if(!(holder.length < 4) && holder[0].equals("D") || holder[0].equals("DG")) {
                if(holder[0].equals("D")){removeFromFitness(holder, false);}
                else{removeFromFitness(holder, true);}
            }
            else if(holder[0].equals("Q")) {
                System.out.println("Gym Manager is terminated.");
            }
            else if(holder[0].equals("")) {
            }
            else {
                System.out.println(holder[0] + " is an invalid " +
                        "command");
            }
        }
        catch(Exception ArrayIndexOutOfBoundsException){
            System.out.println("Command had an invalid number of arguments");
        }
    }
    private int checkInputSize(String[] holder){
        int size = 0;
        for(int i = 0; i < holder.length; i++)
        {
            if(holder[i] != null)
                size++;
        }
            return size;
    }
    private Scanner loadInput(String[] holder) throws FileNotFoundException {
        Scanner input = null;

        if (holder[0].equals("LM")) {
            File file = new File("memberList.txt");
            input = new Scanner(file);
        }
        else if(holder[0].equals("LS")) {
            File file = new File("classSchedule.txt");
            input = new Scanner(file);
        }


        return input;
    }
    /**
     * Add member into the database by checking for valid information,
     * such as the date of birth, location, expiration, and if the member is already in
     * the database. This method is invoked by the run method, when the user inputs "A".
     * @param person The person that will be added to the database.
     */
    private void addMember(String[] person) {
        String firstName = person[1];
        String lastName = person[2];
        Date dob = new Date(person[3]);
        Date expiration = new Date(person[4]);
        Member newPerson = new Member(firstName, lastName, dob,
                expiration,determineLocation(person[5]));
        if(dob.isValid() && dob.isAdult() && determineLocation(person[5]) != null
                && memberList.find(newPerson) == -1 && expiration.isValidExpiration()){
            memberList.add(newPerson);
            System.out.println(String.format("%s %s has been added",
                    firstName, lastName));
        }
        else if(!dob.isValid()) {
            System.out.println("DOB " + dob.getDate() + " Invalid " +
                    "calendar date!");
        }
        else if(!dob.isPastButNotTodayOrPresent()) {
            System.out.println("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " cannot be today or a future date!");
        }
        else if(!dob.isAdult()) {
            System.out.println("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " must be 18 or older to join!");
        }
        else if(determineLocation(person[5]) == null) {
            System.out.println(person[5] +" is an invalid location!");
        }
        else if(memberList.find(newPerson) != -1) {
            System.out.println(String.format("%s %s is already in " +
                    "the data base", firstName, lastName));
        }
        else if(!expiration.isValid()){
            System.out.println("Expiration date " + expiration.getDate() + ": " +
                    "invalid calendar date");
        }
    }
    /**
     * Add member into the database by checking for valid information and accounts for membership;
     * such as the date of birth, location, expiration, and if the member is already in
     * the database. This method is invoked by the run method, when the user inputs "AP" or "AF".
     * @param person The person that will be added to the database.
     */
    private void addTieredMember(String[] person) {
        String firstName = person[1];
        String lastName = person[2];
        Date dob = new Date(person[3]);
        Member newPerson = null;
        if(person[0].equals("A")) {
             newPerson = new Member(firstName, lastName, dob, determineLocation(person[4]));
        }
        else if(person[0].equals("AF")) {
             newPerson = new Family(firstName, lastName, dob, determineLocation(person[4]));
        }
        else if(person[0].equals("AP")) {
             newPerson = new Premium(firstName, lastName, dob, determineLocation(person[4]));
        }

        if(dob.isValid() && dob.isAdult() && determineLocation(person[4]) != null
                && memberList.find(newPerson) == -1){
            memberList.add(newPerson);
            System.out.println(String.format("%s %s has been added",
                    firstName, lastName));
        }
        else if(!dob.isValid()) {
            System.out.println("DOB " + dob.getDate() + " Invalid " +
                    "calendar date!");
        }
        else if(!dob.isPastButNotTodayOrPresent()) {
            System.out.println("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " cannot be today or a future date!");
        }
        else if(!dob.isAdult()) {
            System.out.println("DOB " + newPerson.getDateOfBirth().getDate() +":" +
                    " must be 18 or older to join!");
        }
        else if(determineLocation(person[4]) == null) {
            System.out.println(person[4] +" is an invalid location!");
        }
        else if(memberList.find(newPerson) != -1) {
            System.out.println(String.format("%s %s is already in " +
                    "the data base", firstName, lastName));
        }
    }

    /**
     * Adds members to the member database when the "LM" command is called
     * Assumes that everything in the file is valid; doesn't check for inconsistencies compared
     * to the manual input of a member
     * @param person Person to be added to the database
     * @param fileChecker Always true in ordered to create an overloaded method and ensure that
     * it is only called when a file is being read
     */
    private void addMember(String[] person, boolean fileChecker)
    {
        String firstName = person[0];
        String lastName = person[1];
        Date dob = new Date(person[2]);
        Date expiration = new Date(person[3]);
        Member newPerson = new Member(firstName, lastName, dob,
                expiration,determineLocation(person[4]));
        if(dob.isValid() && dob.isAdult() && determineLocation(person[4]) != null
                && memberList.find(newPerson) == -1 && expiration.isValidExpiration()){
            memberList.add(newPerson);
        }
    }
    /**
     * Remove a valid member from their Fitness Class, will not allow drop if
     * the member is not checked in, invalid date of birth, and if the class does not exist.
     * This method is invoked by the run method, when the user inputs "D".
     * @param person The person that will be dropped their fitness class.
     */
    private void removeFromFitness(String[] person, boolean isGuest)
    {
        String fitnessClass = person [1];
        String instructor = person[2];
        String location = person[3];
        String firstName = person[4];
        String lastName = person[5];
        Date dob = new Date(person[6]);
        Member newPerson = new Member(firstName, lastName, dob);
        Member personToRemove = getMember(newPerson);
        if(!checkMemberForClass(personToRemove,  fitnessClass,  location,  instructor, true))
        {
        }
        else if(!fitnessClassDatabase.guestIsInClass(location, fitnessClass, instructor, newPerson) && isGuest
                || (!fitnessClassDatabase.memberIsInClass(location, fitnessClass, instructor,
                newPerson) && !isGuest)){
                System.out.println(String.format("%s %s did not check in", personToRemove.getFname(), personToRemove.getLName()));
            }
            else if(fitnessClassDatabase.memberIsInClass(location, fitnessClass, instructor,
                    newPerson)|| (fitnessClassDatabase.guestIsInClass(location, fitnessClass,
                    instructor, newPerson) && isGuest)) {
                if(!isGuest) {
                    System.out.println(String.format("%s %s done with the class",
                            personToRemove.getFname(), personToRemove.getLName()));
                    fitnessClassDatabase.removeFromClass(personToRemove, location, instructor, fitnessClass);
                }
                else
                {
                    System.out.println(String.format("%s %s Guest done with the class",
                            personToRemove.getFname(), personToRemove.getLName()));
                    fitnessClassDatabase.removeGuestFromClass(personToRemove, location, instructor,
                            fitnessClass);
                    ((Family) personToRemove).addGuestPass();
                }


        }
        else if(!fitnessClassDatabase.memberIsInClass(location, fitnessClass, instructor,
            newPerson)){
            System.out.println(String.format("%s %s is not a " + "participant in " + "%s", firstName, lastName, fitnessClass));
        }
        else {
            System.out.println(fitnessClass + " class does not " + "exist");
        }
    }

    /**
     * Prints the members in the database as is including their membership fees.
     * This method is invoked by the run method, when the user inputs "PF".
     */
    private void printListOfFees()
    {
        System.out.println();
        if(memberList.getNumOfMembers() != 0)
        {
            System.out.println("-list of members-");
            memberList.printByFee();
            System.out.println("-end of list-\n");
        }
        else {
            System.out.println("Member database is empty!");
        }
    }
    /**
     * Prints the members in the database as is.
     * This method is invoked by the run method, when the user inputs "P".
     */
    private void printList()
    {
        System.out.println();
        if(memberList.getNumOfMembers() != 0)
        {
            System.out.println("-list of members-");
            memberList.print();
            System.out.println("-end of list-\n");
        }
        else {
            System.out.println("Member database is empty!");
        }
    }
    /**
     * Cancels the member's membership and removes them from the member database.
     * Will not allow removal if the person is not a member.
     * This method is invoked by the run method, when the user
     * inputs "R".
     * @param person A person that will be removed from the member database.
     */
    private void removeFromList(String[] person)
    {

        boolean isInList = true;
        if(person.length < 3 || person[1] == null || person[2] == null || person[3] == null)
        {
            System.out.println("Invalid Command");
            return;
        }
        String firstName = person[1];
        String lastName = person[2];
        Date dob = new Date(person[3]);
        Member personToRemove = new Member(firstName, lastName,dob);
        for(Member m: memberList.getMlist())
        {
            if(m != null && personToRemove.equals(m))
            {
                memberList.remove(m);
                System.out.println(firstName + " " + lastName + " " +
                        "removed.\n");
                isInList = false;
                break;
            }
        }
        if(isInList) {
            System.out.println(firstName + " " + lastName + " is " +
                    "not in the database");
        }
    }
    /**
     * Used to check the validity of location for the adding members,
     * the locations of the add members must be Bridgewater, Edison, Franklin,
     * Piscataway, or Somerville.
     * @param loc A person's location must equal to one of the locations listed.
     * @return Returns the location if the person's location is equal to the locations listed.
     * Returns null if it is somewhere else.
     */
    private static Location determineLocation(String loc)
    {

        if(loc.toLowerCase().equals("bridgewater"))
        {
            return Location.BRIDGEWATER;
        }
        if(loc.toLowerCase().equals("edison"))
        {
            return Location.EDISON;
        }
        if(loc.toLowerCase().equals("franklin"))
        {
            return Location.FRANKLIN;
        }
        if(loc.toLowerCase().equals("piscataway"))
        {
            return Location.PISCATAWAY;
        }
        if(loc.toLowerCase().equals("somerville"))
        {
            return Location.SOMERVILLE;
        }
        else{
            return null;
        }
    }
    /**
     * Prints the list of members in the database ordered by the county names and then the zip codes;
     * that is, if the locations are in the same county, ordered by the zip codes.
     * This method is invoked by the run method, when the user inputs "PC".
     */
    private void printByCounty()
    {
        System.out.println();
        if(memberList.getNumOfMembers() != 0)
        {
            System.out.println("-list of members sorted by county and zipcode-");
            memberList.printByCounty();
            System.out.println("-end of list-\n");
        }
        else {
            System.out.println("Member database is empty!");
        }
    }
    /**
     * Prints the list of members in the database ordered by the members’ last names and then first names;
     * that is, if two members have the same last name, ordered by the first name.
     * This method is invoked by the run method, when the user inputs "PN".
     */
    private void printByName()
    {
        System.out.println();
        if(memberList.getNumOfMembers() != 0)
        {

            System.out.println("-list of members sorted by last name, and first name-");
            memberList.printByName();
            System.out.println("-end of list-\n");
        }
        else {
            System.out.println("Member database is empty!");
        }
    }
    /**
     * Prints the list of members in the database ordered by the expiration dates. If two expiration dates
     * are the same, their order does not matter.
     * This method is invoked by the run method, when the user inputs "PD".
     */
    private void printByExpiration()
    {
        System.out.println();
        if(memberList.getNumOfMembers() != 0)
        {
            System.out.println("-list of members sorted by " +
                    "membership expiration date-");
            memberList.printByExpiration();
            System.out.println("-end of list-");
        }
        else {
            System.out.println("Member database is empty!");
        }
    }
    /**
     * Print the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
     * name, the time of the class, and the list of members who have already checked in today.
     * This method is invoked by the run method, when the user inputs "S".
     */
     private void displaySchedule()
    {
        System.out.println();
        System.out.println("-Fitness Classes-");
        for(FitnessClass c: fitnessClassDatabase.getClasses())
        {
            if(c != null) {
                System.out.println(String.format("%s - %s, %s, %s", c.getClassType(), c.getInstructor()
                        , c.getTime().getTime(), c.getLocation()));
                displayParticipants(c);
            }
        }
        System.out.println("-end of class list-");

    }

    /**
     * Used to load the fitness class schedule from a file input
     * Assumes the file is correctly formatted
     * @param holder Contains string values that hold a person's attributes
     */
    private void loadClasses(String[] holder)
    {
        String classType = holder[0];
        Instructor instructor = Instructor.valueOf(holder[1].toUpperCase());
        Time time = Time.valueOf(holder[2].toUpperCase());
        Location location = Location.valueOf(holder[3].toUpperCase());

        FitnessClass newClass = new FitnessClass(classType,
                instructor, time, location);

        fitnessClassDatabase.addClass(newClass);

    }

    /**
     * Used when loading the class schedule to print out the classes when finished
     */
    private void loadClassesSchedule()
    {
        System.out.println();
        for(FitnessClass f : fitnessClassDatabase.getClasses())
        {
            if(f != null) {
                System.out.println(String.format("%s - %s, %s, %s",
                        f.getClassType(), f.getInstructor(),
                        f.getTime().getTime(),
                        f.getLocation()));
            }
        }


    }

    /**
     * Check in members into their desired fitness class. A member can not check if
     * the membership has expired, the member does not exist, the date of birth is invalid,
     * the fitness class does not exist, there is a time conflict with other fitness classes,
     * or the member has already checked in.
     * This method is invoked by the run method, when the user inputs "C".
     * @param person A person that will be checked into the fitness class.
     */
   /* public void checkInLegacy(String[] person) {
        String fitnessClassType = person[1];
        String firstName = person[2];
        String lastName = person[3];
        Date dob = new Date(person[4]);
        Member newPerson = new Member(firstName, lastName, dob);
        Member actualMember = getMember(newPerson);
        if(!checkMemberForClass(actualMember, fitnessClassType)){
            return;
        }
        else if(fitnessClassType.toLowerCase().equals("pilates")) {
            if(fitnessClassDatabase.checkIfInPilates(actualMember)){
                System.out.println(String.format("%s %s has " +
                        "already checked into %s", firstName, lastName, fitnessClassType));
            }
            else {
                fitnessClassDatabase.addToPilatesClass(actualMember);
                System.out.println(String.format("%s %s has check " +
                        "in %s", firstName, lastName, fitnessClassType));
            }
        }
        else if(fitnessClassType.toLowerCase().equals("cardio")) {
            if(fitnessClassDatabase.checkIfInCardio(actualMember)){
                System.out.println(String.format("%s %s has " +
                        "already checked into %s", firstName, lastName, fitnessClassType));
            }
            else {
                fitnessClassDatabase.addToCardioClass(actualMember);
                System.out.println(String.format("%s %s has check in %s", firstName, lastName, fitnessClassType));
            }
        }
        else if(fitnessClassType.toLowerCase().equals("spinning")) {
            if(fitnessClassDatabase.checkIfInSpinning(actualMember)){
                System.out.println(String.format("%s %s has already checked into %s", firstName, lastName, fitnessClassType));
            }
            else {
                fitnessClassDatabase.addToSpinningClass(actualMember);
                System.out.println(String.format("%s %s has check in %s", firstName, lastName, fitnessClassType));
            }
        }
    }*/





    /**
     * Check in members into their desired fitness class. A member can not check if
     * the membership has expired, the member does not exist, the date of birth is invalid,
     * the fitness class does not exist, there is a time conflict with other fitness classes,
     * or the member has already checked in.
     * This method is invoked by the run method, when the user inputs "C" or "CG".
     * @param person A person that will be checked into the fitness class.
     * @param isGuest A person is either a guest or not a guest effecting implementation
     */
    public void checkIn(String[] person, boolean isGuest) {
       String fitnessClassType = person[1];
       String instructor = person[2];
       String location = person[3];
       String firstName = person[4];
       String lastName = person[5];
       Date dob = new Date(person[6]);
       Member newPerson = new Member(firstName, lastName, dob);
       Member actualMember = getMember(newPerson);
       if (!checkMemberForClass(actualMember, fitnessClassType, location, instructor, isGuest)) {
       }
       else {
           FitnessClass currentClass = fitnessClassDatabase.getFitnessClass(instructor, location, fitnessClassType);
           Location loc = determineLocation(location);
           assert loc != null;
         if (actualMember instanceof Family && isGuest) {
               if (((Family) actualMember).getGuestPasses() == 0) {
                   System.out.println(String.format("%s %s ran out of guest passes",
                           actualMember.getFname(), actualMember.getLName()));
               }
                   else{
                       if(actualMember.getLocation() == currentClass.getLocation())
                       {
                           currentClass.addToGuestClass(actualMember);
                           actualMember.useGuestPass();
                           System.out.println(String.format("%s %s (guest) checked in %s - %s, " +
                                           "%s, %s",
                                   actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                                   currentClass.getInstructor(),
                                   currentClass.getTime().getTime(), currentClass.getLocation()));
                           displayParticipants(currentClass);
                       }
                       else{
                           System.out.println(String.format("%s %s checking in %s, %s, %s - guest" +
                                           " location restriction", actualMember.getFname(), actualMember.getLName(),
                                            loc, loc.getZIP(), actualMember.getLocation().getCOUNTY()));
                       }

                   }
               }
           else if (actualMember instanceof Family) {
               System.out.println(String.format("%s %s checked in %s - %s, %s, %s",
                       actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                       currentClass.getInstructor(), currentClass.getTime().getTime(),
                       currentClass.getLocation()));
               currentClass.addToClass(actualMember);
               displayParticipants(currentClass);
           }
         else if(isGuest)
         {
             System.out.println("Standard membership - guest check-in is not allowed.");
         }
         else if (!actualMember.getLocation().toString().equalsIgnoreCase(location)) {
               System.out.println(String.format("%s %s checking in %s, %s, %s - standard membership " +
                               "location restriction", actualMember.getFname(), actualMember.getLName(),
                       loc, loc.getZIP(), actualMember.getLocation().getCOUNTY()));
           } else if (actualMember.getLocation().toString().equalsIgnoreCase(location)) {
               System.out.println(String.format("%s %s checked in %s - %s, %s, %s",
                       actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                       currentClass.getInstructor(),
                       currentClass.getTime().getTime(), currentClass.getLocation()));
               currentClass.addToClass(actualMember);
               displayParticipants(currentClass);
           }
       }
   }

    /**
     * Used to check if a member is already in their desired fitness class, and
     * whatever their date of birth and expiration date is valid.
     * A member can not be added if they are already in the fitness class or
     * be removed if they are not in the fitness class to begin with.
     * @param member The member that is checked to see if they are in a fitness class or not.
     * @param classType The fitness class that is being checked for said member.
     * @param location Location of the member
     * @param instuctor Instructor of the fitness class being checked into
     * @param isGuest A person is either a guest or not a guest
     * @return Returns true if the member is already in the fitness class,
     * else return false.
     */
    private boolean checkMemberForClass(Member member,
                                         String classType, String location, String instuctor,
                                        boolean isGuest)
    {

        if(member.getDateOfBirth().isValid() && member.getDateOfBirth().isAdult()
                && memberList.find(member) != -1 && member.getExpiration().isValidExpiration()
                && !member.getExpiration().isPastButNotTodayOrPresent()
                && FitnessClass.checkIfActualClass(classType) && Instructor.isValidInstructor(instuctor)
                && fitnessClassDatabase.isValidLocationForInstructor(instuctor, location, classType)
                && determineLocation(location) != null && checkMemberValidLocation(member, location)) {
            if(fitnessClassDatabase.isCheckedIn(member) && !isGuest) {
                return compareClassCheckIn(fitnessClassDatabase.getMemberClass(member), location,
                        instuctor, member, classType);
            }
            return true;
        }
        else if(!FitnessClass.checkIfActualClass(classType)) {
            System.out.println(classType + " class does not exist.");
        }
        else if(!member.getDateOfBirth().isValid()) {
            System.out.println("DOB " + member.getDateOfBirth().getDate() + " invalid calendar date!");
        }

        else if(!Instructor.isValidInstructor(instuctor)) {
            System.out.println(instuctor + " - instructor does not exist");
        }
        else if(determineLocation(location) == null) {
            System.out.println(location + " - invalid location.");
        }
        else if(!fitnessClassDatabase.isValidLocationForInstructor(instuctor, location, classType))
        {
            System.out.println(String.format("%s by %s does not exist at %s", classType,
                    instuctor, location));
        }
        else if(memberList.find(member) == -1) {
            System.out.println(String.format("%s %s is not in the gym member database", member.getFname(), member.getLName()));
        }
        else if(!member.getExpiration().isValidExpiration()){
            System.out.println("Expiration date " + member.getExpiration().getDate() + ": invalid calendar date");
        }
        else if(member.getExpiration().isValidExpiration() && member.getExpiration().isPastButNotTodayOrPresent()){
            System.out.println("Expiration date " + member.getDateOfBirth().getDate() + ": " +
                    "has expired");
        }
        else{
            return true;
        }
        return false;
    }

    /**
     * Checks a member's location for the class they are checking into
     * Members cannot check into anywhere but their own location
     * Family members and children can check in anywhere
     * @param member
     * @param location
     * @return True if the fitness class is in a valid location; False if the fitness class is
     * outside their location range
     */
    private boolean checkMemberValidLocation(Member member, String location)
    {
        if(member instanceof Family)
        {
            return true;
        }
        else if(!member.getLocation().toString().equalsIgnoreCase(location)){
            return false;
        }
        return true;

    }

    /**
     * Used to determine if a member is valid in checking into a class when they are already
     * checked into another class
     * Checks cases like time conflicts, being the same exact class and the location
     * @param currentClass Fitness class being checked into
     * @param location Location of the fitness class
     * @param instructor Instructor of the fitness class
     * @param member A person that is checking into the fitness class
     * @param classType Type of the fitness class: Spinning, Cardio, Pilates
     * @return True if the member is eligible to check into the class. False if there are
     * scheduling conflicts
     */
    private boolean compareClassCheckIn(FitnessClass currentClass, String location,
                                     String instructor, Member member, String classType){
        if(determineLocation(location) == null)
        {
            System.out.println(location + " - invalid location.");
            return false;
        }
        if(location.equalsIgnoreCase(currentClass.getLocation().toString()) && instructor.equalsIgnoreCase(currentClass.getInstructor().toString()) && classType.equalsIgnoreCase(currentClass.getClassType()))
        {
            System.out.println(String.format("%s %s already checked in",member.getFname(),
                    member.getLName()));
                    return false;
        }
        else if(fitnessClassDatabase.isValidLocationForInstructor(instructor, location, classType) && !fitnessClassDatabase.memberIsInClass(instructor, classType,location, member))
            {
               FitnessClass classToCompare = fitnessClassDatabase.getFitnessClass(instructor, location, classType);
                if(currentClass.getTime() == classToCompare.getTime())
                {
                    System.out.println(String.format("Time conflict - %s - %s, %s, %s, %s, %s",
                            classToCompare.getClassType(),
                            classToCompare.getInstructor(), classToCompare.getTime().getTime(),
                            classToCompare.getLocation(),classToCompare.getLocation().getZIP(),
                            classToCompare.getLocation().getCOUNTY()));
                    return false;
                }
                else {
                    return true;
                }

            }



        return false;
    }

    /**
     * Shows the current participants of a fitness class
     * Participants include both regular members and guests each have their own list
     * Instances of family also print out their respective guest passes remaining
     * @param currentClass Fitness class whose participants are being displayed
     */
    public void displayParticipants(FitnessClass currentClass)
    {
        if(!currentClass.getParticipants().isEmpty()) {
            System.out.println("- Participants -");
            for (Member m : currentClass.getParticipants()) {
                System.out.println(m.toString());
                if (m instanceof Premium) {
                    System.out.println("(Premium) guest passes remaining " + ((Premium) m).getGuestPasses());
                } else if (m instanceof Family) {
                    System.out.println("(Family) guest passes remaining " + ((Family) m).getGuestPasses());
                }
            }
        }
        if(!currentClass.getGuests().isEmpty()) {
            System.out.println("- Guests -");
            for (Member m : currentClass.getGuests()) {
                System.out.println(m.toString());
                if (m instanceof Premium) {
                    System.out.println("(Premium) guest passes remaining " + ((Premium) m).getGuestPasses());
                } else if (m instanceof Family) {
                    System.out.println("(Family) guest passes remaining " + ((Family) m).getGuestPasses());
                }
            }
        }
        System.out.println();
    }
    /**
     * Used by removedFromFitness and checkIn method to get the members in the database.
     * @param member Member to compare to the member in database.
     * @return Returns the member in the member list is equal to the member given. Else
     * return the member given.
     */
    private Member getMember(Member member)
    {
        for(Member m: memberList.getMlist())
        {
            if(m != null && m.equals(member))
            {
                return m;
            }
        }
        return member;
    }










    public static void main(String[] args) {
        Date dob1 = new Date("1/20/2004");
        Date dob2 = new Date("1/20/2004");
        Date expires = new Date("1/1/2023");
        Date expires2 = new Date("2/2/2023");
        Date expires3 = new Date("3/3/2023");
        Date expires4 = new Date("4/4/2023");
        Date expires5 = new Date("5/5/2023");




        Member person1 = new Member("John", "Eoe", dob1,expires5,
                Location.BRIDGEWATER);
        Member person2 = new Member("Jane", "Doe", dob2,expires4,
                Location.PISCATAWAY);
        Member person3 = new Member("Jow", "Coe", dob2,expires3,
                Location.FRANKLIN);
        Member person4 = new Member("Johnny", "Boe", dob2,expires2,
                Location.EDISON);
        Member person5 = new Member("Julia", "Doe", dob2,expires,
                Location.BRIDGEWATER);
        MemberDatabase database = new MemberDatabase();
        //FitnessClass fitness = new FitnessClass("Cardio");
        database.add(person1);
        database.add(person2);
        database.add(person3);
        database.add(person4);
        database.add(person5);
        //database.printByName();
        //database.printExpiration();
        //database.printByCounty();
        //database.remove(person3);

        //database.print();

        //String a = "Blake";
        //String b = "Blakf";

        //System.out.println(database.getNumberOfMembers());
        //System.out.println(Instructor.KIM);

        database.print();
    }


}

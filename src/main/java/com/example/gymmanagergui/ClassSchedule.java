package com.example.gymmanagergui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The ClassSchedule class manages the fitness classes; along with the
 * members and guests. Guest and members can be added or removed from a fitness class
 * as well as check whether the individuals are in a fitness class.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;
    private String message;
    private final int MAX_CLASSES = 3;
    private final int MAX_INSTRUCTORS = 5;

    /**
     * Initialized ClassSchedule creates a FitnessClass array setting the size to 4.
     */
    public ClassSchedule() {
        numClasses = 4;
        classes = new FitnessClass[4];
    }
    /**
     * Get the number of classes.
     * @return The number of classes.
     */
    public int getNumClasses() {
        return numClasses;
    }
    /**
     * Get the fitness classes.
     * @return The fitness classes.
     */
    public FitnessClass[] getClasses() {
        return classes;
    }
    /**
     * Add a fitness class into the database.
     * @param fitnessClass The class being added.
     * @return Returns true when a fitness class has been successfully added.
     */
    public boolean addClass(FitnessClass fitnessClass) {
        int index = 0;
        for (int i = 0; i < classes.length; i++) {
            if (classes[i] == null) {
                index = i;
                break;
            }
            if (i == classes.length - 1) {
                this.grow(numClasses, classes);
                numClasses += 4;
                classes[i + 1] = fitnessClass;
                return true;
            }
        }
        classes[index] = fitnessClass;
        return true;
    }
    /**
     * Remove a member from the fitness class.
     * @param member The member being removed.
     * @param location The location.
     * @param instructor The fitness class instructor.
     * @param classType The class type being offered.
     */
    public void removeFromClass(Member member, String location, String instructor, String classType) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (instructor.equalsIgnoreCase(c.getInstructor().toString()))
                    if (c.getLocation().toString().equals(location.toUpperCase()))
                        if (c.getClassType().equalsIgnoreCase(classType))
                            c.removeFromFitnessClass(member);
        }


    }
    /**
     * Remove a guest from the fitness class.
     * @param member The member being removed.
     * @param location The location.
     * @param instructor The fitness class instructor.
     * @param classType The class type being offered.
     */
    public void removeGuestFromClass(Member member, String location, String instructor,
                                 String classType) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (instructor.equalsIgnoreCase(c.getInstructor().toString()))
                    if (c.getLocation().toString().equals(location.toUpperCase()))
                        if (c.getClassType().equalsIgnoreCase(classType))
                            c.removeGuestFromFitnessClass(member);
        }


    }
    /**
     * Used by the fitness database each time to increase the size
     * once it has hit max capacity.
     * @param size The size that being added.
     * @param list The Fitness class database that being increased.
     */
    private void grow(int size, FitnessClass[] list) {
        FitnessClass[] holder = new FitnessClass[size];
        for (int i = 0; i < size; i++) {
            holder[i] = list[i];
        }
        classes = new FitnessClass[size + 4];
        for (int i = 0; i < size; i++) {
            classes[i] = holder[i];
        }

    }
    /**
     * Checks if a member or guest is in a fitness class.
     * @param person The person being checked.
     * @return Returns true if the person is in the class, otherwise return false.
     */
    public boolean isCheckedIn(Member person) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (c.isInFitnessClass(person))
                    return true;

        }
        return false;

    }
    /**
     * Get the fitness class of a member.
     * @param person The member in the class.
     * @return Returns the fitness class of the member,
     * if the member is not in the class return null.
     */
    public FitnessClass getMemberClass(Member person) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (c.isInFitnessClass(person))
                    return c;

        }
        return null;
    }
    /**
     * Checks the validity of the class being offered.
     * @param i The instructor of the fitness class.
     * @param location The location.
     * @param classType The class that is being offered.
     * @return Returns true if the class is a valid class being offered, otherwise
     * return false.
     */
    public boolean isValidLocationForInstructor(String i, String location, String classType) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (c.getInstructor().toString().equals(i.toUpperCase()))
                    if (c.getLocation().toString().equals(location.toUpperCase()))
                        if (c.getClassType().equalsIgnoreCase(classType))
                            return true;

        }
        return false;
    }
    /**
     * Get a fitness class that being offered.
     * @param i The instructor of the fitness class.
     * @param location The location.
     * @param classType The class that is being offered.
     * @return Returns a fitness class that is offered if it matches the criteria,
     * otherwise return null if no class matches with the criteria.
     */
    public FitnessClass getFitnessClass(String i, String location, String classType) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (i.equalsIgnoreCase(c.getInstructor().toString()))
                    if (c.getLocation().toString().equals(location.toUpperCase()))
                        if (c.getClassType().equalsIgnoreCase(classType))
                            return c;

        }
        return null;
    }
    /**
     * Check if a member is in a fitness class.
     * @param location The location.
     * @param classType The class that is being offered.
     * @param instructor The instructor of the fitness class.
     * @param member The member that is being checked for.
     * @return True if the member is found in a fitness class, otherwise return false.
     */
    public boolean memberIsInClass(String location, String classType, String instructor, Member member) {
        for (FitnessClass c : classes) {
            if (c != null)
                if (c.getInstructor().toString().equals(instructor.toUpperCase()))
                    if (c.getLocation().toString().equals(location.toUpperCase()))
                        if (c.getClassType().equalsIgnoreCase(classType))
                            return c.isInFitnessClass(member);


        }
        return false;


    }
    /**
     * Check if a guest is in a fitness class.
     * @param location The location.
     * @param classType The class that is being offered.
     * @param instructor The instructor of the fitness class.
     * @param member The guest that is being checked for.
     * @return True if the guest is found in a fitness class, otherwise return false.
     */
    public boolean guestIsInClass(String location, String classType, String instructor,
                                 Member member)
    {
        for(FitnessClass c : classes)
        {
            if( c != null)
                if(c.getInstructor().toString().equals(instructor.toUpperCase()))
                    if(c.getLocation().toString().equals(location.toUpperCase()))
                        if(c.getClassType().equalsIgnoreCase(classType))
                            return c.isGuestInFitnessClass(member);


        }
        return false;



    }

    public void loadClasses(File classes) throws FileNotFoundException {
        String[] classDetails =  new String[4];
        Scanner fileInput = new Scanner(classes);
        while(fileInput.hasNextLine()) {
            classDetails = fileInput.nextLine().split(" ");
            if (!classDetails[0].equals("")) {
                String classType = classDetails[0];
                Instructor instructor = Instructor.valueOf(classDetails[1].toUpperCase());
                Time time = Time.valueOf(classDetails[2].toUpperCase());
                Location location = Location.valueOf(classDetails[3].toUpperCase());
                FitnessClass newClass = new FitnessClass(classType, instructor, time, location);
                this.addClass(newClass);
            }
        }

    }

    public String displaySchedule()
    {
        String schedule;
        schedule = ("-Fitness Classes-\n");
        for(FitnessClass c: this.getClasses())
        {
            if(c != null) {
                schedule += (String.format("%s - %s, %s, %s\n", c.getClassType(), c.getInstructor()
                        ,c.getTime().getTime(), c.getLocation()));
                displayParticipants(c, schedule);
            }
        }
        schedule += ("-end of class list-\n");
        return schedule;
    }

    /**
     * Shows the current participants of a fitness class
     * Participants include both regular members and guests each have their own list
     * Instances of family also print out their respective guest passes remaining
     * @param currentClass Fitness class whose participants are being displayed
     */
    public void displayParticipants(FitnessClass currentClass, String schedule)
    {
        if(!currentClass.getParticipants().isEmpty()) {
            schedule += ("- Participants -\n");
            for (Member m : currentClass.getParticipants()) {
                schedule += (m.toString()+ "\n");
                if (m instanceof Premium) {
                    schedule += ("(Premium) guest passes remaining " + ((Premium) m).getGuestPasses() + "\n");
                } else if (m instanceof Family) {
                    schedule += ("(Family) guest passes remaining " + ((Family) m).getGuestPasses()+ "\n");
                }
            }
        }
        if(!currentClass.getGuests().isEmpty()) {
            schedule += ("- Guests -"+ "\n");
            for (Member m : currentClass.getGuests()) {
                schedule += (m.toString()+ "\n");
                if (m instanceof Premium) {
                    schedule += ("(Premium) guest passes remaining " + ((Premium) m).getGuestPasses() +"\n");
                } else if (m instanceof Family) {
                    schedule += ("(Family) guest passes remaining " + ((Family) m).getGuestPasses() + "\n");
                }
            }
        }
        schedule += "\n";
        //return schedule;
    }

    public Location[] getInstructorLocations(Instructor i)
    {
        Location[] instructorLocations = new Location[MAX_CLASSES];
        int index = 0;
        for(FitnessClass f: this.classes)
        {
            if(f != null && f.getInstructor() == i)
            {
                if(!isInInstructorLocationArray(instructorLocations, f.getLocation()))
                {
                    instructorLocations[index] = f.getLocation();
                    index++;

                }
            }
        }
        Location[] instructorLocationsFinal = new Location[index];
        for(int j = 0; j < index; j++)
        {
            instructorLocationsFinal[j] = instructorLocations[j];
        }

        return instructorLocationsFinal;
    }
    private boolean isInInstructorLocationArray(Location[] locations, Location toBeAdded)
    {
        for(int i = 0; i < MAX_CLASSES; i++)
        {
            if(toBeAdded == locations[i])
            {
                return true;
            }
        }
        return false;
    }
    public Instructor[] getLocationInstructors(Location l)
    {
        Instructor[] locationInstructors = new Instructor[MAX_INSTRUCTORS];
        int index = 0;
        for(FitnessClass f: this.classes)
        {
            if(f != null && f.getLocation() == l)
            {
                System.out.println(f.getLocation());
                if(!isInLocationInstructorArray(locationInstructors, f.getInstructor()))
                {
                    locationInstructors[index] = f.getInstructor();
                    index++;

                }
            }
        }
        Instructor[] locationInstructorsFinal = new Instructor[index];
        for(int j = 0; j < index; j++)
        {
            locationInstructorsFinal[j] = locationInstructors[j];
        }

        return locationInstructorsFinal;
    }
    private boolean isInLocationInstructorArray(Instructor[] instructors, Instructor toBeAdded)
    {
        for(int i = 0; i < MAX_INSTRUCTORS; i++)
        {
            if(toBeAdded == instructors[i])
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Check in members into their desired fitness class. A member can not check if
     * the membership has expired, the member does not exist, the date of birth is invalid,
     * the fitness class does not exist, there is a time conflict with other fitness classes,
     * or the member has already checked in.
     * This method is invoked by the run method, when the user inputs "C" or "CG".
     * @param person A person that will be checked into the fitness class.
     * @param isGuest A person is either a guest or not a guest effecting implementation
     */
    public String checkIn(String[] person, boolean isGuest, MemberDatabase mlist,
                        ClassSchedule fitnessClassDatabase) {
        String fitnessClassType = person[1];
        String instructor = person[2];
        String location = person[3];
        String firstName = person[4];
        String lastName = person[5];
        Date dob = new Date(person[6]);
        message = "";
        Member newPerson = new Member(firstName, lastName, dob);
        Member actualMember = mlist.getMember(newPerson);
        if (!checkMemberForClass(actualMember, fitnessClassType, location, instructor, isGuest,
                fitnessClassDatabase, mlist)) {
        }
        else {
            FitnessClass currentClass = fitnessClassDatabase.getFitnessClass(instructor, location, fitnessClassType);
            Location loc = Location.setLocation(location);
            assert loc != null;
            if (actualMember instanceof Family && isGuest) {
                if (((Family) actualMember).getGuestPasses() == 0) {
                    message += (String.format("%s %s ran out of guest passes\n",
                            actualMember.getFname(), actualMember.getLName()));
                }
                else{
                    if(actualMember.getLocation() == currentClass.getLocation())
                    {
                        currentClass.addToGuestClass(actualMember);
                        actualMember.useGuestPass();
                        message += (String.format("%s %s (guest) checked in %s - %s, " +
                                        "%s, %s\n",
                                actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                                currentClass.getInstructor(),
                                currentClass.getTime().getTime(), currentClass.getLocation()));
                        displayParticipants(currentClass, message);
                    }
                    else{
                        message += (String.format("%s %s checking in %s, %s, %s - guest" +
                                        " location restriction\n",
                                actualMember.getFname(), actualMember.getLName(),
                                loc, loc.getZIP(), actualMember.getLocation().getCOUNTY()));
                    }

                }
            }
            else if (actualMember instanceof Family) {
                message += String.format("%s %s checked in %s - %s, %s, %s\n",
                        actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                        currentClass.getInstructor(), currentClass.getTime().getTime(),
                        currentClass.getLocation());
                currentClass.addToClass(actualMember);
                displayParticipants(currentClass, message);
            }
            else if(isGuest)
            {
                message += ("Standard membership - guest check-in is not allowed.\n");
            }
            else if (!actualMember.getLocation().toString().equalsIgnoreCase(location)) {
                message += (String.format("%s %s checking in %s, %s, %s - standard membership " +
                                "location restriction\n",
                        actualMember.getFname(), actualMember.getLName(),
                        loc, loc.getZIP(), actualMember.getLocation().getCOUNTY()));
            } else if (actualMember.getLocation().toString().equalsIgnoreCase(location)) {
                message += (String.format("%s %s checked in %s - %s, %s, %s\n",
                        actualMember.getFname(), actualMember.getLName(), currentClass.getClassType(),
                        currentClass.getInstructor(),
                        currentClass.getTime().getTime(), currentClass.getLocation()));
                currentClass.addToClass(actualMember);
                displayParticipants(currentClass, message);
            }
        }
        System.out.println("here");
        System.out.println(message);
        return message;
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
                                        boolean isGuest, ClassSchedule fitnessClassDatabase,
                                        MemberDatabase  memberList)
    {

        if(member.getDateOfBirth().isValid() && member.getDateOfBirth().isAdult()
                && memberList.find(member) != -1 && member.getExpiration().isValidExpiration()
                && !member.getExpiration().isPastButNotTodayOrPresent()
                && FitnessClass.checkIfActualClass(classType) && Instructor.isValidInstructor(instuctor)
                && fitnessClassDatabase.isValidLocationForInstructor(instuctor, location, classType)
                && Location.setLocation(location) != null && checkMemberValidLocation(member, location)) {
            if(fitnessClassDatabase.isCheckedIn(member) && !isGuest) {
                return compareClassCheckIn(fitnessClassDatabase.getMemberClass(member), location,
                        instuctor, member, classType, fitnessClassDatabase);
            }
            return true;
        }
        else if(!FitnessClass.checkIfActualClass(classType)) {
            message += (classType + " class does not exist.\n");
        }
        else if(!member.getDateOfBirth().isValid()) {
            message += ("DOB " + member.getDateOfBirth().getDate() + " invalid calendar date!\n");
        }

        else if(!Instructor.isValidInstructor(instuctor)) {
            message += (instuctor + " - instructor does not exist\n");
        }
        else if(Location.setLocation(location) == null) {
            message += (location + " - invalid location.\n");
        }
        else if(!fitnessClassDatabase.isValidLocationForInstructor(instuctor, location, classType))
        {
            message += (String.format("%s by %s does not exist at %s\n", classType,
                    instuctor, location));
        }
        else if(memberList.find(member) == -1) {
            message += (String.format("%s %s is not in the gym member database\n",
                    member.getFname(), member.getLName()));
        }
        else if(!member.getExpiration().isValidExpiration()){
            message += ("Expiration date " + member.getExpiration().getDate() + ": invalid " +
                    "calendar date\n");
        }
        else if(member.getExpiration().isValidExpiration() && member.getExpiration().isPastButNotTodayOrPresent()){
            message += ("Expiration date " + member.getDateOfBirth().getDate() + ": " +
                    "has expired\n");
        }
        else{
            return true;
        }
        return false;
    }
    private boolean compareClassCheckIn(FitnessClass currentClass, String location,
                                        String instructor, Member member, String classType,
                                        ClassSchedule fitnessClassDatabase){
        if(Location.setLocation(location) == null)
        {
            message += (location + " - invalid location.\n");
            return false;
        }
        if(location.equalsIgnoreCase(currentClass.getLocation().toString()) && instructor.equalsIgnoreCase(currentClass.getInstructor().toString()) && classType.equalsIgnoreCase(currentClass.getClassType()))
        {
            message += (String.format("%s %s already checked in\n",member.getFname(),
                    member.getLName()));
            return false;
        }
        else if(fitnessClassDatabase.isValidLocationForInstructor(instructor, location, classType) && !fitnessClassDatabase.memberIsInClass(instructor, classType,location, member))
        {
            FitnessClass classToCompare = fitnessClassDatabase.getFitnessClass(instructor, location, classType);
            if(currentClass.getTime() == classToCompare.getTime())
            {
                message += (String.format("Time conflict - %s - %s, %s, %s, %s, %s\n",
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
}

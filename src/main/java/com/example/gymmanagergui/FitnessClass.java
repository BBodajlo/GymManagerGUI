package com.example.gymmanagergui;
import java.util.ArrayList;
/**
 * The FitnessClass creates databases for each of the fitness classes
 * and allows members to check in their desire fitness classes. FitnessClass
 * contains the name of the instructors, fitness class, and time of the classes.
 * Members in the classes can also choose to be dropped
 * and members in the classes can be printed.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class FitnessClass {

    private ArrayList<Member> participants;
    private ArrayList<Member> guests;

    private Instructor instructor;

    private Time time;

    private String classType;

    private Location location;

    private int classSize;

    /**
     * Initialize a fitness class using the class type, instructor, time, and location.
     * @param location The location.
     * @param instructor The name of the instructor.
     * @param classtype The type of class being offered.
     * @param time The time of the clas.
     */
    public FitnessClass(String classtype, Instructor instructor,
                        Time time, Location location)
    {
        this.classType = classtype;
        this.instructor = instructor;
        this.time = time;
        this.location = location;
        participants = new ArrayList<>();
        guests = new ArrayList<>();

    }

    /**
     * Compare if fitness classes are the same.
     * @param obj The class that is being compared.
     * @return Returns true if the fitness classes are the same,
     * otherwise return false.
     */
    @Override
    public boolean equals(Object obj)
    {
        FitnessClass fitnessClass = (FitnessClass)obj;

        if(this.getInstructor().equals(fitnessClass.getInstructor())
        && this.getLocation().equals(fitnessClass.getLocation())
        && this.getTime().equals(fitnessClass.getTime())
        && this.getClass().equals(fitnessClass.getClass()))
        {
            return true;
        }
        return false;

    }
    /**
     * Get the name of the instructor for the class.
     * @return Returns instructor name
     */
    public Instructor getInstructor()
    {
        return instructor;
    }
    /**
     * Get the time for the class.
     * @return Returns the time of the class
     */
    public Time getTime()
    {
        return time;
    }
    /**
     * Get the location of the gym.
     * @return The location
     */
    public Location getLocation()
    {
        return location;
    }
    /**
     * Get type of class being offered.
     * @return The class type.
     */
    public String getClassType()
    {
        return classType;
    }
    /**
     * Get the members participating in the class.
     * @return The members that are participating.
     */
    public ArrayList<Member> getParticipants()
    {
        return participants;
    }
    /**
     * Get the guests participating in the class.
     * @return The guests that are participating.
     */
    public ArrayList<Member> getGuests()
    {
        return guests;
    }
    /**
     * Add valid members into the database.
     * The database will increase in size when member is added.
     * @param member The member being added into the class.
     * @return Returns true if the member has been added
     */
    public boolean addToClass(Member member) {
        participants.add(member);
        return true;
    }
    /**
     * Add valid guests into the database.
     * The database will increase in size when guest is added.
     * @param member The guest being added into the class.
     * @return Returns true if the guest has been added
     */
    public boolean addToGuestClass(Member member)
    {
        guests.add(member);
        return true;
    }
    /**
     * Remove a member from the class if they are found.
     * @param member The member that is being removed.
     * @return Returns true if a member is removed, otherwise return false.
     */
    public boolean removeFromFitnessClass(Member member)
    {
        if(participants.contains(member))
        {
            participants.remove(member);
            return true;
        }
        return false;
    }
    /**
     * Remove a guest from the class if they are found.
     * @param member The guest that is being removed.
     * @return returns True if a guest is removed, otherwise return false.
     */
    public boolean removeGuestFromFitnessClass(Member member)
    {
        if(guests.contains(member))
        {
            guests.remove(member);
            return true;
        }
        return false;
    }
    /**
     * Check if the name of the fitness class that a member wants to take
     * is a valid one.
     * @param classType The name of a class that a member wants to take.
     * @return Returns true if the member is taking Pilates, Spinning, or Cardio.
     * Otherwise, returns false.
     */
    public static boolean checkIfActualClass(String classType)
    {
        if(classType.toLowerCase().equals("pilates")
                || classType.toLowerCase().equals("spinning")
                || classType.toLowerCase().equals("cardio"))
        {
            return true;
        }
        return false;
    }
    /**
     * Check if a member is in the fitness class.
     * @param person The member that is being checked for.
     * @return Returns True if the member is in the class, otherwise return false.
     */
    public boolean isInFitnessClass(Member person)
    {
        if((this).participants == null)
        {
            return false;
        }
        else {
            for(Member m: participants) {
                if (m.equals(person)) {
                    return true;
                }

            }
        }
        return false;
    }
    /**
     * Check if a guest is in the fitness class.
     * @param person The guest that is being checked for.
     * @return Returns True if the guest is in the class, otherwise return false.
     */
    public boolean isGuestInFitnessClass(Member person)
    {
        if((this).guests == null)
        {
            return false;
        }
        else {
                for(Member m: guests)
                {
                    if(m.equals(person))
                    {
                        return true;
                    }

                }
        }
        return false;
    }
}
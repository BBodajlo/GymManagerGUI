package com.example.gymmanagergui;
/**
 * The ClassSchedule class manages the fitness classes; along with the
 * members and guests. Guest and members can be added or removed from a fitness class
 * as well as check whether the individuals are in a fitness class.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;

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
}

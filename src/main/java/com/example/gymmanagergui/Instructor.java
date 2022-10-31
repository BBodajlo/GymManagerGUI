package com.example.gymmanagergui;
/**
 * The names of all the fitness classes instructors.
 */
public enum Instructor {
    /**
     * Instructor of Pilates class.
     */
    JENNIFER,
    /**
     * Instructor of Spinning class.
     */
    DENISE,
    /**
     * Instructor of cardio class.
     */
    KIM,
    DAVIS,
    EMMA;



    /**
     * Takes a string and sees if it is equal to a valid instructor
     * @param instructor String representation of an instructor
     * @return True if the string is equals to an instructor name. False if the string is not any
     * of the instructor enums
     */
    public static boolean isValidInstructor(String instructor)
    {
        if(instructor.toLowerCase().equals("jennifer"))
        {
            return true;
        }
        else if(instructor.toLowerCase().equals("denise"))
        {
            return true;
        }
        else if(instructor.toLowerCase().equals("kim"))
        {
            return true;
        }
        else if(instructor.toLowerCase().equals("davis"))
        {
            return true;
        }
        else if(instructor.toLowerCase().equals("emma"))
        {
            return true;
        }
        return false;
    }
}

package com.example.gymmanagergui;
/**
 * Family class creates members that can consist of information
 * such as, their first name, last name, date of birth,
 * membership expiration date, location and guest passes.
 * Family is a child of the member class
 * @author Blake Bodajlo, Stanley Jiang
 */
public class Family extends Member{

    private int guestPasses;
    private static final int QUARTERLY = 3;


    /**
     * Initializes a member's first name, last name, dob, and location using the super constructor
     * Uses the super's inplementation of setting the expiration date to the future
     * Sets the guest passes to one
     *
     * @param fname    First Name
     * @param lname    Last Name
     * @param dob      Date of birth
     * @param location Location of gym
     */
    public Family(String fname, String lname, Date dob
            , Location location)
    {
        super(fname, lname, dob, location);
        setExpiration();
        guestPasses = 1;
    }

    /**
     * Calculates the membership fee based on the family fee
     * @return The membership fee for based on the membership type
     */
    @Override
    public double memberShipFee()
    {
        double oneTimeFee = 29.99;
        double perMonth = 59.99;

        return(oneTimeFee + (perMonth*QUARTERLY));
    }

    /**
     * Gives the amount of guestPasses remaining
     * @return Integers values of guest passes remaining
     */
    public int getGuestPasses()
    {
        return guestPasses;
    }

    public void setGuestsPasses(int i)
    {
        guestPasses = i;
    }
    /**
     * Decrements the guestPasses by one when ever a guest member is checked in
     */
    public void useGuestPass()
    {
        this.guestPasses--;
    }

    /**
     * Adds to the guest pass when a guest checks out of a class
     */
    public void addGuestPass()
    {
        this.guestPasses++;
    }

}

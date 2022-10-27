package com.example.gymmanagergui;
/**
 * Premium class creates members that can consist of information
 * such as, their first name, last name, date of birth,
 * membership expiration date, location and guest passes.
 * Premium is a child of the family class
 * @author Blake Bodajlo, Stanley Jiang
 */


public class Premium extends Family{

    private static final int YEARLY = 12;


    /**
     * Initializes a member's first name, last name, dob, and location using the super constructor
     * Uses the super's inplementation of setting the expiration date to the future
     * Sets the guest passes to three using method defined in family
     *
     * @param fname    First Name
     * @param lname    Last Name
     * @param dob      Date of birth
     * @param location Location of gym
     */
    public Premium(String fname, String lname, Date dob, Location location)
    {
        super(fname, lname, dob, location);
        super.setGuestsPasses(3);
    }




    /**
     * Calculates the membership fee based on the premium fee
     * @return The membership fee for based on the membership type
     */
    @Override
    public double memberShipFee()
    {
        double oneTimeFee = 0;
        double perMonthFee = 59.99;

        return(oneTimeFee + (perMonthFee*YEARLY) - perMonthFee);
    }

    /**
     * Sets a newly initialized premium member to an expiration date that is one year from the
     * current day; premium membership has a different expiration procedure from its parents
     */
    @Override
    public void setExpiration()
    {
        Date threeMonths = new Date();
        this.getExpiration().getTwelveMonths();
    }



}

package com.example.gymmanagergui;
/**
 * Member class creates members that can consist of information
 * such as, their first name, last name, date of birth,
 * membership expiration date, and location.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;
    private static final int QUARTERLY = 3;

    /**
     * Initialize a default member named John Doe from Bridgewater.
     */
    public Member() {
        fname = "John";
        lname = "Doe";
        dob = new Date();
        expire = new Date();
        location = Location.BRIDGEWATER;
    }

    /**
     * Initialize a member with a first name, last name, and date of birth.
     *
     * @param dob   Date of birth
     * @param fname First name
     * @param lname Last Name
     */
    public Member(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Initialize a member with a first name, last name, and date of birth,
     * membership expiration date, and location.
     *
     * @param fname    First Name
     * @param lname    Last Name
     * @param dob      Date of birth
     * @param expire   Gym membership expiration date
     * @param location Location of gym
     */
    public Member(String fname, String lname, Date dob, Date expire
            , Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    public Member(String fname, String lname, Date dob
            , Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = new Date();
        setExpiration();
        this.location = location;
    }


    /**
     * Give a string representation of the member's data.
     * The information is formatted in the order of First name, Last
     * name, Date of Birth, Gym membership expiration, and their
     * Location including the zip code and county
     *
     * @return String type formatted with the member's data
     */
    @Override
    public String toString() {
        String textualMember = String.format("%s %s, DOB: " + "%s," +
                        " Membership expires %s, Location: %s, %s, " +
                        "%s", this.fname, this.lname, this.dob.getDate(),
                this.expire.getDate(), this.location,
                this.location.getZIP(),
                this.location.getCOUNTY());
        return textualMember;
    }

    /**
     * Give a string representation of the member's data.
     * The information is formatted in the order of First name, Last
     * name, Date of Birth, Gym membership expiration, and their
     * Location including the zip code and county and membership fee
     *
     * @return String type formatted with the member's data
     */
    public String toStringWFee() {
        String textualMember = "";
        if (!this.getExpiration().isPastButNotTodayOrPresent()) {
            textualMember = String.format("%s %s, DOB: " + "%s," +
                            " Membership expires %s, Location: %s, %s, " +
                            "%s, Membership fee: %.2f", this.getFname(), this.getLName(),
                    this.getDateOfBirth().getDate(),
                    this.getExpiration().getDate(), this.getLocation(),
                    this.getLocation().getZIP(),
                    this.getLocation().getCOUNTY(), this.memberShipFee());
        } else {
            textualMember = String.format("%s %s, DOB: " + "%s," +
                            " Membership expired %s, Location: %s, %s, " +
                            "%s, Membership fee: %.2f", this.getFname(), this.getLName(),
                    this.getDateOfBirth().getDate(),
                    this.getExpiration().getDate(), this.getLocation(),
                    this.getLocation().getZIP(),
                    this.getLocation().getCOUNTY(), this.memberShipFee());


        }
        return textualMember;
    }

    /**
     * Compares two members equality through having the same first
     * name, last name, and date of birth.
     * Members are considered equal if they have the same names and
     * dates of birth meaning they are the same person.
     * The object passed is cast into a member object.
     *
     * @param obj Expected to be a member object and is cast to
     *            compare the two members.
     * @return True if they have the same names, and dates of
     * birth. False if one of their first name, last name, or date
     * is different.
     */
    @Override
    public boolean equals(Object obj) {
        Member otherMember = (Member) obj;

        if (this.fname.toLowerCase().equals(otherMember.getFname().toLowerCase())
                && this.lname.toLowerCase().equals(otherMember.getLName().toLowerCase())
                && this.compareDateOfBirth(otherMember.getDateOfBirth())) {
            return true;
        }
        return false;
    }

    /**
     * Compares the last names and first names of two members.
     * Will check to see if they are equal first. Then will check
     * to see if just the last names are equal and compare first
     * names. Then will just compare the last names. The order is
     * supposed to be compared last names, then first names.
     *
     * @param member Used to compare last and first names
     * @return Returns 0 if the two names are the same. Returns
     * less than 0 if the super member's name is after the other's
     * name.
     * Returns greater than 0 if the super member's name is before
     * the other's
     * name.
     */
    @Override
    public int compareTo(Member member) {
        if (this.compareLastNames(member.getLName()) == 0
                && this.compareFirstNames(member.getFName()) == 0) {
            return 0;
        } else if (this.compareLastNames(member.getLName()) == 0) {
            return this.compareFirstNames(member.getFName());
        } else {
            return this.compareLastNames(member.getLName());
        }
    }

    /**
     * Get the membership expiration date of the member.
     *
     * @return Returns the expiration date.
     */
    public Date getExpiration() {
        return expire;
    }

    /**
     * Get the first name of the member.
     *
     * @return Returns the first name.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Get the first name of the member.
     *
     * @return Returns the first name.
     */
    private String getFName() {
        return this.fname;
    }

    /**
     * Get the last name of the member.
     *
     * @return Returns the last name.
     */
    public String getLName() {
        return this.lname;
    }

    /**
     * Get the date of birth of the member.
     *
     * @return Returns the date of birth.
     */
    public Date getDateOfBirth() {
        return this.dob;
    }

    /**
     * Gives the membership fee based on their "member" status
     * Both onetimefee and permonth are set based on the
     * inheritance chain of membership
     *
     * @return The onetimefee plus the three months of the
     * permonth fee
     */
    
    public double memberShipFee() {
        double oneTimeFee = 29.99;
        double perMonth = 39.99;

        return (oneTimeFee + perMonth * QUARTERLY);
    }

    public void setExpiration() {
        Date threeMonths = new Date();
        this.expire = threeMonths.getThreeMonths();
    }

    /**
     * Checks to see of the first names are equal to one another.
     * Used in the equals method of member.
     * @param fname
     * @return True if the names are equal. False if the names are
     * not equal.
     */private int compareFirstNames(String fname)
    {
        return this.fname.compareTo(fname);
    }
    /**
     * Checks to see of the last names are equal to one another.
     * Used in the equals method of member.
     * @param lname
     * @return True if the names are equal. False if the names are
     * not equal.
     */
    private int compareLastNames(String lname)
    {
        return this.lname.compareTo(lname);
    }

    /**
     * Checks to see if the two dates are equal to one another.
     * Used in the equal method of member.
     * @param dob
     * @return True if the two dates are equal. False if the two
     * dates are different.
     */
    private  boolean compareDateOfBirth(Date dob)
    {
        if(this.dob.compareTo(dob) != 0)
        {
            return false;
        }
        return true;
    }

    /**
     * Get the location of the member.
     * @return Returns location.
     */
    public Location getLocation()
    {
        return location;
    }


    public static void main(String[] args)
    {
        Member carl = new Member("Carl","Brown", new Date());
        Member jane = new Member("Jane","Doe", new Date());

        //Test 1: compareTo
        //Brown is alphabetically before Doe; should return < 0
        System.out.println(carl.compareTo(jane));


        Member john = new Member("John","Phil", new Date());

        //Test 2: compareTo
        //Phil is alphabetically after Doe; should return > 0
        System.out.println(john.compareTo(jane));

        Member roy = new Member("Roy","Brooks", new Date());

        //Test 3: compareTo
        //Brooks is alphabetically the same as Brooks; should return 0
        System.out.println(roy.compareTo(roy));

        john = new Member("John","Doe", new Date());

        //Test 4: compareTo
        //John is alphabetically the after Jane; should return > 0
        System.out.println(john.compareTo(jane));

        //Test 5: compareTo
        //Jane is alphabetically before John; should return < 0
        System.out.println(jane.compareTo(john));

        for(int i = 0; i < 3; i++)
        {
            System.out.println("Hello");
            System.out.println("Goodbye");
        }






    }

    public void useGuestPass() {
    }



}

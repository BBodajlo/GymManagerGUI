package com.example.gymmanagergui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MemberDatabase class creates a database comprised of a member array and the size.
 * The object creates a default member array of size 4.
 * The class includes functionality such as printing the members in the array as is,
 * by name, by expiration, and by county.
 * Members can be added, removed, and found from the MemberDatabase.
 * The member array will grow by size 4 once it has hit maximum capacity.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class MemberDatabase {
    private Member[] mlist;
    private int size;
    private static final int NOT_FOUND = -1;

    /**
     * Initializes the Member Database array
     * to an initial size of 4.
     */
    public MemberDatabase() {
        size = 4;
        this.mlist = new Member[size];
    }

    /**
     * Print the members in the array as is.
     */
    public String print() {
        String message = "\n";
        for (Member member : mlist) {
            if (member != null)
                message += (member.toString() + "\n");
        }
        return message;
    }

    /**
     * Print the members in the array as is but with fee.
     */
    public String printWFee() {
        String message = "\n";
        for (Member member : mlist) {
            if (member != null) {
                message += (member.toStringWFee() + "\n");
                if(member instanceof Premium) {
                    message += ("(Premium) guest-pass remaining: " + ((Premium) member).getGuestPasses() + "\n");
                }
                else if (member instanceof Family)
                {
                    message += ("(Family) guest-pass remaining: " + ((Family) member).getGuestPasses() + "\n");
                }

            }
        }
        return message;
    }
    /**
     * Print the members in the array by name.
     */
    public String printByName()
    {
        //String message = null;
        Member temporaryMember = new Member();
        int index;
        for(int i = 0; i<this.getNumberOfMembers(); i++)
        {
            index = i;
            {
                for(int j = i+1; j< this.getNumberOfMembers(); j++)
                {
                    if(mlist[index].compareTo(mlist[j]) > 0)
                        index = j;
                }
                temporaryMember = mlist[index];
                mlist[index] = mlist[i];
                mlist[i] = temporaryMember;
            }
        }
        return this.print();
    }
    /**
     * Print the members in the array by membership expiration date.
     */
    public String printByExpiration()
    {
        Member temporaryMember = new Member();
        int index;
        for(int i = 0; i<this.getNumberOfMembers(); i++)
        {
            index = i;
            {
                for(int j = i+1; j< this.getNumberOfMembers(); j++)
                {
                    if(mlist[index].getExpiration().compareTo(mlist[j].getExpiration()) > 0)
                        index = j;
                }
                temporaryMember = mlist[index];
                mlist[index] = mlist[i];
                mlist[i] = temporaryMember;
            }
        }
        return this.print();
    }
    /**
     * Print the members in the array by County.
     */
    public String printByCounty()
    {
        Member temporaryMember = new Member();
        int index;
        for(int i = 0; i<this.getNumberOfMembers(); i++)
        {
            index = i;
            {
                for(int j = i+1; j< this.getNumberOfMembers(); j++)
                {
                    if(mlist[index].getLocation().compareCounty(mlist[j].getLocation()) > 0)
                        index = j;
                }
                temporaryMember = mlist[index];
                mlist[index] = mlist[i];
                mlist[i] = temporaryMember;
            }
        }
        return this.print();
    }
    /**
     * Print the members in the array by fee.
     */
    public void printByFee()
    {
        Member temporaryMember = new Member();
        int index;
        for(int i = 0; i<this.getNumberOfMembers(); i++)
        {
            index = i;
            {
                for(int j = i+1; j< this.getNumberOfMembers(); j++)
                {
                    if(mlist[index].getLocation().compareCounty(mlist[j].getLocation()) > 0)
                        index = j;
                }
                temporaryMember = mlist[index];
                mlist[index] = mlist[i];
                mlist[i] = temporaryMember;
            }
        }
        this.printWFee();
    }
    /**
     * Find if given a member is in the array by comparing
     * the name and date of birth to the members in the array.
     * @param member used as the reference for comparison
     * @return Returns the index of the member that was found in the array.
     * Returns -1 stylized as NOT_FOUND if the member was not found in the array.
     */
    public int find(Member member) {
        for (int i = 0; mlist.length > i; i++) {
            if (mlist[i] != null && mlist[i].equals(member)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Adds a member into member database,
     * if the database is full increase the size of the database and then add the member.
     * @param member The member that is being added.
     * @return Returns true when the member has been added.
     */
    public boolean add(Member member) {
        int index = 0;
        for(int i = 0; i< size; i++)
        {
            if(mlist[i] == null)
            {
                index = i;
                break;
            }
            if(i == size-1)
            {
                this.grow();
                mlist[i+1] = member;
                return true;
            }
        }
        mlist[index] = member;
        return true;
    }

    /**
     * Remove a member from the member database if the member is in the database.
     * @param member The member that is being removed.
     * @return Returns true if the member is removed, else returns false.
     */
    public boolean remove(Member member)
    {
        int index = 0;
        for (Member orginal : mlist)
        {
            if (orginal.equals(member))
            {
                mlist[index] = null;
                this.removeHelper();
                return true;
            }
            index++;
        }
        return false;

    }
    /**
     * Used by the remove method to move members into the correct
     * position after removing.
     */
    private void removeHelper()
    {
        int index = 0;
        boolean foundNull = false;
        for(int i = 0; i<size; i++)
        {

            if(foundNull){
                mlist[i-1] = mlist[i];
            }
            if(mlist[i] == null && foundNull == false)
            {
                foundNull = true;
            }
            if(mlist[i] == null && foundNull)
            {
                mlist[i] = null;
                break;
            }
        }

    }
    /**
     * Used by the print methods to find number of members in the members' database.
     * @return Return the number of members in the database.
     */
    private int getNumberOfMembers()
    {
        int count = 0;
        for(int i = 0; i<mlist.length; i++)
        {
            if(mlist[i] != null)
            {
                count++;
            }
        }
        return count;
    }
    /**
     * Used when the member database hits maximum capacity, this method
     * grows the size of the database by the size of 4 each time.
     */
    private void grow()
    {
        Member[] holder = new Member[size];
        for(int i = 0; i<size; i++)
        {
            holder[i] = mlist[i];
        }
        this.mlist = new Member[size+4];
        for(int i = 0; i<size; i++)
        {
            mlist[i] = holder[i];
        }
        this.size= this.size+4;
    }/**
     * Get the member database.
     * @return Return the member database.
     */
    public Member[] getMlist()
    {
        return this.mlist;
    }
    /**
     * Find the number of members in the members' database.
     * @return Return the number of members in the database.
     */
    public int getNumOfMembers()
    {
        int membersInDatabase = 0;
        for(Member m: mlist)
        {
            if(m != null)
            {
                membersInDatabase++;
            }
        }
        return membersInDatabase;
    }

    public void addMember(File members) throws FileNotFoundException {
        String[] person;
        Scanner input = new Scanner(members);
        while(input.hasNextLine()) {
            person = input.nextLine().split(" ");
            if(!person[0].equals(""))
            {

                String firstName = person[0];
                String lastName = person[1];
                Date dob = new Date(person[2], true);
                Date expiration = new Date(person[3], true);
                Location location = Location.setLocation(person[4]);
                System.out.println(String.format("%s %s %s %s",firstName,lastName,
                        dob.getDate(), expiration.getDate()));
                Member newPerson = new Member(firstName, lastName, dob,
                        expiration,location);
                if(dob.isValid() && dob.isAdult() && location != null
                        && this.find(newPerson) == -1 && expiration.isValidExpiration()){
                    this.add(newPerson);
                }
            }
        }
    }

}









package com.example.gymmanagergui;
import java.time.Year;
import java.util.Calendar;


/**
 * Date class creates a date object comprised of a day, month, and
 * year integer values.
 * The object can be created to any date or can be defaulted to the
 * current date based on the constructors. It includes
 * functionality to compare dates to one another in chornological
 * order, as well as its validity in terms of the gym manager
 * system. Its validity is any date after the current as well as
 * having accurate days and months. Basic get methods for the three
 * fields are provided.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUAD_YEAR = 4;
    public static final int THIRTY_DAYS = 30; //for comparisons
    // involving months with 30 days
    public static final int THIRTY_ONE_DAYS = 31; //for comparisons
    // involving months with 31 days
    public static final int CURRENTYEAR =
            Calendar.getInstance().get(Calendar.YEAR);//constant to
    // identify the current year on program start up to eliminate
    // creating more objects and improve code clarity
    public static final int CURRENTMONTH =
            Calendar.getInstance().get(Calendar.MONTH) + 1;
    public static final int CURRENTDAY =
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static final int YEAR_MIN = 1;
    public static final int YEAR_MAX = 12;
    /**
     * Uninitialized dates get set to the current date by default
     */
    public Date() {
        //Uses a calendar object to get the system date
        Calendar today = Calendar.getInstance();
        //Uses the calendar's fields to set current instance variables
        this.day = today.get(Calendar.DAY_OF_MONTH);
        this.month = today.get(Calendar.MONTH) + 1;
        this.year = today.get(Calendar.YEAR);
    }
    /**
     * Initializes the date to the date the user passes
     *
     * @param date the date is expected to be formatted xx/xx/xxxx
     */
    public Date(String date) {
        String[] holder = new String[3];
        holder = date.split("-");
        this.year = Integer.parseInt(holder[0]);
        this.month = Integer.parseInt(holder[1]);
        this.day = Integer.parseInt(holder[2]);
    }

    /**
     * Chronologically Compares one date with the date that is passed
     * The one calling this method takes priority in the output
     *
     * @param date date used as a reference to compare
     * @return Returns 1: if original date is after the parameter
     * Returns -1: if original date is before the parameter
     * Returns: 0 if they are the same date
     */
    @Override
    public int compareTo(Date date) {
        if (this.checkYear(date) > 0)
            return 1;
        if (this.checkYear(date) < 0)
            return -1;
        if (this.checkYear(date) == 0) {
            if (this.checkMonth(date) > 0) {
                return 1;
            } else if (this.checkMonth(date) < 0) {
                return -1;
            }
        }
        if (this.checkMonth(date) == 0) {
            if (this.checkDay(date) > 0) {
                return 1;
            } else if (this.checkDay(date) < 0) {
                return -1;
            }
        }
        return 0; //Means the two dates are equal as all other
    }

    /**
     * Verifies that the date is after the current and that the
     * correct days correspond to the correct months.
     * This is a catch-all that verifies the year is either the
     * current or future. The month is future or current. And the
     * days are correctly matched to the months (30 or 31) where
     * the leap year is also accounted for.
     *
     * @return Returns True if the date is after the current date.
     * Returns False if the date is before the current date, is the
     * current date, the days don't correspond to the months,
     * either day month or year is in a valid range.
     */
    public boolean isValid() {
        if (!this.checkValidDay() || !this.checkValidYear() || !this.checkValidMonth()) {
            return false;
        }
        return true;
    }
    /**
     * Verify if the expiration is a valid date.
     * @return Returns false if the day or month is not valid.
     * Otherwise, return true.
     */
    public boolean isValidExpiration() {
        if(!this.checkValidDay() || !this.checkValidMonth()) {
            return false;
        }
        return true;
    }

    /**
     * A get method to return the day of the date
     * @return integer representation of the day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * A get method to return the month of the date
     * @return integer representation of the month (JAN = 1; DEC = 12)
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * A get method to return the year of the date
     * @return integer representation of the year with 4 digits
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Used by the compareTo method to determine if the original
     * day is before, after, or the same as the one passed
     * @return Returns 1 if the original day is after
     * Returns -1 if the original day is before
     * Returns 0 if they are the same day
     */
    public String getDate() {
        return String.format("%d/%d/%d", this.getMonth(),
                this.getDay(), this.getYear());
    }
    /**
     * Used to check if the day is before, after or the same as the one passed.
     * @param date used as the reference for comparison
     * @return Returns 0 if the days equal. Returns -1 if this day is less than the day given.
     * Returns 1 is this day is greater than the day given.
     */
    private int checkDay(Date date) {
        if (this.day > date.day) {
            return 1;
        } else if (this.day < date.day) {
            return -1;
        }
        return 0; //returns 0 if equal
    }

    /**
     * Used by the compareTo method to determine if the original
     * day is before, after, or the same as the one passed
     * @param date used as the reference for comparison
     * @return Returns 1 if the original month is after
     * Returns -1 if the original month is before
     * Returns 0 if they are the same month
     */
    private int checkMonth(Date date) {
        if (this.month > date.month) {
            return 1;
        } else if (this.month < date.month) {
            return -1;
        }
        return 0; //returns 0 if equal
    }

    /**
     * Used by the compareTo method to determine if the original
     * year is before, after, or the same as the one passed
     *
     * @param date used as the reference for comparison
     * @return Returns 1 if the original year is after
     * Returns -1 if the original year is before
     * Returns 0 if they are the same year
     */
    private int checkYear(Date date) {
        if (this.year > date.year) {
            return 1;
        } else if (this.year < date.year) {
            return -1;
        }
        return 0; //returns 0 if equal
    }

    /**
     * Used by isValid to determine if the month is JAN->DEC
     *
     * @return Returns True if the given date has a valid month
     * number between 1-12 (JAN - DEC)
     * Returns false if the month given is below 1 or above 12
     */
    private boolean checkValidMonth() {
        if (this.month < YEAR_MIN || this.month > YEAR_MAX)
            return false;
        return true;
    }

    /**
     * Used by isValid to determine if the given day fits in the
     * month and that it is not 0 or less
     * Also handles the leap years as well as the given days for them
     *
     * @return Returns True if the day matches the month; JAN is 1-31
     * days; JUN is 1-30 days; leap years are 29, and other FEB is 28
     * Returns false if the days are out of range of the months
     */
    private boolean checkValidDay() {

        if (this.month == MonthDays.JAN.getMonthNum()
                || this.month == MonthDays.MAR.getMonthNum()
                || this.month == MonthDays.MAY.getMonthNum()
                || this.month == MonthDays.JUL.getMonthNum()
                || this.month == MonthDays.AUG.getMonthNum()
                || this.month == MonthDays.OCT.getMonthNum()
                || this.month == MonthDays.DEC.getMonthNum()) {
            return (this.day <= THIRTY_ONE_DAYS && this.day > 0);
        } else if (this.month == MonthDays.APR.getMonthNum()
                || this.month == MonthDays.JUN.getMonthNum()
                || this.month == MonthDays.SEP.getMonthNum()
                || this.month == MonthDays.NOV.getMonthNum()) {
            return (this.day <= THIRTY_DAYS && this.day > 0);
        } else if (this.month == MonthDays.FEB.getMonthNum()) {
            if (this.checkLeapYear() && this.day <= 29 && this.day > 0) {
                return true;
            } else if (!this.checkLeapYear() && this.day <= 28 && this.day > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used by isValid to determine if the given year is the
     * current or future
     * Valid years cannot be ones prior to the current
     *
     * @return Returns True if the year is the current year or any
     * after
     * Returns false for years before the current year
     */
    private boolean checkValidYear() {
        if(year < 0)
        {
            return false;
        }
        else if (this.year <= CURRENTYEAR)
            return true;
        return false;
    }

    /**
     * Used by checkValidDay to determine if the correct range of
     * FEB is used by calculating if the given year is a leap year
     * Leap years have 29 days and any other have 28 days
     *
     * @return Returns True if the given year is a leap year
     * Returns False if the given year is not a leap year
     */
    private boolean checkLeapYear() {
        if (this.year % 4 == 0) {
            if (this.year % 100 == 0) {
                if (this.year % 400 == 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * Used to determine if the given date is before today and
     * not a future date.
     * Valid dates cannot be today or the future so the
     * years, then months, then days are checked to make sure.
     *
     * @return Returns True if the given date is not today or the future.
     * Returns false if otherwise.
     */
    public boolean isPastButNotTodayOrPresent() {
        if (CURRENTYEAR < this.year) {
            return false;
        }  else if (CURRENTYEAR == this.year) {
            if (CURRENTMONTH - this.month < 0) {
                return false;
            }
            if (CURRENTMONTH - this.month > 0) {
                return true;
            }
            if (CURRENTMONTH - this.month == 0) {
                if (CURRENTDAY - this.day > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Used to check if a person is 18 years or older, by comparing
     * their date of birth to today.
     *
     * @return Returns True if date of birth is greater than 18 years old,
     * else returns false if it is under 18 years old.
     */
    public boolean isAdult()
    {
        if(CURRENTYEAR - this.year < 18)
        {
            return false;
        }
        else if(CURRENTYEAR - this.year == 18)
        {
            if(CURRENTMONTH - this.month > 0)
            {
                return true;
            }
            if(CURRENTMONTH - this.month < 0)
            {
                return false;
            }
            if(CURRENTMONTH - this.month == 0)
            {
                if(CURRENTDAY - this.day >= 0)
                {
                    return false;
                }
                if(CURRENTDAY - this.day < 0)
                {
                    return true;
                }
            }


        }
        return true;
    }
    /**
     * Increase the month by 3 months.
     * @return Return the month by 3 months later.
     */
    public Date getThreeMonths()
    {
        int newMonth;
        if(this.month+3 > YEAR_MAX)
        {
            newMonth = (month+3)%YEAR_MAX;
            this.year++;
            this.month = newMonth;
            if(!this.isValid())
            {
                this.month++;
                this.day = 1;
            }
        }
        else
        {
                this.month = this.month+3;
                if(!this.isValid())
                {
                    this.month++;
                    this.day = 1;
                }
        }

        return this;
    }
    /**
     * Increase the year by 12 months.
     * @return Return the year by 12 months later.
     */
    public Date getTwelveMonths()
    {
        this.year++;
        return this;
    }
    public static void main(String[] args) {

        //isValid tests for a proper calendar date

        //Test 1: Testing leap year checking
        //Date1 is not a leap year containing a leap year index for
        //february; the result should be false
        Date date1 = new Date("2/29/2018");
        System.out.println(("Test 1: " + date1.isValid()));
        //Test 2: Testing leap year checking
        //Date2 is a leap year containing a leap year index for
        //february; the result should be true
        Date date2 = new Date("2/29/2020");
        System.out.println(("Test 2: " + date2.isValid()));
        //Test 3: Testing isValid
        //Date3 has a negative number which is not valid for a
        //date; the result should be true
        Date date3 = new Date("-1/31/2003");
        System.out.println(("Test 3: " + date3.isValid()));
        //Test 4: Testing isValid
        //Date4 has 31 days for a 30 day month; the result should be
        // false
        Date date4 = new Date("4/31/2003");
        System.out.println(("Test 4: " + date4.isValid()));
        //Test 5: Testing isValid
        //Date5 has a negative number for days;
        // the result should be false
        Date date5 = new Date("1/-2/2003");
        System.out.println(("Test 5: " + date5.isValid()));
        //Test 6: Testing isValid
        //Date6 has a negative number for years;
        // the result should be false
        Date date6 = new Date("10/1/-2020");
        System.out.println(("Test 6: " + date6.isValid()));



        Date date8 = new Date("5/31/2023");
        System.out.println(("Test 8: " + date8.isValid()));

        Date date7 = new Date("11/31/2000");
        date7.getThreeMonths();
        System.out.println(date7.getDate());




    }
}


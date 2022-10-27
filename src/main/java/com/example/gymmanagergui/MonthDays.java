package com.example.gymmanagergui;
/**
 *  Holds the value of each month of a year.
 *  {@link #JAN}
 *  {@link #FEB}
 *  {@link #MAR}
 *  {@link #APR}
 *  {@link #MAY}
 *  {@link #JUN}
 *  {@link #JUL}
 *  {@link #AUG}
 *  {@link #SEP}
 *  {@link #OCT}
 *  {@link #NOV}
 *  {@link #DEC}
 */
public enum MonthDays {
    /**
     * Month of January.
     */
    JAN(1),
    /**
     * Month of February.
     */
    FEB(2),
    /**
     * Month of March.
     */
    MAR(3),
    /**
     * Month of April.
     */
    APR(4),
    /**
     * Month of May.
     */
    MAY(5),
    /**
     * Month of June.
     */
    JUN(6),
    /**
     * Month of July.
     */
    JUL(7),
    /**
     * Month of August.
     */
    AUG(8),
    /**
     * Month of September.
     */
    SEP(9),
    /**
     * Month of October.
     */
    OCT(10),
    /**
     * Month of November.
     */
    NOV(11),
    /**
     * Month of December.
     */
    DEC(12);


    private final int day;

    /**
     * Set the integer value of the month.
     * @param day Takes an integer value and set it to the month.
     */
    MonthDays(int day) {
        this.day = day;
    }
    /**
     * Get the integer form of the month.
     * @return day Returns the month in integer form
     */
    public int getMonthNum()
    {
        return day;
    }

    public static void main (String[] args)
    {

        System.out.println(MonthDays.JAN.day);
    }
}

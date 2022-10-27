package com.example.gymmanagergui;
/**
 *  Time of the fitness classes, formatted by hh:mm.
 *  {@link #MORNING}
 *  {@link #AFTERNOON}
 */
public enum Time {
    /**
     * The time of Pilates class.
     */
    MORNING(9, 30),
    /**
     * The time of Spinning and Cardio class.
     */
    AFTERNOON(14,0),
    /**
     * The time of Spinning and Cardio class.
     */
    EVENING(18,30);



    private final int hour;
    private final int minutes;

    /**
     * Constructor that sets the time by hour and minute.
     * @param h Set the hour
     * @param m Set the minute
     */
    Time(int h, int m)
    {
        this.hour = h;
        this.minutes = m;
    }
    /**
     * @return Current hour.
     */
    public int getHour()
    {
        return this.hour;
    }
    /**
     * @return Current minute.
     */
    public int getMinutes()
    {
        return this.minutes;
    }
    /**
     * @return Current time formatted by hh:mm.
     */
    public String getTime()
    {
        String time = String.format(hour + ":" + "%1$02d", minutes);
        return time;
    }

    public static void main(String[] args)
    {
        //System.out.println(Time.MORNING.hour);
        System.out.println(Time.MORNING.getTime());
    }

}

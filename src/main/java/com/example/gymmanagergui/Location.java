package com.example.gymmanagergui;
/**
 *  Locations of the gyms with zip and county
 *  {@link #BRIDGEWATER}
 *  {@link #EDISON}
 *  {@link #FRANKLIN}
 *  {@link #PISCATAWAY}
 *  {@link #SOMERVILLE}
 */
public enum Location {
    /**
     * Bridgewater Location
     */
    BRIDGEWATER("08807", "SOMERSET"),
    /**
     * Edison Location
     */
    EDISON("08837", "MIDDLESEX"),
    /**
     * Franklin Location
     */
    FRANKLIN("08873", "SOMERSET"),
    /**
     * Piscataway Location
     */
    PISCATAWAY("08854", "MIDDLESEX"),
    /**
     * Somerville Location
     */
    SOMERVILLE("08876", "SOMERSET");

    private  final String ZIP;
    private final String COUNTY;

    /**
     * Set the Zip and County of the Location
     * @param z Used to input the zip
     * @param c Used to input the county
     */
    Location(String z, String c)
    {
        this.ZIP = z;
        this.COUNTY = c;
    }


    /**
     * Get the Zip code for the location
     * @return The zip code
     */
    public String getZIP()
    {
        return ZIP;
    }
    /**
     * Get the County for the location
     * @return The County
     */
    public String getCOUNTY()
    {
        return  COUNTY;
    }
    /**
     * Check if a person location is Bridgewater, Edison,
     * Franklin, Piscataway, or Somerville.
     * @param otherLocation The Location that is being compared.
     * @return Returns less than 0 if the original is
     * lexicographically after
     * the other
     * Returns 0 if the otherLocation is equal to the locations listed.
     * Returns greater than 0 if the original is lexicographically
     * before the
     * other
     */
    public int compareCounty(Location otherLocation)
    {
        if(this.COUNTY.compareTo(otherLocation.COUNTY) == 0)
        {
            return this.ZIP.compareTo(otherLocation.ZIP);
        }
        return this.COUNTY.compareTo(otherLocation.COUNTY);

    }
}

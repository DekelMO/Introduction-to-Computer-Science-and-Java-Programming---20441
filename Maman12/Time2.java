/**
 * Represents time - hours:minutes by the number of mionutes pass since midnight. 
 * Values must represent a proper time.
 * 
 * @author Dekel Moens
 * @version 27.03.2022
 */
public class Time2
{
    // variable
    private int _minFromMid;// the amount from minutes pass since midnight

    private final int MIN_TIME = 0 , MAX_HOUR = 23, MAX_MINUTE = 59; // Time cannot go below 0 or above 23hours/59minutes
    private final int MINUTES_IN_HOUR = 60, MINUTES_IN_DAY = 1440, TWO_DIGITS = 10; // 10 is the first two digits number

    // constructors
    /**
     * Constructs a Time2 object.
     * Construct a new time instance with the specified hour and minute.
     * Hour should be between 0-23, otherwise it should be set to 0. 
     * Minute should be between 0-59, otherwise they should be set to 0.
     *
     *
     *@param h hour.
     *@param m minute.
     */
    public Time2(int h, int m)
    {
        if((MIN_TIME <= h) && (h <= MAX_HOUR))// confriming valid hours range
        {
            _minFromMid = h * MINUTES_IN_HOUR;
        }
        else
        {
            _minFromMid = 0;
        }
        if((MIN_TIME <= m) && (m <= MAX_MINUTE))// confriming valid minutes range
        {
            _minFromMid += m;
        }
    }

    /**
     * Copy constructor for Time2.
     * Constructs a time with the same variable as another time.
     * 
     * @param other The time object from which to construct the new time.
     */
    public Time2(Time2 other)
    {
        _minFromMid = other._minFromMid;
    }

    //Methods
    /**
     * Returns the hour of the time.
     * 
     * @return The hour of the time
     */
    public int getHour()
    {
        return _minFromMid/MINUTES_IN_HOUR;// extract the hour part from the "minFromMid" by gethering every 60 minutes to 1 hour
    }

    /**
     * Returns the minute of the time.
     * 
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minFromMid%MINUTES_IN_HOUR;// after extracting the hour the remain opart is the minutes
    }

    /**
     * Changes the hour of the time.
     * If an illegal number is received hour will remain unchanged.
     * 
     * @param num The new hour
     */
    public void setHour(int num)
    {
        if((MIN_TIME <= num) && (num <= MAX_HOUR))// confriming valid minutes range
        {
            _minFromMid = getMinute()+ num * MINUTES_IN_HOUR;//get the minutes part from getMin method and
            //add the new hour after covertion to minutes
        }
    }

    /**
     * Changes the minute of the time. 
     * If an illegal number is received minute will be unchanged.
     * 
     * @param num The new minute.
     */
    public void setMinute(int num)
    {
        if((MIN_TIME <= num) && (num <= MAX_MINUTE))// confriming valid minutes range
        {
            _minFromMid = getHour() * MINUTES_IN_HOUR + num;//convert the hour part from getHour method to minutes 
            //and add the new minutes to it 
        }
    }

    /**
     * Return the amount of minutes since midnight.
     * 
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return _minFromMid;
    }

    /**
     * Checks if the received time is equal to this time.
     * 
     * @param other The time to be compared with this time.
     * 
     * @return True if the received time is equal to this time.
     */
    public boolean equals(Time2 other)
    {
        return (_minFromMid == other._minFromMid);// if the minutes from mid night are equals so the hours are equals
    }

    /**
     * Checks if this time is before a received time.
     * 
     * @param other The time to check if this time is before.
     * 
     * @return True if this time is before other time.
     */
    public boolean before(Time2 other)
    {
        return (_minFromMid < other._minFromMid);// If less minutes pass from midnnight so the time is before the other. 
    }

    /**
     * Checks if this time is after a received time.
     * 
     * @param other The time to check if this time is after.
     * 
     * @return True if this time is after other time.
     */
    public boolean after(Time2 other)
    {
        return other.before(this);// To check if "this" is after "other" is the same as check if "other" is before "this"
    }

    /**
     * Calculates the difference (in minutess) between two times.
     * 
     * @param other The time to check the difference with. Assumption: this time is after other time.
     * 
     * @return int difference in minutes.
     */
    public int difference(Time2 other)
    {
        return (_minFromMid - other._minFromMid);// subtraction the smaller(erlier time) minFromMid from the bigger(later time)
        //will result the minutes diffrent between the hours
    }

    /**
     * Returns a string representation of this time(hh:mm).
     * 
     * @return String representation of this time(hh:mm).
     */

    public String toString()
    {
        String tempString;
        if (getHour() < TWO_DIGITS)// in case there is only one hours digit adding a 0 to recive hh format
        {
            tempString = "0" + getHour() + ":";
        }
        else 
        {
            tempString = getHour() + ":";
        }
        if (getMinute() < TWO_DIGITS)
        {
            tempString += "0" + getMinute();// in case there is only one minutes digit adding a 0 to recive hh format
        }
        else
        {
            tempString += getMinute();
        }

        return tempString;
    }

    /**
     * Copy current object and add requested minutes to new object.
     * 
     * @param num The minutes need to add.
     * 
     * @return new update Time2 object.
     */
    public Time2 addMinutes(int num)
    {
        int tempHours , tempMinutes = _minFromMid + num;
        if (tempMinutes > MIN_TIME)
        {
            tempMinutes %= MINUTES_IN_DAY;// every 1440 minutes will count as a day and will be removed.
        }
        else if (tempMinutes < MIN_TIME)
        {
            tempMinutes = (tempMinutes%MINUTES_IN_DAY) + MINUTES_IN_DAY ;/* calculating the negative number of minutes to reduce
            from one day adn the reduce it from one day minutes*/
        }
        tempHours = tempMinutes / MINUTES_IN_HOUR;// every 60 minute will be converted to 1 hour and added to "tempHour" variable
        tempMinutes %= MINUTES_IN_HOUR; // calculating the minutes after convertion of the extra minutes to hours
        return new Time2(tempHours, tempMinutes);
    }

}
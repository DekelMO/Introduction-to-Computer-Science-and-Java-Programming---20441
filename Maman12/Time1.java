/**
 * Represents time - hours:minutes. Coordinates cannot be negative.
 * 
 * @author Dekel Moens
 * @version 26.03.2022
 */
public class Time1
{
    // variables
    private int _hour; // hours number between 0-23
    private int _minute;// minutes number between 0-59
    // constants 
    private final static int MIN_TIME = 0 , MAX_HOUR = 23, MAX_MINUTE = 59; // Time cannot go below 0 or above 23hours/59minutes
    private final static int MINUTES_IN_HOUR = 60, HOURS_IN_DAY = 24, TWO_DIGITS = 10; // 10 is the first two digits number
    // constructors
    /**
     * Constructs a Time1 object. 
     * Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise it should be set to 0.
     * 
     * @param h the hour of the time.
     * @param m the minute of the time.
     */
    public Time1(int h, int m)
    {
        if((MIN_TIME <= h) && (h <= MAX_HOUR))// confriming valid hours range
        {
            _hour = h;
        }
        else
        {
            _hour = MIN_TIME;
        }
        if((MIN_TIME <= m) && (m <= MAX_MINUTE))// confriming valid minutes range
        {
            _minute = m;
        }
        else
        {
            _minute = MIN_TIME;
        }
    }

    /**
     * Copy constructor for Time1.
     * Construct a time with the same instance variables as another time.
     * 
     * @param other The time object from which to construct the new time.
     */
    public Time1(Time1 other)
    {
        _hour = other._hour;// time range cheaks are not required as it was conducted in the previous contructor.
        _minute = other._minute;
    }

    //methods

    /**
     * Returns the hour of the time.
     * 
     * @return The hour of the time.
     */
    public int getHour()
    {
        return _hour;
    }

    /**
     * Returns the minute of the time.
     *
     * @return The minute of the time.
     */
    public int getMinute()
    {
        return _minute;
    }

    /**
     * Changes the hour of the time. 
     * If an illegal number is received hour will be unchanged.
     * 
     * @param num The new hour.
     */
    public void setHour(int num)
    {
        if((MIN_TIME <= num) && (num <= MAX_HOUR))// confriming valid minutes range
        {
            _hour = num;
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
            _minute = num;
        }
    }

    /**
     * Return a string representation of this time (hh:mm).
     * 
     * @return String representation of this time (hh:mm).
     */
    public String toString()
    {
        String tempString;

        if(_hour < TWO_DIGITS)// in case there is only one hours digit adding a 0 to recive hh format
        {
            tempString = "0" + _hour + ":" ;
        }
        else
        {
            tempString = _hour + ":" ;
        }
        if(_minute < TWO_DIGITS)// in case there is only one minutes digit adding a 0 to recive hh format
        {
            tempString += "0" + _minute;
        }
        else
        {
            tempString += _minute;
        }
        return tempString;

    }

    /**
     * Return the amount of minutes since midnight.
     * 
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return (_hour * MINUTES_IN_HOUR) + _minute; 
    }

    /**
     * Check if the received time is equal to this time.
     * 
     * @param other The time to be compared with this time.
     * 
     * @return True if the received time is equal to this time.
     */
    public boolean equals(Time1 other)
    {
        return ((_hour == other._hour) && (_minute == other._minute));

    }

    /**
     * Check if this time is before a received time.
     * 
     * @param other The time to check if this point is before.
     * 
     * @return True if this time is before other time.
     */
    public boolean before(Time1 other)
    {
        return ((_hour < other._hour) || ((_hour == other._hour) && (_minute < other._minute)));
        //Minutes is irelevant if "this" hour's is smaller then "other" hour's. 
        //If the hours are equals so the minutes is the relevant factor.
    }

    /**
     * Check if this time is after a received time.
     * 
     * @param other The time to check if this point is after.
     * 
     * @return True if this time is after other time.
     */
    public boolean after(Time1 other)
    {
        return other.before(this);// To check if "this" is after "other" is the same as check if "other" is before "this"
    }

    /**
     * Calculates the difference (in minutes) between two times. 
     * Assumption: this time is after other time.
     * 
     * @param other The time to check the difference to.
     * 
     * @return int difference in minutes.
     */

    public int difference(Time1 other)
    {
        return ((_hour - other._hour) * MINUTES_IN_HOUR + ( _minute - other._minute ));
        // calculation of the hours diffrances in minutes and the add the minutes diffrence
    }

    /**
     * Copy current object and add requested minutes to new object.
     * 
     * @param num The minutes need to add.
     * 
     * @return new update Time1 object.
     */
    public Time1 addMinutes(int num)
    {
        int tempMinute = _minute + num , tempHour = _hour;
        if(tempMinute > MAX_MINUTE)// if there is over 59 minutes the method will convert the the extra minutes to hours
        {
            tempHour += (tempMinute / MINUTES_IN_HOUR);// every 60 minute will be converted to 1 hour and added to "tempHour" variable
            tempMinute %= MINUTES_IN_HOUR;// calculating the minutes after convertion of the extra minutes to hours
            if(tempHour > MAX_HOUR)// if there is over 23 hours the method will count the remain hour in a new day
            {
                tempHour %= HOURS_IN_DAY;//every 24 hours count as a day and removed, the remain hours is insrted to "tempHour" 
            }
        }
        else if(tempMinute < MIN_TIME)// if there is less then 0 minutes the method will reduce the time
        {
            tempHour += (tempMinute / MINUTES_IN_HOUR);// // calculating the hours to be reduce
            tempMinute %= MINUTES_IN_HOUR;// calculating the minutes to be reduce  
            if (tempMinute < MIN_TIME)// if there is still minutes to be reduce(less then 60)
            {
                tempMinute += MINUTES_IN_HOUR;// convertionb of 1 hour into 60 minutes and reduce the remain minutes
                tempHour--;// reduce the 1 hour which we converted to minutes
            }
            if(tempHour < MIN_TIME)// if the hour is a negative number the hours will be reduce from the days
            {
                tempHour %= HOURS_IN_DAY;// calculating the hours to be reduce(less then 24 hours)
                tempHour += HOURS_IN_DAY;// madding 24 hours as move 1 day back
            }
        }
        return new Time1(tempHour, tempMinute);
    }

}
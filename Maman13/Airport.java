/**
 * Represent Daily Flight Board.
 * A Daily Flight Board object is represented by no. of flights, Airport location(city) and the flights from and to this city.
 * 
 * @author Dekel Moens
 * @version 20.04.2022
 */

public class Airport
{
    // Variables
    private Flight[] _flightsSchedule;
    private int _noOfFlights;
    private String _city;

    // Constants
    private static final int MAX_FLIGHTS = 200;
    private static final int MIN_FLIGHTS = 0;

    // Constructors
    /**
     * Constructor for Airport object.
     * No. of Flights will set for the minimum(0).
     * Flight board capacity will be set for the maximum(200).
     * 
     * @param city The city of the Aiport which its fligths described in the flight board.  
     */
    public Airport (String city)
    {
        _city = city;
        _flightsSchedule = new Flight[MAX_FLIGHTS];
        _noOfFlights = MIN_FLIGHTS;
    }

    // Methods
    /**
     * Adding a new flight to the flight board
     * If the flightboard is full or the flight is not related to the airport city, the flight board will remain unchanged
     * (without adding the flight). 
     * No.of flights will be updated if the flight was added.
     * 
     * @param f The flight to add.
     * 
     * @return true if the flight was added and flase if it wasnt. 
     */
    public boolean addFlight(Flight f)
    {
        if((_noOfFlights < _flightsSchedule.length) && (f.getDestination().equals(_city) || f.getOrigin().equals(_city)))
        //Confirming that the fligth board isnt full and that the flight is relevant to the flight board city.
        {
            _flightsSchedule[_noOfFlights] = new Flight(f);
            _noOfFlights++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Removing a flight from the flight board.
     * If the flight to be removed is not in the flight board the flight board will remain uncahnged(without removing any flight)
     * No. of flights will be updated if the flight was removed.
     * 
     * @param f The flight to remove.
     * 
     * @return true if the flight was removed and flase if i wasnt.
     * 
     */
    public boolean removeFlight(Flight f)
    {
        for(int i = 0; i < _noOfFlights ; i++)// The loop will run on all the indexes available in the flightboard. The index should
        // be smaller from the noOfFlights as the highest index is always smaller by 1 from the noOfFlightd(index start from 0).
        {
            if(_flightsSchedule[i].equals(f))// Check if the flight to be remove is in the flight board.
            {
                for(int t = i +1; t < _noOfFlights; t++)//All the flight in the indexes after the flight to be remove index,
                // will move one location down. the flight to be remove in the index "i" will be replaced by the flight in the index 
                //i+1 and so on.
                {
                    _flightsSchedule[t-1] = _flightsSchedule[t];
                }
                _flightsSchedule[_noOfFlights -1] = null;//Even those the no. of flight will be updated for safety the last 
                // flight in the flight board which is now also the "before the last" flight in the flight board will be set as null. 
                _noOfFlights--;
                return true;
            }
        }
        return false;
    }

    /**
     * Find the first flight from a given place.
     * If no flight is from the given palce null will be return.
     * 
     * @param place The origin of the flight.
     * 
     * @return A copy of the flight departure time of the first flight from the give place, null wil return if no flight was found.
     */
    public Time1 firstFlightFromOrigin(String place)
    {
        Time1 firstFlight;
        for(int i =0; i < _noOfFlights; i++)
        {
            if(_flightsSchedule[i].getOrigin().equals(place))//Check if there is a flight in the array that its origin match the place.
            {
                firstFlight = _flightsSchedule[i].getDeparture();//Save the Deprature time of the flight.
                for(int t = i+1; t < _noOfFlights; t++)//Check all the remain flights in the array.
                {
                    if((_flightsSchedule[t].getOrigin().equals(place)) && (_flightsSchedule[t].getDeparture().before(firstFlight))) 
                    // If additional flight with the same origin and with erlier departure time was found, the first flight time
                    //wil be updated.
                    {
                        firstFlight = _flightsSchedule[t].getDeparture();
                    }
                }
                return firstFlight;//New object isnt neccesary as getDeparture method already created one.

            }
        }
        return null;
    }

    /**
     * Check how many full flights in the flightboard.
     * 
     * @return the number of the full flight.
     */
    public int howManyFullFlights()
    {
        int fullFlightsCount = 0;
        for(int i = 0; i < _noOfFlights; i++)
        {
            if(_flightsSchedule[i].getIsFull())
            {
                fullFlightsCount++;
            }
        }
        return fullFlightsCount;
    }

    /**
     * Check how many flight there are between the Flightboard city and a given place.
     * 
     * @param place Destination of flights from city or origin of flights to city.
     * 
     * @return The amount of flights between flight board city to place(both directions).
     */
    public int howManyFlightsBetween (String place) 
    {
        int flightBetweenCount = 0;
        for(int i = 0; i < _noOfFlights; i++)
        {
            if((place.equals(_flightsSchedule[i].getOrigin())) || (place.equals(_flightsSchedule[i].getDestination())))
            // Place and city are two diffrent location, because city is always the destination or the origin(the city of the 
            // flight board) it required only to check if place is the destination of the origin of the flights.
            {
                flightBetweenCount++;
            }    
        }
        return flightBetweenCount;
    }

    /**
     * Check the most popular destination in the fligh board.
     * 
     * @return The most popular destination if the flightboard is empty null will be returned.
     */
    public String mostPopularDestination() 
    {
        String popularDestination = null;// Temp value to be return.
        int popularDestinationReps = 0;// Temp popular destination reps.
        for(int i = 0; i< _noOfFlights; i++)
        {
            int counter = 0;// Counter for each destination.
            for(int t = i; t < _noOfFlights; t++)// The secod loop will start from the "i" index. If there are a repetitions  
            //of the destination in the indexes prior to "i" its mean the destinations was already checked 
            //and if there isnt so it will be 0 .Starting to check from the "i" index will reduce some of the unnaccesary checks.
            {
                if(_flightsSchedule[i].getDestination().equals(_flightsSchedule[t].getDestination()))//Counting the amount of repetitions
                //for each destination.
                {
                    counter++;
                }
            }
            if(counter > popularDestinationReps)//If the amount of repetitions is greter, the temp max the popular 
            //destination will be updated.
            {
                popularDestinationReps = counter;
                popularDestination = _flightsSchedule[i].getDestination();
            }
        }
        return popularDestination;
    }

    /**
     * return the most expensive flight in the flightboard.
     * 
     * @return The most expensive flight,if the flightboard is empty null will be returned.
     */
    public Flight mostExpensiveTicket()
    {
        if(_noOfFlights == MIN_FLIGHTS)// Confirming there is flight in the flightboard.
        {
            return null;
        }
        Flight mostExpensiveFlight = _flightsSchedule[0];//Because the flight board isnt empty there is atleast one flight.
        //we will define the first flight temporary as the most expnesive flight and start to check if there is more expensive flight.
        for(int i = 1; i < _noOfFlights; i++)// As we already assigned the first flight as the temp most expensive flight we will,
        //start to check from the second flight.
        {
            if(mostExpensiveFlight.isCheaper(_flightsSchedule[i]))// If a more expensive flight was found the most expensive
            // flight variable will be updated accordingly.
            {
                mostExpensiveFlight = _flightsSchedule[i];
            }
        }
        return new Flight(mostExpensiveFlight);
    }

    /**
     * Return the longest flight in the flightboard.
     * 
     * @return The longest flight, if the flightboard is empty null will be returned.
     */
    public Flight longestFlight() 
    {
        if(_noOfFlights == MIN_FLIGHTS)// Confirming there is flight in the flightboard.
        {
            return null;
        }
        Flight longestFlight = _flightsSchedule[0];//Because the flight board isnt empty there is atleast one flight,
        //we will define the first flight as the longest flight and start to check if there longer flight.
        for(int i = 1; i < _noOfFlights; i++)// As we already assigned the first flight as the temp longest flight we will,
        //start to check from the second flight.
        {
            if((longestFlight.getFlightDuration()) < (_flightsSchedule[i].getFlightDuration()))// If a longer flight was found, 
            // the longest flight variable will be updated accordingly.
            {
                longestFlight = _flightsSchedule[i];
            }
        }
        return new Flight(longestFlight);
    }

    /**
     * Return a string representation of this flightboard.
     * For example: "The flights for airport Tel-Aviv today are:" and all the flight board flights string description in seprate rows.
     * 
     * @return String representation of this flightboard.
     */
    public String toString() 
    {
        if(_noOfFlights == MIN_FLIGHTS)
        {
            return null;
        }
        String temp = "The flights for airport " + _city + " today are:\n";
        for(int i = 0; i < _noOfFlights; i++)
        {
            temp += _flightsSchedule[i].toString() + "\n";
        }
        return temp;
    }
}
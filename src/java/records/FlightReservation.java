/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import items.*;
import java.util.Date;
import java.util.HashMap;
import reservationPortalSystem.Customer;

/**
 *
 * @author ahmed
 */
public class FlightReservation extends ReservationRecord
{

    //int ticketsNumber;

    /* the map representing the reserved seats of the flight where
    the key is the name of the age group and the value is another
    hash map representing the degree of thr seat */
    HashMap<String, HashMap<String, Integer>> seats;

    public FlightReservation()
    {
        seats = new HashMap<String, HashMap<String, Integer>>();
    }

    public FlightReservation(ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, HashMap<String, HashMap<String, Integer>> seats)
    {
        super(myReservationItem, mypayment, reserve, myDateInformation);
        this.seats = seats;
    }

    public HashMap<String, HashMap<String, Integer>> getSeats()
    {
        return seats;
    }

    public void setSeats(HashMap<String, HashMap<String, Integer>> seats)
    {
        this.seats = seats;
    }

    @Override
    public HashMap getSearchCritria()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("sourceAirport", new Airport());
        fields.put("destinationAirport", new Airport());
        fields.put("StartDate", new Date());
        if (myReservationItem instanceof TwoWayFlight)
        {
            fields.put("EndDate", new Date());
        }
        return fields;
    }

    @Override
    public Double calculartePrice()
    {
        price = 0;
        Flight myFlight = (Flight) myReservationItem;
        //iterate over the age groups of the flight
        for (AgeGroup group : myFlight.getMyAgeGroup())
        {
            //checks if the customer chooses this age group
            if (seats.containsKey(group.getName()))
            {
                HashMap<String, Integer> classes = seats.get(group.getName());
                //checking the classes of the chairs
                if (classes.containsKey("FirstSeats"))
                {
                    price += (classes.get("FirstSeats") * myFlight.getFirstTicketPrice() * group.getPrice());
                }
                if (classes.containsKey("BussinessSeats"))
                {
                    price += (classes.get("BussinessSeats") * myFlight.getBussinessTicketPrice() * group.getPrice());
                }
                if (classes.containsKey("EconomySeats"))
                {
                    price += (classes.get("EconomySeats") * myFlight.getEconomyTicketPrice() * group.getPrice());
                }
            }
        }
        //doubling the price if this flight is two way flight
        if (myFlight instanceof TwoWayFlight)
        {
            price *= 2;
        }

        return price;
    }
}

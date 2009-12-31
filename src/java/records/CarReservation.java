/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import items.*;
import java.util.HashMap;
import reservationPortalSystem.Customer;

/**
 *
 * @author ahmed
 */
public class CarReservation extends ReservationRecord
{

    private Location pickupLocation;
    private Location returnLocation;

    public CarReservation()
    {
        super();
        pickupLocation = new Location();
        returnLocation = new Location();
        myReservationItem = new Car();
    }

    public CarReservation(Car myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, Location pickupLocation, Location returnLocation)
    {
        super(myReservationItem, mypayment, reserve, myDateInformation);
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
    }

    public Location getPickupLocation()
    {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation)
    {
        this.pickupLocation = pickupLocation;
    }

    public Location getReturnLocation()
    {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation)
    {
        this.returnLocation = returnLocation;
    }

    @Override
    public Double calculartePrice()
    {
        price = 0.0;
        Car reservedCar = (Car) myReservationItem;
        DoubleDate interval = (DoubleDate) myDateInformation;
        double costPerDay = reservedCar.getRentPrice();
        int days = (interval.getEndDate().getYear() - interval.getStartDate().getYear() )*365
                 + (interval.getEndDate().getMonth() - interval.getStartDate().getMonth() )*30
                 + (Math.abs(interval.getEndDate().getDate() - interval.getStartDate().getDate()) + 1);

        price = days * costPerDay;
        return price;
    }

    @Override
    public HashMap getSearchCritria()
    {
        HashMap<String,Object> fields=new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("pickupLocation", pickupLocation);
        fields.put("returnLocation", returnLocation);
        fields.put("carType", null);
        fields.put("CarModel", null);
        return fields;
    }
}

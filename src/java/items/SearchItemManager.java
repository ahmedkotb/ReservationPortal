/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Collection;
import items.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Queue;
import javax.jdo.Query;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author Ahmed Mohsen
 */
public class SearchItemManager
{

    HashMap<String, Object> searchCriteria;   //the properties of the wanted item
    TYPE type;                            //the type of the wanted item

    public SearchItemManager(HashMap<String, Object> searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    public SearchItemManager(HashMap<String, Object> searchCriteria, TYPE type)
    {
        this.searchCriteria = searchCriteria;
        this.type = type;
    }

    public HashMap<String, Object> getSearchCriteria()
    {
        return searchCriteria;
    }

    public void setSearchCriteria(HashMap<String, Object> searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    public TYPE getType()
    {
        return type;
    }

    public void setType(TYPE type)
    {
        this.type = type;
    }

    public Collection searchItems()
    {
        if (type == TYPE.CAR)
        {
            return searchCars();
        } else if (type == TYPE.FLIGHT)
        {
            return searchFlights();
        } else if (type == TYPE.HOTEL)
        {
            return searchHotels();
        } else
        {
            return null;
        }

    }

    private Collection<Flight> searchFlights()
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Flight.class);
        String filter = "";
        String parameters = "";

        //two way flight
        if (searchCriteria.containsKey("endDate") && searchCriteria.get("endDate") != null )
        {
            filter += "this.myDateInformation.isInBetween(endDate) && ";
            parameters += "java.util.Date endDate , ";
        }else
            searchCriteria.remove("endDate");

        filter +="this.myDateInformation.isInBetween(startDate) && this.sourceAirport.equals(sourceAirport) && this.destinationAirport.equals(destinationAirport)";
        parameters +="java.util.Date startDate , Airport sourceAirport , Airport destinationAirport";
        query.declareParameters(parameters);
        query.setFilter(filter);

        Collection<Flight> result = (Collection<Flight>) query.executeWithMap(searchCriteria);
        return result;
    }

    private Collection<Car> searchCars()
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Car.class);
        String filter = "";
        String parameters = "";

        if (searchCriteria.containsKey("carType") && searchCriteria.get("carType") != null && !((String)searchCriteria.get("carType")).equals(""))
        {
            filter += "this.carType == carType && ";
            parameters += "String carType , ";
        }else
            searchCriteria.remove("carType");

        if (searchCriteria.containsKey("carModel") && searchCriteria.get("carModel") != null && !((String)searchCriteria.get("carModel")).equals(""))
        {
            filter += "this.carModel == carModel && ";
            parameters += "String carModel , ";
        }
        else
            searchCriteria.remove("carModel");
    
               
        filter += "this.myAgency.supportedLocations.contains(pickupLocation) == true && this.myAgency.supportedLocations.contains(returnLocation) == true && this.availableNumber > 0";

        parameters += "Location pickupLocation , Location returnLocation";
        query.declareParameters(parameters);
        query.setFilter(filter);       
        Collection<Car> result = (Collection<Car>) query.executeWithMap(searchCriteria);
        return result;
    }

    private Collection<Hotel> searchHotels()
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Hotel.class);
        String filter = "";
        String parameters = "";

        if (searchCriteria.containsKey("stars") && (Integer) searchCriteria.get("stars") != 0)
        {
            filter += "this.stars == stars && ";
            parameters += "int stars , ";
        }
        else
            searchCriteria.remove("stars");
        filter += "this.location.equals(location) && ";
        filter += "this.myRooms.contains(room) && room.guestNumber == guestNumber";
        parameters += "int guestNumber , Location location";
        query.declareParameters(parameters);
        query.declareVariables("Room room");
        query.setFilter(filter);
        Collection<Hotel> result = (Collection<Hotel>) query.executeWithMap(searchCriteria);
        return result;

    }
    
}

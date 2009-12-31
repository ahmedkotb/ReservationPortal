/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Collection;
import items.*;
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
    searchType type;                            //the type of the wanted item

    public SearchItemManager(HashMap<String, Object> searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    public SearchItemManager(HashMap<String, Object> searchCriteria, searchType type)
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

    public searchType getType()
    {
        return type;
    }

    public void setType(searchType type)
    {
        this.type = type;
    }

    public Collection searchItems()
    {
        if (type == searchType.CAR)
        {
            return searchCars();
        } else if (type == searchType.FLIGHT)
        {
            return searchFlights();
        } else if (type == searchType.HOTEL)
        {
            return searchHotels();
        } else
        {
            return null;
        }

    }

    private Collection<Flight> searchFlights()
    {
        return null;
    }

    private Collection<Car> searchCars()
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Car.class);
        String filter = "";
        String parameters = "";

        if (searchCriteria.containsKey("carType") && searchCriteria.get("carType") != null)
        {
            filter += "this.carType == carType && ";
            parameters += "String carType , ";
        }

        if (searchCriteria.containsKey("carModel") && searchCriteria.get("carModel") != null)
        {
            filter += "this.carModel == carModel && ";
            parameters += "String carModel , ";
        }
        filter += "this.myAgency.supportedLocations.contains( pickupLocation ) && this.myAgency.supportedLocations.contains( returnLocation )";
        //filter+="this.myAgency.hasSupport( pickupLocation ) && this.myAgency.hasSupport( returnLocation )";

        parameters += "Location pickupLocation , Location returnLocation";
        //query.declareImports("import items.*");
        // query.declareVariables("Location pickupLocation2");
        //query.declareVariables("Location returnLocation2");
        query.declareParameters(parameters);
        query.setFilter(filter);
        System.out.println(filter);
        System.out.println(parameters);
        //System.out.println(searchCriteria.containsKey("pickupLocation"));
        Collection<Car> result = (Collection<Car>) query.executeWithMap(searchCriteria);
        return result;
    }

    private Collection<Hotel> searchHotels()
    {
        return null;
    }
}
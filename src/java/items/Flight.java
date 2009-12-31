/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import reservationPortalSystem.DateInformation;
import java.util.ArrayList;
import java.util.HashMap;
import reservationPortalSystem.Admin;

/**
 *
 * @author ahmed
 */
public abstract class Flight extends ReservationItem
{

    protected Airport sourceAirport;
    protected Airport destinationAirport;
    protected Double economyTicketPrice;
    protected Double firstTicketPrice;
    protected Double bussinessTicketPrice;
    protected int availableFirstSeats;
    protected int availableBussinessSeats;
    protected int availableEconomySeats;
    protected ArrayList<AgeGroup> myAgeGroup;
    protected DateInformation myDateInformation;

    public Flight()
    {
        super();
        sourceAirport = new Airport();
        destinationAirport = new Airport();
        economyTicketPrice = 0.0;
        firstTicketPrice = 0.0;
        bussinessTicketPrice = 0.0;
        availableBussinessSeats = 0;
        availableEconomySeats = 0;
        availableFirstSeats = 0;
        myAgeGroup = new ArrayList<AgeGroup>();
    }

    public Flight(int quantity, Airport sourceAirport, Airport destinationAirport, Double economyTicketPrice, Double firstTicketPrice, Double bussinessTicketPrice, int availableFirstSeats, int availableBussinessSeats, int availableEconomySeats, ArrayList<AgeGroup> myAgeGroup, Admin provider)
    {
        super(quantity, provider);
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.economyTicketPrice = economyTicketPrice;
        this.firstTicketPrice = firstTicketPrice;
        this.bussinessTicketPrice = bussinessTicketPrice;
        this.availableFirstSeats = availableFirstSeats;
        this.availableBussinessSeats = availableBussinessSeats;
        this.availableEconomySeats = availableEconomySeats;
        this.myAgeGroup = myAgeGroup;
    }

    public int getAvailableBussinessSeats()
    {
        return availableBussinessSeats;
    }

    public void setAvailableBussinessSeats(int availableBussinessSeats)
    {
        this.availableBussinessSeats = availableBussinessSeats;
    }

    public int getAvailableEconomySeats()
    {
        return availableEconomySeats;
    }

    public void setAvailableEconomySeats(int availableEconomySeats)
    {
        this.availableEconomySeats = availableEconomySeats;
    }

    public int getAvailableFirstSeats()
    {
        return availableFirstSeats;
    }

    public void setAvailableFirstSeats(int availableFirstSeats)
    {
        this.availableFirstSeats = availableFirstSeats;
    }

    public Double getBussinessTicketPrice()
    {
        return bussinessTicketPrice;
    }

    public void setBussinessTicketPrice(Double bussinessTicketPrice)
    {
        this.bussinessTicketPrice = bussinessTicketPrice;
    }

    public Airport getDestinationAirport()
    {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport)
    {
        this.destinationAirport = destinationAirport;
    }

    public Double getEconomyTicketPrice()
    {
        return economyTicketPrice;
    }

    public void setEconomyTicketPrice(Double economyTicketPrice)
    {
        this.economyTicketPrice = economyTicketPrice;
    }

    public Double getFirstTicketPrice()
    {
        return firstTicketPrice;
    }

    public void setFirstTicketPrice(Double firstTicketPrice)
    {
        this.firstTicketPrice = firstTicketPrice;
    }

    public ArrayList<AgeGroup> getMyAgeGroup()
    {
        return myAgeGroup;
    }

    public void setMyAgeGroup(ArrayList<AgeGroup> myAgeGroup)
    {
        this.myAgeGroup = myAgeGroup;
    }

    public Airport getSourceAirport()
    {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport)
    {
        this.sourceAirport = sourceAirport;
    }

    public void addAgeGroup(AgeGroup group)
    {

        myAgeGroup.add(group);
    }

    public boolean isInUse()
    {
        //assume that at the begining quantity =available number of all seats
        if (quantity == availableBussinessSeats + availableEconomySeats + availableFirstSeats)
        {
            return false;
        }
        return false;

    }

    @Override
    public void reserve(HashMap<String, Integer> info)
    {
        //setting availableFirstSeats
        availableFirstSeats = availableFirstSeats - info.get("FirstSeats");
        //setting availableBussinessSeats
        availableBussinessSeats = availableBussinessSeats - info.get("BussinessSeats");
        //setting availableEconomySeats
        availableEconomySeats = availableEconomySeats - info.get("EconomySeats");


    }

    @Override
    public void returnBack(HashMap<String, Integer> info)
    {
        //setting availableFirstSeats
        availableFirstSeats = availableFirstSeats + info.get("FirstSeats");
        //setting availableBussinessSeats
        availableBussinessSeats = availableBussinessSeats + info.get("BussinessSeats");
        //setting availableEconomySeats
        availableEconomySeats = availableEconomySeats + info.get("EconomySeats");

    }

    @Override
    public HashMap getObjectData()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.putAll(super.getObjectData());   //putting the common attributes from the abstract class in the map
        fields.put("sourceAirport", sourceAirport);
        fields.put("destinationAirport", destinationAirport);
        fields.put("economyTicketPrice", economyTicketPrice);
        fields.put("firstTicketPrice", firstTicketPrice);
        fields.put("bussinessTicketPrice", bussinessTicketPrice);
        fields.put("availableEconomySeats", availableEconomySeats);
        fields.put("availableFirstSeats", availableFirstSeats);
        fields.put("availableBussinessSeats", availableBussinessSeats);
        fields.put("myAgeGroup", myAgeGroup);
        fields.put("DateInformation", myDateInformation);

        return fields;
    }

    public void setObjectData(HashMap fields)
    {
        //setting the attributes from parent class
        quantity = (Integer) fields.get("quantity");
        rating = (Double) fields.get("rating");
        reviews = (ArrayList<Review>) fields.get("reviews");
        //setting the concrete class attributess
        sourceAirport = (Airport) fields.get("sourceAirport");
        destinationAirport = (Airport) fields.get("destinationAirport");
        economyTicketPrice = (Double) fields.get("economyTicketPrice");
        firstTicketPrice = (Double) fields.get("firstTicketPrice");
        bussinessTicketPrice = (Double) fields.get("bussinessTicketPrice");
        availableEconomySeats = (Integer) fields.get("availableEconomySeats");
        availableFirstSeats = (Integer) fields.get("availableFirstSeats");
        availableBussinessSeats = (Integer) fields.get("availableBussinessSeats");
        myAgeGroup = (ArrayList<AgeGroup>) fields.get("myAgeGroup");
        myDateInformation = (DateInformation) fields.get("DateInformation");

    }
}

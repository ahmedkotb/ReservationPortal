/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;
import java.util.HashMap;

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

    public Flight(int quantity, Airport sourceAirport, Airport destinationAirport, Double economyTicketPrice, Double firstTicketPrice, Double bussinessTicketPrice, int availableFirstSeats, int availableBussinessSeats, int availableEconomySeats, ArrayList<AgeGroup> myAgeGroup)
    {
        super(quantity);
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
    public void reserve(HashMap info)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void returnBack(HashMap info)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

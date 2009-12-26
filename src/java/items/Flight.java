/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;

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


}

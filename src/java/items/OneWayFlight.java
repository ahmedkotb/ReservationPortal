/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import java.util.ArrayList;
import reservationPortalSystem.Admin;

/**
 *
 * @author ahmed
 */
public class OneWayFlight extends Flight {

    public OneWayFlight()
    {
        super();
        super.myDateInformation=new SingleDate();

    }

    public OneWayFlight(int quantity, Airport sourceAirport, Airport destinationAirport, Double economyTicketPrice, Double firstTicketPrice, Double bussinessTicketPrice, int availableFirstSeats, int availableBussinessSeats, int availableEconomySeats, ArrayList<AgeGroup> myAgeGroup,SingleDate mySingleDate, Admin provider)
    {
        super(quantity, sourceAirport, destinationAirport, economyTicketPrice, firstTicketPrice, bussinessTicketPrice, availableFirstSeats, availableBussinessSeats, availableEconomySeats, myAgeGroup,provider);
        myDateInformation=mySingleDate;
    }




   

}

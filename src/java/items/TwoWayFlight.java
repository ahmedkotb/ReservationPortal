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
public class TwoWayFlight extends Flight {


 public TwoWayFlight()
    {
        super();
        super.myDateInformation=new SingleDate();

    }

  public TwoWayFlight(int quantity, Airport sourceAirport, Airport destinationAirport, Double economyTicketPrice, Double firstTicketPrice, Double bussinessTicketPrice, int availableFirstSeats, int availableBussinessSeats, int availableEconomySeats, ArrayList<AgeGroup> myAgeGroup,DoubleDate myDoubleDate)
    {
        super(quantity, sourceAirport, destinationAirport, economyTicketPrice, firstTicketPrice, bussinessTicketPrice, availableFirstSeats, availableBussinessSeats, availableEconomySeats, myAgeGroup);
        myDateInformation=myDoubleDate;
    }




    @Override
    public void reserve()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void returnBack()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

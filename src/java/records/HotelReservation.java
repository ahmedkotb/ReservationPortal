/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import reservationPortalSystem.Payment;
import reservationPortalSystem.DateInformation;
import items.*;
import java.util.HashMap;
import reservationPortalSystem.Customer;

/**
 *
 * @author ahmed
 */
public class HotelReservation extends ReservationRecord
{

    int guestNumber;
    int roomNumber;

    public HotelReservation()
    {
        super();
        guestNumber = 0;
        roomNumber = 0;
    }

    public HotelReservation(int reservationID, ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, int guestNumber, int roomNumber)
    {
        super(myReservationItem, mypayment, reserve, myDateInformation);
        this.guestNumber = guestNumber;
        this.roomNumber = roomNumber;
    }

    public int getGuestNumber()
    {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber)
    {
        this.guestNumber = guestNumber;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    @Override
    public HashMap getSearchCritria()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("location", new Location());
        fields.put("stars", 0);
        fields.put("guestNumber", guestNumber);
        return fields;
    }

    @Override
    public Double calculartePrice()
    {
        price = 0;
        Hotel myHotel = (Hotel) myReservationItem;
        DoubleDate interval = (DoubleDate) myDateInformation;
        double pricePerDay = 0.0;
        for (Room myRoom : myHotel.getMyRooms())
        {
            if (myRoom.getRoomNumber() == roomNumber)
            {
                pricePerDay = myRoom.getPricePerDay();
                break;
            }
        }

        //the number of days when the customer reserves the room in the hotel
        int days = (interval.getEndDate().getYear() - interval.getStartDate().getYear()) * 365
                 + (interval.getEndDate().getMonth() - interval.getStartDate().getMonth()) * 30
                 + (Math.abs(interval.getEndDate().getDate() - interval.getStartDate().getDate()) + 1);
        
        price = days * pricePerDay;
        return price;
    }

    @Override
    public HashMap getInfo()
    {
        HashMap<String ,Integer> info=new HashMap<String, Integer>();
        info.put("roomNumber", roomNumber);
        return info;
    }
}

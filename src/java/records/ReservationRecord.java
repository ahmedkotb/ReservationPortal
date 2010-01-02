/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import reservationPortalSystem.Payment;
import reservationPortalSystem.DateInformation;
import items.*;
import java.util.Date;
import java.util.HashMap;
import reservationPortalSystem.Customer;

/**
 *
 * @author Ahmed Mohsen and Ahmed Kotb :D
 */
public abstract class ReservationRecord
{

    protected int reservationID;    //may be removed from the constructor and become auto incremented
    protected Date purchaseDate;
    protected double price;
    protected ReservationItem myReservationItem;
    protected Payment mypayment;
    protected Customer reserver;
    protected DateInformation myDateInformation;
    protected String status;

    public ReservationRecord()
    {
        purchaseDate = new Date();
        reservationID = 0;
        price = 0;
        reserver = new Customer();
        status = (ReservationStatus.ONHOLD).toString();
        myDateInformation = new SingleDate(new Date());
    }

    public ReservationRecord(ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, ReservationStatus myReservationStatus)
    {
        this.reservationID = 0;
        this.price = calculartePrice();
        this.myReservationItem = myReservationItem;
        this.mypayment = mypayment;
        this.reserver = reserve;
        this.myDateInformation = myDateInformation;
        this.status = myReservationStatus.toString();
        purchaseDate = new Date();
        pickItem();     //capture the item
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public ReservationStatus getStatus()
    {
        return ReservationStatus.valueOf(status);
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public ReservationRecord(ReservationItem myReservationItem, Payment mypayment, Customer reserver, DateInformation myDateInformation)
    {
        this(myReservationItem,
                mypayment,
                reserver,
                myDateInformation, ReservationStatus.ONHOLD);
    }

    public abstract HashMap getSearchCritria();

    public DateInformation getMyDateInformation()
    {
        return myDateInformation;
    }

    public void setMyDateInformation(DateInformation myDateInformation)
    {
        this.myDateInformation = myDateInformation;
    }

    public ReservationItem getMyReservationItem()
    {
        return myReservationItem;
    }

    public void setMyReservationItem(ReservationItem myReservationItem)
    {
        this.myReservationItem = myReservationItem;
    }

    public void setStatus(ReservationStatus myReservationStatus)
    {
        this.status = myReservationStatus.toString();
    }

    public Payment getMypayment()
    {
        return mypayment;
    }

    public void setMypayment(Payment mypayment)
    {
        this.mypayment = mypayment;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getReservationID()
    {
        return reservationID;
    }

    public void setReservationID(int reservationID)
    {
        this.reservationID = reservationID;
    }

    public Customer getReserve()
    {
        return reserver;
    }

    public void setReserve(Customer reserve)
    {
        this.reserver = reserve;
    }

    public Customer getReserver()
    {
        return reserver;
    }

    public void setReserver(Customer reserver)
    {
        this.reserver = reserver;
    }

    /**
     * return a hashmap representing the info which will be used to reserve or set free a reservation
     * @return
     */
    public abstract HashMap getInfo();

    /**
     * calculate the price of a record
     * @return the price of the reservation
     */
    public abstract Double calculartePrice();

    /**
     * a factory method to create a record with specified type
     * @param type the type of the record car , hotel , flight ,etc..
     * @return the wanted concrete class but warraped under the abstract class 
     */
    public static ReservationRecord createRecord(TYPE type)
    {
        if (type == TYPE.CAR)
        {
            return new CarReservation();
        }
        if (type == TYPE.FLIGHT || type == TYPE.ONEWAYFLIGHT || type == TYPE.TwoWayFlight)
        {
            return new FlightReservation();
        }
        if (type == TYPE.HOTEL)
        {
            return new HotelReservation();
        }
        // not supported yet...
        return null;
    }

    /**
     * decreasing the reserved item resources like available number
     */
    public void pickItem()
    {
        myReservationItem.reserve(getInfo());
    }

    /**
     * increasing the reserved item resources like available number
     */
    public void returnItem()
    {
        myReservationItem.returnBack(getInfo());
    }
}

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
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author Ahmed Mohsen and Ahmed Kotb :D
 */
public abstract class ReservationRecord
{


    protected String reservationID;    //may be removed from the constructor and become auto incremented
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
        reservationID = generateUniqueId();
        price = 0;
        reserver = new Customer();
        status = (ReservationStatus.ONHOLD).toString();
        myDateInformation = new SingleDate(new Date());
    }

    public ReservationRecord(ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, ReservationStatus myReservationStatus)
    {
        
        this.price = calculartePrice();
        this.myReservationItem = myReservationItem;
        this.mypayment = mypayment;
        this.reserver = reserve;
        this.myDateInformation = myDateInformation;
        this.status = myReservationStatus.toString();
        purchaseDate = new Date();
        this.reservationID = generateUniqueId();
        pickItem();     //capture the item
    }
  
    public ReservationRecord(ReservationItem myReservationItem, Payment mypayment, Customer reserver, DateInformation myDateInformation)
    {
        this(myReservationItem,
                mypayment,
                reserver,
                myDateInformation, ReservationStatus.ONHOLD);
    }

    public abstract HashMap getSearchCritria();



    public Date getPurchaseDate(){
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    public ReservationStatus getStatus(){
        return ReservationStatus.valueOf(status);
    }

    public String getReservationID() {
        return reservationID;
    }


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
        setStatus(ReservationStatus.PAYED);     //setting the status of the record as payed
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Customer getReserve()
    {
        return reserver;
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
     * generates a new unique id
     * @return string that represents a unique item id
     */
    private String generateUniqueId(){
        return (Long.toHexString(new Date().getTime()));
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
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().begin();
        myReservationItem.reserve(getInfo());
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().commit();
    }

    /**
     * increasing the reserved item resources like available number
     */
    public void returnItem()
    {
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().begin();
        myReservationItem.returnBack(getInfo());
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().commit();
    }
}

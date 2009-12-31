/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import items.DateInformation;
import items.ReservationItem;
import java.util.Date;
import java.util.HashMap;
import reservationPortalSystem.Customer;

/**
 *
 * @author ahmed
 */
public abstract class ReservationRecord
{

    protected int reservationID;    //may be removed from the constructor and become auto incremented
    protected double price;
    protected ReservationItem myReservationItem;
    protected Payment mypayment;
    protected Customer reserver;
    protected DateInformation myDateInformation;
    protected String status;

    public ReservationRecord()
    {
        reservationID = 0;
        price = 0;
        reserver = new Customer();
        status = (ReservationStatus.ONHOLD).toString();
    }

    public ReservationRecord(int reservationID, ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation, ReservationStatus myReservationStatus)
    {
        this.reservationID = reservationID;
        this.price = calculartePrice();
        this.myReservationItem = myReservationItem;
        this.mypayment = mypayment;
        this.reserver = reserve;
        this.myDateInformation = myDateInformation;
        this.status = myReservationStatus.toString();
    }

    public ReservationRecord(int reservationID, ReservationItem myReservationItem, Payment mypayment, Customer reserve, DateInformation myDateInformation)
    {
        this.reservationID = reservationID;
        this.myReservationItem = myReservationItem;
        this.mypayment = mypayment;
        this.reserver = reserve;
        this.myDateInformation = myDateInformation;
        this.price = calculartePrice();
        this.status = (ReservationStatus.ONHOLD).toString();
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

    public ReservationStatus getStatus()
    {
        return ReservationStatus.valueOf(status);
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






    public abstract Double calculartePrice();

    public static ReservationRecord createRecord(String type)
    {
        return null;
    }
}

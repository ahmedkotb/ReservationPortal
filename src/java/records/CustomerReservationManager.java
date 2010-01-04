/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import java.util.Collection;
import javax.jdo.Query;
import reservationPortalSystem.Customer;
import reservationPortalSystem.Payment;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author Ahmed Mohsen
 */
public class CustomerReservationManager {

    private Customer customer;
    private int numberOfOnholdReservation;
    private int numberOfOnSpotReservation;

    public CustomerReservationManager() {
        customer = new Customer();
        numberOfOnSpotReservation = 0;
        numberOfOnholdReservation = 0;
    }

    public CustomerReservationManager(Customer customer) {
        this.customer = customer;
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("reservationPortalSystem.Customer customer");

        //setting onHold number
        query.setFilter("this.status== \"ONHOLD\" && this.reserver.getUserName() == customer.getUserName()");
        Collection result = (Collection) query.execute(customer);
        numberOfOnholdReservation = result.size();

        //setting onSpot number
        query.setFilter("this.status== \"PAYED\" && this.reserver.getUserName() == customer.getUserName()");    //will be modified later
        result = (Collection) query.execute(customer);
        numberOfOnSpotReservation = result.size();
    }


    /**
     * get the on hold reservation records
     * @return collection of on hold reservations
     */
    public Collection<ReservationRecord> getOnHoldReservations(){
        ReservationMonitor.getInstance().refresh();
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("reservationPortalSystem.Customer customer");
        query.setFilter("this.status== \"ONHOLD\" && this.reserver.getUserName() == customer.getUserName()");
        Collection result = (Collection) query.execute(customer);
        return result;
    }
    public int getNumberOfSpotReservation() {
        return numberOfOnSpotReservation;
    }

    public void setNumberOfSpotReservation(int numberOfSpotReservation) {
        this.numberOfOnSpotReservation = numberOfSpotReservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumberOfOnholdReservation() {
        return numberOfOnholdReservation;
    }

    public void setNumberOfOnholdReservation(int numberOfOnholdReservation) {
        this.numberOfOnholdReservation = numberOfOnholdReservation;
    }

    public void reserve(ReservationRecord record) {
        record.pickItem();
        ReservationPortalSystem.getInstance().save(record);
        if (record.getStatus() == ReservationStatus.PAYED) {
            numberOfOnSpotReservation++;
        } else {
            numberOfOnholdReservation++;    //onHold
        }
        ReservationMonitor.getInstance().refresh();
    }

    public void cancel(ReservationRecord record) {
        record.setStatus(ReservationStatus.CANCELED);
        record.returnItem();
        if (record.getStatus() == ReservationStatus.PAYED) {
            numberOfOnSpotReservation--;
        } else {
            numberOfOnholdReservation--;
        }
        ReservationMonitor.getInstance().refresh();

    }

    public void pay(ReservationRecord record, Payment payment) {
        numberOfOnSpotReservation++;
        numberOfOnholdReservation--;
        record.setMypayment(payment);
        ReservationMonitor.getInstance().refresh();

    }
}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import java.util.Collection;
import java.util.Date;
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

    final private int MAX_ONHOLD_RESERVATIONS = 3;
    final private int MAX_WEEKLY_RESERVATIONS = 3;

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
    /**
     * get the confirmed reservation records
     * @return collection of confirmed reservations
     */
    public Collection<ReservationRecord> getConfirmedReservations(Date startDate , Date endDate){
        ReservationMonitor.getInstance().refresh();
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("reservationPortalSystem.Customer customer , java.util.Date startDate , java.util.Date endDate");
        query.setFilter("( ( (this.status == \"PAYED\") || (this.status == \"DONE\" ) )  && ( this.reserver.getUserName() == customer.getUserName() ) && ( this.purchaseDate.after(startDate)  &&  this.purchaseDate.before(endDate) ) )");
        query.setOrdering("this.purchaseDate descending");
        Collection result = (Collection) query.execute(customer,startDate,endDate);
        return result;
    }
    /**
     * gets a certain record from the data base
     * @param id the record id
     * @return reservation record
     */
    public ReservationRecord getRecord(String id){
        ReservationMonitor.getInstance().refresh();
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("String id");
        query.setFilter("this.reservationID == id");
        Collection result = (Collection) query.execute(id);
        return (ReservationRecord)result.toArray()[0];
    }

    
    /**
     * make sure that the user didnt exceed his limit on weekly confirmed reservation
     * @return true if the user didnt excced the limit , false otherwise
     */
    public boolean canMakeConfirmedReservation(){
        ReservationMonitor.getInstance().refresh();
        //a date that is a week behind
        Date date = new Date((new Date()).getTime() - 7 * 24 * 60 * 60 * 1000);
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("reservationPortalSystem.Customer customer , java.util.Date date");
        query.setFilter("( ( (this.status == \"PAYED\") || (this.status == \"DONE\" ) )  && ( this.reserver.getUserName() == customer.getUserName() ) && ( this.purchaseDate.after(date) ) )");
        query.setOrdering("this.purchaseDate descending");
        Collection result = (Collection) query.execute(customer,date);
        if (result.size() < MAX_WEEKLY_RESERVATIONS)
            return true;
        return false;
    }

    public int getMAX_WEEKLY_RESERVATIONS() {
        return MAX_WEEKLY_RESERVATIONS;
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

    public void reserve(ReservationRecord record){
        record.pickItem();
        ReservationPortalSystem.getInstance().save(record);
        if (record.getStatus() == ReservationStatus.PAYED) {
            numberOfOnSpotReservation++;
        } else {
            numberOfOnholdReservation++;    //onHold
        }
        ReservationMonitor.getInstance().addRecord(record);
        ReservationMonitor.getInstance().refresh();
    }

    public int getMAX_ONHOLD_RESERVATIONS() {
        return MAX_ONHOLD_RESERVATIONS;
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
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().begin();
        record.setMypayment(payment);
        record.setPrice(record.calculartePrice());
        ReservationPortalSystem.getInstance().getConnection().currentTransaction().commit();
        ReservationMonitor.getInstance().refresh();

    }
}



package reservationPortalSystem;

import items.CarAgency;
import items.ReservationItem;
import items.Review;
import java.util.Collection;
import java.util.HashMap;
import javax.jdo.Query;

/**
 * Manages All item operations in the repository
 * @author Ahmed Kotb
 */
public class ReservationItemManager implements IAdminReservationItemManager , ICustomerReservationItemManager {

    //still needs the search manager class to be implemented

    public ReservationItemManager() {
        
    }


    /**
     * returns a list of all car agencies in the system
     * @return car agency collection
     */
    public Collection<CarAgency> getAllCarAgencies(){
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(CarAgency.class);
        Collection result = (Collection) query.execute();
        return result;
    }


    public void addCarAgency(CarAgency agency){
        ReservationPortalSystem.getInstance().save(agency);
    }

    public void addItem(ReservationItem item) {
        ReservationPortalSystem.getInstance().save(item);
    }

    public Collection<ReservationItem> search(HashMap info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void deleteItem(ReservationItem item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addReview(ReservationItem item, Review review) {
        item.AddReview(review);
    }

    /**
     * returns a car Agency object given a specific name
     * @param name the name of the car agency
     * @return the car agency object
     */
    public CarAgency getCarAgency(String name) {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(CarAgency.class , "this.name == name");
        query.declareParameters("String name");
        Collection result = (Collection) query.execute(name);
        return (CarAgency)result.toArray()[0];
    }

}

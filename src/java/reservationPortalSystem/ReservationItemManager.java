package reservationPortalSystem;

import items.CarAgency;
import items.ReservationItem;
import items.Review;
import items.SearchItemManager;
import items.TYPE;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.jdo.Query;

/**
 * Manages All item operations in the repository
 * @author Ahmed Kotb
 */
public class ReservationItemManager implements IAdminReservationItemManager , ICustomerReservationItemManager {

    //still needs the search manager class to be implemented
    SearchItemManager searchManager;
    public ReservationItemManager() {
        searchManager = new SearchItemManager(null);
    }

    public ReservationItem getItem(String id) {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationItem.class,"this.id == id");
        query.declareParameters("String id");
        Collection result = (Collection) query.execute(id);
        if (result.size() == 0) return null;        
        return (ReservationItem)result.toArray()[0];
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
        searchManager.setSearchCriteria(info);
        searchManager.setType(TYPE.CAR);
        return searchManager.searchItems();
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


     /**
     * return the top 10 most reserved reservation item according to reserved counter
     * @param type determine the rule bywhich the top 10 are chosen
     * @return a collection of 10 items sorted according to thier reserved counter
     */
    public Collection<ReservationItem> getTop10(Top_10 type)
    {
        //get all items
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationItem.class);
        if(type==Top_10.RESERVED_NUMBER)
            query.setOrdering("this.reservedCount descending");
        if(type==Top_10.RATING)
            query.setOrdering("this.rating descending");

        Collection<ReservationItem> result = (Collection<ReservationItem>) query.execute();
        if (result.size() <= 10)
        {
            return result;
        }
        //adding the result to array list
        ArrayList<ReservationItem> top_10 = new ArrayList<ReservationItem>();
        int counter=0;  //counter to determine when to exit from loop
        for (ReservationItem item : result)
        {
            counter++;
            top_10.add(item);   //add top 10 item
            if(counter==10)
                break;
        }
        return top_10;


    }



}

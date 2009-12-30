package reservationPortalSystem;

import items.ReservationItem;
import items.Review;
import java.util.Collection;
import java.util.HashMap;

/**
 *  A Read Only interface for ReservationItemManager class used by Customer
 * @author Ahmed Kotb
 */
public interface ICustomerReservationItemManager {

    /**
     * search for item that satisfies the given criteria in the hashmap
     * @param info the search criteria
     */
    public Collection<ReservationItem> search(HashMap info);


    /**
     * adds a new review to the teh
     * @param review
     */
    public void addReview(ReservationItem item , Review review);

}

package reservationPortalSystem;

import items.ReservationItem;
import items.Review;
import java.util.Collection;
import java.util.HashMap;

/**
 * Manages All item operations in the repository
 * @author Ahmed Kotb
 */
public class ReservationItemManager implements IAdminReservationItemManager , ICustomerReservationItemManager {

    //still needs the search manager class to be implemented

    public ReservationItemManager() {
        
    }

    public void addItem(ReservationItem item) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}

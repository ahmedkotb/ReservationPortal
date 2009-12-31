package reservationPortalSystem;

import items.CarAgency;
import items.ReservationItem;
import java.util.Collection;
import java.util.HashMap;

/**
 * A Read Only interface for ReservationItemManager class used by Admin
 * @author Ahmed Kotb
 */
public interface IAdminReservationItemManager {

    /**
     * adds a new item to the item repository
     * @param item the new item to be added
     */
    public void addItem(ReservationItem item);

    /**
     * adds a new car agency to the system
     * @param agency the car agencyto be added
     */
    public void addCarAgency(CarAgency agency);


    /**
     * returns a car agency object given its name
     * @param name the name of the car agency
     * @return the car agency
     */
    public CarAgency getCarAgency(String name);
    /**
     * returns a list of all car agencies in the system
     * @return car agency collection
     */
    public Collection<CarAgency> getAllCarAgencies();

    /**
     * search for item that satisfies the given criteria in the hashmap
     * @param info the search criteria
     */
    public Collection<ReservationItem> search(HashMap info);


    /**
     * deletes the given item
     * @param item the item to be deleted
     */
    public void deleteItem(ReservationItem item);
}

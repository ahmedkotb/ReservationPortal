package records;
import java.util.Collection;
import reservationPortalSystem.Admin;
/**
 *
 * @author Ahmed Kotb
 */
public class AdminReservationManager {

    private Admin admin;



    public AdminReservationManager(Admin admin){
        this.admin = admin;
    }

    
    /**
     * clear a record ,, make the item associated with the record available for being reserved again
     * @param record the record to be cleared
     */
    public void clearRecord (ReservationRecord record){

    }


    /**
     * return a list of overDue records that should have been cleared
     * @return collection of records
     */
    public Collection<ReservationRecord> getOverDueRecords(){
        return null;
    }



    


}

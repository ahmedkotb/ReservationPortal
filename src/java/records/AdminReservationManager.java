package records;

import java.util.Collection;
import java.util.Date;
import javax.jdo.Query;
import reservationPortalSystem.Admin;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author Ahmed Kotb
 */
public class AdminReservationManager
{

    private Admin admin;

    public AdminReservationManager(Admin admin)
    {
        this.admin = admin;
    }

    /**
     * clear a record ,, make the item associated with the record available for being reserved again
     * @param record the record to be cleared
     */
    public void clearRecord(ReservationRecord record)
    {
        record.setStatus(ReservationStatus.DONE);
        record.getMyReservationItem().returnBack(record.getInfo());
    }

    /**
     * return a list of overDue records that should have been cleared
     * @return collection of records
     */
    public Collection<ReservationRecord> getOverDueRecords()
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.setFilter("this.myDateInformation.isOverDue()");
        query.setOrdering("this.purchaseDate descending");
        Collection<ReservationRecord> result = (Collection) query.execute();

        return result;
    }

    /**
     * return a list of all records where there purchase date is with in the date intervals
     * @param start the starting date of the interval
     * @param end the ending date of the interval2
     * @return collection of records
     */
    public Collection<ReservationRecord> getAllRecords(Date start,Date end)
    {
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.setFilter("this.purchaseDate >= start  && this.purchaseDate <= end");
        query.setOrdering("this.purchaseDate descending");
        query.declareParameters("java.util.Date start ,java.util.Date end");
        Collection<ReservationRecord> result = (Collection) query.execute(start,end);

        return result;
    }

    /**
     * return a list of record where there status is payed so that the admin can clear it
     * and also if a customer end his reservation before what is supposed to be the admin can
     * clear his reserve record
     * @return
     */
     public Collection<ReservationRecord> getUnClearedRecords()
    {
         Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.setFilter("this.status == \"PAYED\" ");
        query.setOrdering("this.purchaseDate descending");
        Collection<ReservationRecord> result = (Collection) query.execute();

        return result;
    }

}

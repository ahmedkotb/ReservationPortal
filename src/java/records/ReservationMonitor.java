package records;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import javax.jdo.Query;
import reservationPortalSystem.ReservationPortalSystem;
/**
 * monitors the on hold reservation Records
 * @author Ahmed Kotb
 */
public class ReservationMonitor {

    //queue of onhold reservations
    private Queue<ReservationRecord> recordsQueue;

    //singlton instance
    private static ReservationMonitor monitor;


    private ReservationMonitor() {
        //load all the onhold reservations
        recordsQueue = new LinkedList();
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class, "this.status ==\"ONHOLD\"");
        query.setOrdering("this.purchaseDate ascending");
        Collection<ReservationRecord> result = (Collection) query.execute();

        for (ReservationRecord record : result)
            recordsQueue.add(record);
    }


    /**
     * get the singlton instance
     * @return the reservationMonitor instance
     */
    public static ReservationMonitor getInstance(){
        if (monitor == null)
            monitor = new ReservationMonitor();
        return monitor;
    }

    /**
     * adds a new onhold record to the queue to be monitored
     * @param record the record to be added
     */
    public void addRecord(ReservationRecord record){
        recordsQueue.add(record);
    }



    /**
     * refresh the queue by removing onhold reservations that passed an hour
     */
    public void refresh(){
        Date now = new Date();
        ReservationRecord record;
        while (!recordsQueue.isEmpty() && recordsQueue.peek().purchaseDate.after(now)){
            record = recordsQueue.remove();
            if (record.getStatus().equals(ReservationStatus.ONHOLD))
                record.setStatus(ReservationStatus.CANCELED);
        }
    }


}

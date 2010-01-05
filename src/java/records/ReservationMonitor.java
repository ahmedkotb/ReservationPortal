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


    //constant on hold time in Mins
    private final int ON_HOLD_TIME = 5;


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


    public int getON_HOLD_TIME() {
        return ON_HOLD_TIME;
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
        System.out.println("here");
        Date now = new Date();
        ReservationRecord record;
        while (!recordsQueue.isEmpty() && dateDiff(recordsQueue.peek().purchaseDate, now)){
            record = recordsQueue.remove();
            if (record.getStatus().equals(ReservationStatus.ONHOLD)){
                ReservationPortalSystem.getInstance().getConnection().currentTransaction().begin();
                record.setStatus(ReservationStatus.CANCELED);
                ReservationPortalSystem.getInstance().getConnection().currentTransaction().commit();
                record.returnItem();
                
            }
            System.out.println("removed + " + record.getPurchaseDate());
        }
    }

    /**
     * make sure that the difference between the two dates is equal to or larger than the onhold time
     * @param d1 first date
     * @param d2 second date
     * @return true if it is bigger , false if not
     */
    private boolean dateDiff(Date d1,Date d2){
        long diff = (d2.getTime() -d1.getTime())/1000/60;
        if (diff >= ON_HOLD_TIME)
            return true;
        return false;
    }

}

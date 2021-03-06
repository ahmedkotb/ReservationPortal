/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import utilities.MD5HashGenerator;
import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;
import items.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import records.*;

/**
 *
 * @author ahmed
 */
public class ReservationPortalSystem
{

    private static ReservationPortalSystem systemInstance;
    //private PersistenceManager databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");
    private PersistenceManager databaseConnector = Utilities.getPersistenceManager("/home/ahmed/database/database.odb");
    private ReservationItemManager itemManager;

    private ReservationPortalSystem()
    {
    }

    /**
     * init the Reservation Portal System
     */
    private void initSystem()
    {
        //load the item manager
        itemManager = new ReservationItemManager();
        //load the moniter and refresh old records
        ReservationMonitor.getInstance().refresh();
    }

    /**
     * return the Reservation Item Manager of the system
     * the client of this method should get the instance in one of the read only interfaces
     * @return ReservationItemManager
     * @see ICustomerReservationItemManager
     * @see IAdminReservationItemManager
     */
    public ReservationItemManager getItemManager()
    {
        return itemManager;
    }

    public PersistenceManager getConnection()
    {
        return databaseConnector;
    }

    public static ReservationPortalSystem getInstance()
    {
        if (systemInstance == null)
        {
            systemInstance = new ReservationPortalSystem();
            systemInstance.initSystem();
        }

        return systemInstance;
    }

    /**
     * login method
     * @param userName the user name entered in the login form
     * @param password the password entered in the login form
     * @return a user object or userNotfoundException if the user doesn't exit
     */
    public User login(String userName, String password) throws Exception
    {

        Query query = databaseConnector.newQuery(User.class, "this.userName == userName");
        query.declareParameters("String userName");
        Collection result = (Collection) query.execute(userName);
        Iterator itr = result.iterator();

        //check if the user is found in the users database
        if (itr.hasNext() == false)
        {
            throw new Exception("UserNotFoundException");
        }

        //get the user
        User user = (User) itr.next();

        //check if the admin was activated
        if (user instanceof Admin && !((Admin) user).isActivated())
        {
            throw new Exception("NotActivatedException");
        }

        //compare given password with the hash generated
        if (MD5HashGenerator.generateHash(password).equals(user.getPassword()))
        {
            //refresh the queue of reservations
            ReservationMonitor.getInstance().refresh();
            databaseConnector.currentTransaction().begin();
            user.setLoggedIn(true);
            user.setLastLoginDate(new Date());
            databaseConnector.currentTransaction().commit();
            return user;
        } else
        {
            throw new Exception("UserNotFoundException");
        }

    }

    public void logout(User user)
    {
        databaseConnector.currentTransaction().begin();
        user.setLoggedIn(false);
        databaseConnector.currentTransaction().commit();
    }

    /**
     * make sure that user exists
     * @param userName the user name
     * @return true if exists , false if not
     */
    public boolean isUserExists(String userName)
    {
        Query query = databaseConnector.newQuery(User.class, "this.userName == userName");
        query.declareParameters("String userName");
        Collection result = (Collection) query.execute(userName);
        if (result.size() != 0)
        {
            return true;
        }
        return false;
    }

    /**
     * registers a new user
     * @param user the user to be registerd
     */
    public void register(User user)
    {
        save(user);
    }

    synchronized public void save(Object presistantObject)
    {
        try
        {
            databaseConnector.currentTransaction().begin();  //start transiction
            databaseConnector.makePersistent(presistantObject);
            databaseConnector.currentTransaction().commit();    //end transiction
        } finally
        {
            // Close the database and active transaction:
            if (databaseConnector.currentTransaction().isActive())
            {
                databaseConnector.currentTransaction().rollback();
            }
            if (!databaseConnector.isClosed())
            {
                //databaseConnector.close();
            }
        }
    }

    /**
     * returns all admins in the system
     * @return collection of admins
     */
    public Collection<Admin> getAllAdmins()
    {
        Query query = databaseConnector.newQuery(Admin.class);
        Collection result = (Collection) query.execute();
        return result;
    }

    /**
     * returns a list of new admin page
     * @return a collection of the admins that wasnt acctivated yet
     */
    public Collection<Admin> getNewAdmins()
    {
        Query query = databaseConnector.newQuery(Admin.class, "this.lastLoginDate == null && this.activated == false");
        Collection result = (Collection) query.execute();
        return result;
    }



    /**
     * returns the annual profit by the system
     * @return ammount of profit the system had during this year
     */
    public double getAnnualProfit(){
        double profit = 0.0;
        //a date that is a week behind
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("int currentYear");
        query.setFilter("( ( (this.status == \"PAYED\") || (this.status == \"DONE\" ) ) && ( this.purchaseDate.getYear() == currentYear ) )");
        Collection<ReservationRecord> result = (Collection<ReservationRecord>) query.execute(new Date().getYear());
        for (ReservationRecord record : result)
            profit+= record.getPrice();
        return profit;

    }

    /**
     * returns the weekly profit
     * @return ammount of profit the system had during this year
     */
    public double getWeeklyProfit(){
        double profit = 0.0;
        //a date that is a week behind
        Date date = new Date((new Date()).getTime() - 7 * 24 * 60 * 60 * 1000);
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(ReservationRecord.class);
        query.declareParameters("java.util.Date date");
        query.setFilter("( ( (this.status == \"PAYED\") || (this.status == \"DONE\" ) ) && ( this.purchaseDate.after(date) ) )");
        Collection<ReservationRecord> result = (Collection<ReservationRecord>) query.execute(date);
        for (ReservationRecord record : result)
            profit+= record.getPrice();
        return profit;
    }

    /**
     * activate or deactivate an admin with a specific name according to the value given
     * @param adminUserName the user name to be activated
     * @param value if true the admin will be activated else it will be disactivated and will not be allowed to login
     */
    public void setAdminActivation(String adminUserName, boolean value)
    {
        Query query = databaseConnector.newQuery(Admin.class, "this.userName == adminUserName");
        query.declareParameters("String adminUserName");
        Collection result = (Collection) query.execute(adminUserName);
        //if (result.size() == 0) return;
        Admin admin = (Admin) result.iterator().next();
        databaseConnector.currentTransaction().begin();
        admin.setActivated(value);
        databaseConnector.currentTransaction().commit();
    }


    /**
     * test methods to add locations
     * REMEMBER TO CHMOD 777 the database first
     * DONT ADD EMPTY LINES TO THE FILE
     */
    public static void addlocations(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("locations")));
            String line = "";
            String[] tokens;
            while (( line =  br.readLine()) !=null){
                tokens = line.split(",");
                Location newloc = new Location(tokens[0],tokens[1], tokens[2]);
                ReservationPortalSystem.getInstance().save(newloc);
            }

        } catch (Exception ex) {

        }
    }
   
    public static void main(String[] args) throws Exception
    {
        //test method

        System.out.println("testing....");
        com.objectdb.Enhancer.enhance("reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer , reservationPortalSystem.Owner,items.*,records.*");
        //addlocations();
        System.exit(0);

        ReservationPortalSystem systemInstance = getInstance();
        systemInstance.getConnection();
        Admin x = new Admin("toot", "toot", "toot", "teet", "@", "010", true, "good admin , worked in xyz for 3 days");
        //systemInstance.login("toot","toot");
        Location l = new Location("1", "1", "1");
        Location l2 = new Location("2", "2", "2");
        Airport air = new Airport("123", "test", l);

        ArrayList<Location> ll = new ArrayList<Location>();
        ll.add(l2);
        ll.add(l);
        CarAgency ag = new CarAgency("motor ride", ll);
        Car c = new Car(10, "BMW", CarType.Economy, 9, 150, ag, x);
        //ReservationPortalSystem systemInstance = getInstance();
        // systemInstance.save(air);
        System.out.println(l2.hasAirport());
        systemInstance.initSystem();
        //systemInstance.save(c);
        Car d = new Car();
        d.setObjectData(c.getObjectData());
        //systemInstance.save(d);
        //x.setName("Ahmed Mohsen");

        Room r1 = new Room(150.0, 5, 12);
        Room r2 = new Room(200.0, 3, 10);

        ArrayList<Room> r = new ArrayList<Room>();
        r.add(r1);
        r.add(r2);



        Hotel h = new Hotel(3, l, 4, null, r, x);
        // systemInstance.save(h);

        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
//        fields.put("carType", CarType.Economy.toString());
//        fields.put("carModel", "BMW");
//        fields.put("pickupLocation", l);
//        fields.put("returnLocation", l2);

//        fields.put("location", l);
//        fields.put("stars", 4);
//        fields.put("guestNumber", 5);


        //fields.put("endDate", new Date());
        //fields.put("startDate", new Date());
        //fields.put("sourceAirport", new Airport());
        //fields.put("destinationAirport", new Airport());


//        // CarReservation r=new CarReservation();
        //System.out.println(fields);
//         Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Car.class);
//         Collection  result2 = (Collection ) query.execute();
//         //System.out.println(result2);



        SearchItemManager ss = new SearchItemManager(fields, TYPE.FLIGHT);
        //Collection result = ss.searchItems();
        //Iterator itr = result.iterator();

        // System.out.println(result);
//        while (itr.hasNext())
//        {
//            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
//            System.out.println((itr.next()));
//        }

        //DoubleDate interval = new DoubleDate(new Date(110, 0, 1), new Date(111, 1, 5));

        // AdminReservationManager man=new AdminReservationManager(x);
        CustomerReservationManager man = new CustomerReservationManager(new Customer());
        //System.out.println(man.getAllRecords(new Date(),new Date()));
        System.out.println(man.getConfirmedReservations(new Date(), new Date()));

        CustomerReservationManager cm = new CustomerReservationManager(new Customer());

       // System.out.println(ReservationPortalSystem.getInstance().getTop10(Top_10.RATING));
    }
}

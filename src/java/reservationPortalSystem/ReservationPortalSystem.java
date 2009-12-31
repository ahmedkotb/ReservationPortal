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
import java.io.File;
import records.BankDraft;
import records.CarReservation;
import records.ReservationMonitor;
import records.ReservationRecord;

/**
 *
 * @author ahmed
 */
public class ReservationPortalSystem {

    private static ReservationPortalSystem systemInstance;
    //private PersistenceManager databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");
    private PersistenceManager databaseConnector = Utilities.getPersistenceManager("/home/ahmed/database/database.odb");
    private ReservationItemManager itemManager;

    private ReservationPortalSystem() {
    }

    /**
     * init the Reservation Portal System
     */
    private void initSystem() {
        itemManager = new ReservationItemManager();
    }

    /**
     * return the Reservation Item Manager of the system
     * the client of this method should get the instance in one of the read only interfaces
     * @return ReservationItemManager
     * @see ICustomerReservationItemManager
     * @see IAdminReservationItemManager
     */
    public ReservationItemManager getItemManager() {
        return itemManager;
    }

    public PersistenceManager getConnection() {
        return databaseConnector;
    }

    public static ReservationPortalSystem getInstance() {
        if (systemInstance == null) {
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
    public User login(String userName, String password) throws Exception {

        Query query = databaseConnector.newQuery(User.class, "this.userName == userName");
        query.declareParameters("String userName");
        Collection result = (Collection) query.execute(userName);
        Iterator itr = result.iterator();

        //check if the user is found in the users database
        if (itr.hasNext() == false) {
            throw new Exception("UserNotFoundException");
        }

        //get the user
        User user = (User) itr.next();

        //check if the admin was activated
        if (user instanceof Admin && !((Admin) user).isActivated()) {
            throw new Exception("NotActivatedException");
        }

        //compare given password with the hash generated
        if (MD5HashGenerator.generateHash(password).equals(user.getPassword())) {
            databaseConnector.currentTransaction().begin();
            user.setLoggedIn(true);
            user.setLastLoginDate(new Date());
            databaseConnector.currentTransaction().commit();
            return user;
        } else {
            throw new Exception("UserNotFoundException");
        }

    }

    public void logout(User user) {
        databaseConnector.currentTransaction().begin();
        user.setLoggedIn(false);
        databaseConnector.currentTransaction().commit();
    }

    /**
     * registers a new user
     * @param user the user to be registerd
     */
    public void register(User user) {
        save(user);
    }

    synchronized public void save(Object presistantObject) {
        try {
            //databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");
            databaseConnector.currentTransaction().begin();  //start transiction
            databaseConnector.makePersistent(presistantObject);
            databaseConnector.currentTransaction().commit();    //end transiction
        } finally {
            // Close the database and active transaction:
            if (databaseConnector.currentTransaction().isActive()) {
                databaseConnector.currentTransaction().rollback();
            }
            if (!databaseConnector.isClosed()) {
                //databaseConnector.close();
            }
        }
    }

    /**
     * returns all admins in the system
     * @return collection of admins
     */
    public Collection<Admin> getAllAdmins() {
        Query query = databaseConnector.newQuery(Admin.class);
        Collection result = (Collection) query.execute();
        return result;
    }

    /**
     * returns a list of new admin page
     * @return a collection of the admins that wasnt acctivated yet
     */
    public Collection<Admin> getNewAdmins() {
        Query query = databaseConnector.newQuery(Admin.class, "this.lastLoginDate == null && this.activated == false");
        Collection result = (Collection) query.execute();
        return result;
    }

    /**
     * activate or deactivate an admin with a specific name according to the value given
     * @param adminUserName the user name to be activated
     * @param value if true the admin will be activated else it will be disactivated and will not be allowed to login
     */
    public void setAdminActivation(String adminUserName, boolean value) {
        Query query = databaseConnector.newQuery(Admin.class, "this.userName == adminUserName");
        query.declareParameters("String adminUserName");
        Collection result = (Collection) query.execute(adminUserName);
        //if (result.size() == 0) return;
        Admin admin = (Admin) result.iterator().next();
        databaseConnector.currentTransaction().begin();
        admin.setActivated(value);
        databaseConnector.currentTransaction().commit();
    }

    public static void main(String[] args) throws Exception {
        //test method
        
        System.out.println("testing....");
        com.objectdb.Enhancer.enhance("reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer , reservationPortalSystem.Owner,items.*,records.*");


        //remove the system.exit0 to test any thing else
        System.exit(0);
        ReservationPortalSystem systemInstance = getInstance();
        systemInstance.getConnection();
        Admin x = new Admin("toot", "toot", "toot", "teet", "@", "010", true, "good admin , worked in xyz for 3 days");
        //systemInstance.login("toot","toot");
        Location l = new Location("1", "1", "1");
        Location l2 = new Location("2", "2", "2");
        ArrayList<Location> ll = new ArrayList<Location>();
        ll.add(l2);
        ll.add(l);
        CarAgency ag = new CarAgency("motor ride", ll);
        Car c = new Car(10, "Mercedes", CarType.Economy, 9, 150, ag, x);
        //ReservationPortalSystem systemInstance = getInstance();
        //systemInstance.save(x);
        systemInstance.initSystem();
        //systemInstance.save(c);
        Car d = new Car();
        d.setObjectData(c.getObjectData());
        //systemInstance.save(d);
        //x.setName("Ahmed Mohsen");

        Payment p = new BankDraft();
        systemInstance.save(p);

        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("carType", CarType.Economy.toString());
        fields.put("carModel", "Mercedes");
        //fields.put("pickupLocation", l);
        //fields.put("returnLocation", l2);
        // CarReservation r=new CarReservation();
        //System.out.println(fields);

        SearchItemManager ss = new SearchItemManager(fields, searchType.CAR);
        // Collection<Car> result = ss.searchItems();
        //Iterator itr = result.iterator();


        //while (itr.hasNext())
        {
            System.out.println("heeeeeereeeeee");
            //  System.out.println(((Car)itr.next()).getCarType());
        }

        //DoubleDate interval = new DoubleDate(new Date(110, 0, 1), new Date(111, 1, 5));
    }
}

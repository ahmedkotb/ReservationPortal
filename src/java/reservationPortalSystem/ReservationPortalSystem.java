/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.MD5HashGenerator;
import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;
import items.Car;
import items.CarAgency;
import items.CarType;
import items.Location;
import java.io.File;

/**
 *
 * @author ahmed
 */
public class ReservationPortalSystem {

    private static ReservationPortalSystem systemInstance;
    private static PersistenceManager databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");

    private ReservationPortalSystem() {
    }

    public static PersistenceManager getConnection() {
        return databaseConnector;
    }

    public static ReservationPortalSystem getInstance() {
        if (systemInstance == null) {
            systemInstance = new ReservationPortalSystem();
            systemInstance.initSystem();
        }

        return systemInstance;
    }

    private void initSystem() {
 
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

        if (itr.hasNext() == false) {
            throw new Exception("UserNotFoundException");
        }


        User user = (User) itr.next();
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
    public Collection<Admin> getAllAdmins(){
        Query query = databaseConnector.newQuery(Admin.class);
        Collection result = (Collection) query.execute();
        return result;
    }

    /**
     * returns a list of new admin page
     * @return a collection of the admins that wasnt acctivated yet
     */
    public Collection<Admin> getNewAdmins(){
        Query query = databaseConnector.newQuery(Admin.class, "this.lastLoginDate == null && this.activated == false");
        Collection result = (Collection) query.execute();
        return result;
    }

    /**
     * activate an admin with a specific name
     * @param adminUserName the user name to be activated
     */
    public void activateAdmin(String adminUserName){
        Query query = databaseConnector.newQuery(Admin.class, "this.userName == adminUserName");
        query.declareParameters("String adminUserName");
        Collection result = (Collection) query.execute(adminUserName);
        //if (result.size() == 0) return;
        Admin admin = (Admin)result.iterator().next();
        databaseConnector.currentTransaction().begin();
        admin.setActivated(true);
        databaseConnector.currentTransaction().commit();
       
    }



    public static void main(String[] args) throws Exception {
        //test method
        ReservationPortalSystem systemInstance = getInstance();
        System.out.println("testing....");
        com.objectdb.Enhancer.enhance("items.*,reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer , reservationPortalSystem.Owner");
//        User x = new Admin("toot", "toot", "toot", "teet", "@", "010", true, "good admin , worked in xyz for 3 days");
//        systemInstance.login("toot","toot");
//        Location l=new Location("1", "1", "1");
//        Location l2=new Location("2", "2", "2");
//        ArrayList<Location> ll=new ArrayList<Location>();
//        ll.add(l2);ll.add(l);
//        CarAgency ag=new CarAgency("motor ride", ll);
//      Car c=new Car(10, "Mercedes", CarType.Economy, 9, 150, ag);
//        //ReservationPortalSystem systemInstance = getInstance();
//        //systemInstance.getConnection();
//        //systemInstance.initSystem();
//        //systemInstance.save(c);
//        Car d=new Car();
//        d.setObjectData( c.getObjectData());
//        //systemInstance.save(d);
//        //x.setName("Ahmed Mohsen");
    }
}

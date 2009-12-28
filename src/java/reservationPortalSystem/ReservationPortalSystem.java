/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.security.NoSuchAlgorithmException;
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
public class ReservationPortalSystem
{

    private static ReservationPortalSystem systemInstance;
    private static PersistenceManager databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");

    private ReservationPortalSystem()
    {
    }

    public static PersistenceManager getConnection()
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

    private void initSystem()
    {

        //com.objectdb.Enhancer.enhance("reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer");
        //com.objectdb.Enhancer.enhance("reservationPortalSystem.*");
    }

    /**
     * login method
     * @param userName the user name entered in the login form
     * @param password the password entered in the login form
     * @return a user object or null if the user doesn't exit
     */
    public User login(String userName, String password)
    {
        //testing
        //assuming that we have only one user with user name ahmed and password hello
        //x is the object in the data base
        //hello md5 hash is : 5d41402abc4b2a76b9719d911017c592
        User x = new Admin("ahmed kotb", "ahmed", "5d41402abc4b2a76b9719d911017c592", "Alex", "@", "010", true, "good admin , worked in xyz for 3 days");
        //login steps...
        //generate the hash of the username compare it to hash of username required
        if (MD5HashGenerator.generateHash(password).equals(x.getPassword()))
        {
            return x;
        }

        return null;
    }

    public void logout(User user)
    {
    }

    public void register(User user)
    {
    }

    synchronized public void save(Object presistantObject)
    {
        try
        {
            //databaseConnector = Utilities.getPersistenceManager("database" + File.separator + "database.odb");
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

    public static void main(String[] args)
    {
        //test method
        System.out.println("testing....");
      // com.objectdb.Enhancer.enhance("items.*,reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer");
        User x = new Admin("toot", "toot", "toot", "teet", "@", "010", true, "good admin , worked in xyz for 3 days");
        Location l=new Location("1", "1", "1");
        Location l2=new Location("2", "2", "2");
        ArrayList<Location> ll=new ArrayList<Location>();
        ll.add(l2);ll.add(l);
        CarAgency ag=new CarAgency("motor ride", ll);
      Car c=new Car(10, "Mercedes", CarType.Economy, 9, 150, ag);
        ReservationPortalSystem systemInstance = getInstance();
        //systemInstance.getConnection();
        //systemInstance.initSystem();
        //systemInstance.save(c);
        Car d=new Car();
        d.setObjectData( c.getObjectData());
        systemInstance.save(d);
        //x.setName("Ahmed Mohsen");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.MD5HashGenerator;

/**
 *
 * @author ahmed
 */
public class ReservationPortalSystem {

    private static ReservationPortalSystem systemInstance;

    private ReservationPortalSystem() {
    }

    public static ReservationPortalSystem getInstance() {
        if (systemInstance == null) {
            systemInstance = new ReservationPortalSystem();
        }

        return systemInstance;
    }

    private void initSystem() {
    }

    /**
     * login method
     * @param userName the user name entered in the login form
     * @param password the password entered in the login form
     * @return a user object or null if the user doesn't exit
     */
    public User login(String userName, String password) {
        //testing
        //assuming that we have only one user with user name ahmed and password hello
        //x is the object in the data base
        //hello md5 hash is : 5d41402abc4b2a76b9719d911017c592
        User x = new Admin("ahmed kotb","ahmed", "5d41402abc4b2a76b9719d911017c592", "Alex", "@", "010", true);
        try {
            //login steps...
            //generate the hash of the username compare it to hash of username required
            if (MD5HashGenerator.generateHash(password).equals(x.getPassword())) {
                return x;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ReservationPortalSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void logout(User user) {
    }

    public void register(User user) {
    }
}

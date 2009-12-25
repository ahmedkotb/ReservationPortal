/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

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
        //hello md5 hash is :
        User x = new Admin("ahmed kotb","ahmed", "hello", "Alex", "@", "010", true);


        return x;
    }

    public void logout(User user) {
    }

    public void register(User user) {
    }
}

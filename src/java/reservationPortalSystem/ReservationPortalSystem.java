/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package reservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class ReservationPortalSystem
{
    private static ReservationPortalSystem systemInstance;

    private  ReservationPortalSystem()
    {

    }



    public static ReservationPortalSystem getInstance()
    {
        if(systemInstance==null)
            systemInstance=new ReservationPortalSystem();

        return systemInstance;
    }

    private void initSystem()
    {
    }

    public User login(String userName, String password)
    {
        //testing
        return new Admin(userName, password, "Alex", "@", "010", true);
    }

    public void logout(User user)
    {
    }

    public void register(User user)
    {
    }
}

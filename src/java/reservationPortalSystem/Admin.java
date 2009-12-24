/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class Admin extends User
{

    private boolean activated;

    public Admin()
    {
        super();
    }

    public Admin(String loginName, String password, String address, String email, String phoneNumber, boolean activated)
    {
        super(loginName, password, address, email, phoneNumber);
        this.activated = activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public boolean isActivated()
    {
        return activated;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class Admin extends User{

    private boolean activated;
    private String qualifications;


    public Admin(){
        super();
    }

    public Admin(String name,String loginName, String password, String address, String email, String phoneNumber, boolean activated,String qualifications){
        super(name,loginName, password, address, email, phoneNumber);
        this.activated = activated;
        this.qualifications = qualifications;
    }

    public void setActivated(boolean activated){
        this.activated = activated;
    }

    public boolean isActivated(){
        return activated;
    }


    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}

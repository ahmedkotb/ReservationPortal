/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class Customer extends User {

    public Customer(){
        super();
    }

    public Customer(String name,String loginName, String password, String address, String email, String phoneNumber){
        super(name,loginName, password, address, email, phoneNumber);
    }



}

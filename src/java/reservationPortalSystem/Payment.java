/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public interface Payment
{

    /**
     * return a hash map representing the item attributes
     * @return the attributes map
     */
    public HashMap getIformation();

    public void setInformation(HashMap fields);
}

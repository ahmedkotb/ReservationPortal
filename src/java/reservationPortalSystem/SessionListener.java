/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reservationPortalSystem;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * A Session Listener Class to detect session timeout
 * @author Ahmed Kotb
 */
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        //to make sure this happens only for the timeout sessions
        //not sessions destroyed by the user
        if (sessionEvent.getSession() != null)
            ReservationPortalSystem.getInstance().logout((User)sessionEvent.getSession().getAttribute("user"));
    }

}

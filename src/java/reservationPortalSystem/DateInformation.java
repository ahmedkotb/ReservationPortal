/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.util.Date;

/**
 *the data holder for any dates in reservation items
 * @author ahmed
 */
public interface DateInformation
{

    public boolean isOverDue();

    public boolean isInBetween(Date myDate);

    public boolean validate();
}

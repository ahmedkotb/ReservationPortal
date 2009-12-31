/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import reservationPortalSystem.DateInformation;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class SingleDate implements DateInformation
{

    private Date StartDate;

    public SingleDate()
    {
        StartDate = new Date();
    }

    public SingleDate(Date StartDate)
    {
        this.StartDate = StartDate;
    }

    public Date getStartDate()
    {
        return StartDate;
    }

    public void setStartDate(Date StartDate)
    {
        this.StartDate = StartDate;
    }

    public boolean isOverDue()
    {
        //in this case the reserved item has no return date so it's not matter
        return false;
    }

    public boolean validate()
    {
        Date today = new Date();
        // check if the reservation will start after the current date
        if (StartDate.before(today))
        {
            return false;
        }
        return true;


    }
}

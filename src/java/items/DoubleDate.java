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
public class DoubleDate implements DateInformation {

private Date StartDate;
private Date endDate;

    public DoubleDate()
    {
        StartDate=new Date();
        endDate = new Date();
    }

    public DoubleDate(Date StartDate, Date endDate)
    {
        this.StartDate = StartDate;
        this.endDate = endDate;
    }

    public Date getStartDate()
    {
        return StartDate;
    }

    public void setStartDate(Date StartDate)
    {
        this.StartDate = StartDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public boolean isOverDue()
    {
        Date today =new Date();

        //check of the return date has passed
        if(endDate.before(today))
            return true;
        return false;
    }

    public boolean validate()
    {
        Date today = new Date();
        // check if the reservation will start after the current date
        if (StartDate.before(today))
            return false;
        //check endDate after StartDate
        if(endDate.before(StartDate))
            return false;
        return true;
    }

    public boolean isInBetween(Date myDate)
    {
        //check that the given date is in between the start and end dates
        if((StartDate.equals(myDate) || myDate.after(myDate)) && (endDate.equals(myDate) ||myDate.before(endDate) ))
            return true;
        return false;
    }


}

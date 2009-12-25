/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *Rooms in a Hotel
 * @author ahmed
 */
public class Room
{

    private double pricePerDay;
    private int guestNumber;

    public Room()
    {
        pricePerDay=0.0;
        guestNumber=0;
    }

    public Room(int pricePerDay, int guestNumber)
    {
        this.pricePerDay = pricePerDay;
        this.guestNumber = guestNumber;
    }

    public int getGuestNumber()
    {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber)
    {
        this.guestNumber = guestNumber;
    }

    public double getPricePerDay()
    {
        return pricePerDay;
    }

    public void setPricePerDay(double  pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }
}

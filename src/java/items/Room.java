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
    private int roomNumber;
    private boolean reserved;   //a flag to determine whether this room is reservsed or not

    public Room()
    {
        pricePerDay = 0.0;
        guestNumber = 0;
        roomNumber = 0;
        reserved = false;
    }

    public Room(int pricePerDay, int guestNumber, int roomNumber)
    {
        this.pricePerDay = pricePerDay;
        this.guestNumber = guestNumber;
        this.roomNumber = roomNumber;
        reserved = false;
    }

    /**
     * another constructor that don't take the room number as parameter as it will auto incremented when used in Hotels
     * @param pricePerDay
     * @param guestNumber
     */
    public Room(int pricePerDay, int guestNumber)
    {
        this.pricePerDay = pricePerDay;
        this.guestNumber = guestNumber;
        reserved = false;
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

    public void setPricePerDay(double pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    public boolean isReserved()
    {
        return reserved;
    }

    public void setReserved(boolean reserved)
    {
        this.reserved = reserved;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
}

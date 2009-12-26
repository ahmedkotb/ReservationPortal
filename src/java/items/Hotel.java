/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class Hotel extends ReservationItem
{

    private Location location;
    private int stars;
    private ArrayList<Hotel> branches;
    public ArrayList<Room> myRooms;

    public Hotel()
    {
        location = new Location();
        stars = 0;
        branches = new ArrayList<Hotel>();
        myRooms = new ArrayList<Room>();
    }

    public Hotel(int quantity, Location location, int stars, ArrayList<Hotel> branches, ArrayList<Room> myRooms)
    {
        super(quantity);
        this.location = location;
        this.stars = stars;
        this.branches = branches;
        this.myRooms = myRooms;
    }

    public ArrayList<Hotel> getBranches()
    {
        return branches;
    }

    public void setBranches(ArrayList<Hotel> branches)
    {
        this.branches = branches;
    }

    public void addBranches(Hotel hotel)
    {
        branches.add(hotel);
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public ArrayList<Room> getMyRooms()
    {
        return myRooms;
    }

    public void addRoom(Room room)
    {
        myRooms.add(room);
    }

    public void setMyRooms(ArrayList<Room> myRooms)
    {
        this.myRooms = myRooms;
    }

    public int getStars()
    {
        return stars;
    }

    public void setStars(int stars)
    {
        this.stars = stars;
    }

    @Override
    public void reserve(HashMap<String, Integer> info)
    {
        //getting the number of the reserved room
        int roomNumber = info.get("room number");
        //search for that room in the hotel
        for (Room room : myRooms)
        {
            if (room.getRoomNumber() == roomNumber)    //if found set its status with reserved
            {
                room.setReserved(true);
                break;  //no need to continue
            }
        }
    }

    @Override
    public void returnBack(HashMap<String, Integer> info)
    {
        //getting the number of the reserved room
        int roomNumber = info.get("room number");
        //search for that room in the hotel
        for (Room room : myRooms)
        {
            if (room.getRoomNumber() == roomNumber)    //if found set its status with returned
            {
                room.setReserved(false);
                break;  //no need to continue
            }
        }
    }

    @Override
    public boolean isInUse()
    {
        //search the whole Hotel if any room is reserved so it is already in use else such that its not in use
        for (Room room : myRooms)
        {
            //a reserved room found
            if (room.isReserved())
            {
                return true;
            }
        }
        return false;

    }
}

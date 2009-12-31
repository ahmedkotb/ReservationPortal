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
        //auto incrementing the room number
        room.setRoomNumber(myRooms.size()+1);
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
        int roomNumber = info.get("roomNumber");
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
        int roomNumber = info.get("roomNumber");
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

     @Override
    public HashMap getObjectData()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.putAll(super.getObjectData());   //putting the common attributes from the abstract class in the map
        fields.put("location", location);
        fields.put("stars", stars);
        fields.put("branches", branches);
        fields.put("myRooms", myRooms);
        return fields;
    }

    public void setObjectData(HashMap fields)
    {
        //setting the attributes from parent class
        quantity = (Integer) fields.get("quantity");
        rating = (Double) fields.get("rating");
        reviews = (ArrayList<Review>) fields.get("reviews");
        //setting the concrete class attributess
        location=(Location) fields.get("location");
        stars=(Integer)fields.get("stars");
        branches=(ArrayList<Hotel>) fields.get("branches");
        myRooms=(ArrayList<Room>) fields.get("myRooms");

        
    }
   
}

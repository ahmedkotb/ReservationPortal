/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import java.util.ArrayList;

/**
 * the store where the customer will rent cars from it
 * @author ahmed
 */
public class CarAgency {

    private String name;
    private String description;
    private ArrayList<Location> supportedLocations;

    public CarAgency()
    {
        name="";
        supportedLocations=new ArrayList<Location>();
    }

    public CarAgency(String name, ArrayList<Location> supportedLocations)
    {
        this.name = name;
        this.supportedLocations = supportedLocations;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Location> getSupportedLocations()
    {
        return supportedLocations;
    }

    public void setSupportedLocations(ArrayList<Location> supportedLocations)
    {
        this.supportedLocations = supportedLocations;
    }
    /**
     * Add a new location to the supported list of the Agency
     * @param newLocation the location which will be added to the supported list of the Agency
     */
    public void addLocation(Location newLocation)
    {
        supportedLocations.add(newLocation);
    }
     /**
      *  delete an old location from the supported list of the Agency
      * @param oldLocation the location which will be deleted from the supported list of the Agency
      */
    public void deleteLocation(Location oldLocation)
    {
        supportedLocations.remove(oldLocation);
    }
}

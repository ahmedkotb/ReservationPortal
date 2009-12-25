/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author ahmed
 */
public class Airport
{

    private String code;
    private String name;
    private Location myLocation;

    public Airport()
    {
        code = "";
        name = "";
        myLocation = new Location();

    }

    public Airport(String code, String name, Location myLocation)
    {
        this.code = code;
        this.name = name;
        this.myLocation = myLocation;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Location getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(Location myLocation)
    {
        this.myLocation = myLocation;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    

}

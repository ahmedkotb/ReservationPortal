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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Airport other = (Airport) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code))
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if (this.myLocation != other.myLocation && (this.myLocation == null || !this.myLocation.equals(other.myLocation)))
        {
            return false;
        }
        return true;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 59 * hash + (this.myLocation != null ? this.myLocation.hashCode() : 0);
        return hash;
    }
    

}

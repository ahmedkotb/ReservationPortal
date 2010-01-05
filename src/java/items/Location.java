/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Collection;
import javax.jdo.Query;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class Location
{

    private String country;
    private String city;
    private String street;

    public Location()
    {
        country = "";
        city = "";
        street = "";
    }

    public Location(String country, String city, String street)
    {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * used to check whether this location has an Airport or not by looking through the database
     * @return a flag which is true if this location has Airport and vice versa
     */
    public boolean hasAirport()
    {//database lookup
        Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(Airport.class, "this.myLocation.equals(location)");
        query.declareParameters("Location location");
        Collection result = (Collection) query.execute(this);
        if (result.size() > 0)
        {   //one airport or more lie in this location
            return true;
        }
        return false;
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
        final Location other = (Location) obj;
        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country))
        {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city))
        {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.country != null ? this.country.hashCode() : 0);
        hash = 53 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 53 * hash + (this.street != null ? this.street.hashCode() : 0);
        return hash;
    }
}

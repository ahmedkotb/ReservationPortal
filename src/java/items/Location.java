/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

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
        return false;
    }
}

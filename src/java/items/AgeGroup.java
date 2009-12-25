/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 * the flight tickets types
 * @author ahmed
 */
public class AgeGroup
{

    private String name;
    private Double price;

    public AgeGroup()
    {
        name="";
        price=0.0;
    }

    public AgeGroup(String name, Double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }
}

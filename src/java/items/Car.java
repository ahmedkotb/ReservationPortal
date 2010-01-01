/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;
import java.util.HashMap;
import reservationPortalSystem.Admin;

/**
 *
 * @author ahmed
 */
public class Car extends ReservationItem
{

    private String carModel;
    private String carType;     //this string will be saved @ database but will be controlled through the enum CarType
    private int availableNumber;
    private double rentPrice;
    private CarAgency myAgency;

    public Car()
    {
        super();
        carModel = "";
        carType = "";
        availableNumber = 0;
        rentPrice = 0.0;
        myAgency = new CarAgency();
    }

    public Car(int quantity, String carModel, CarType carType, int availableNumber, double rentPrice, CarAgency myAgency,Admin provider)
    {
        super(quantity,provider);
        this.carModel = carModel;
        this.carType = carType.toString();
        this.availableNumber = availableNumber;
        this.rentPrice = rentPrice;
        this.myAgency = myAgency;
    }

    public int getAvailableNumber()
    {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber)
    {
        this.availableNumber = availableNumber;
    }

    public String getCarModel()
    {
        return carModel;
    }

    public void setCarModel(String carModel)
    {
        this.carModel = carModel;
    }

    public CarType getCarType()
    {
        return CarType.valueOf(carType);
    }

    public void setCarType(CarType carType)
    {
        this.carType = carType.toString();
    }

    public CarAgency getMyAgency()
    {
        return myAgency;
    }

    public void setMyAgency(CarAgency myAgency)
    {
        this.myAgency = myAgency;
    }

    public double getRentPrice()
    {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice)
    {
        this.rentPrice = rentPrice;
    }

    @Override
    public void reserve(HashMap<String, Object> info)
    {
        //the parameter will be ignored as there is only one available number for car
        availableNumber--;
    }

    @Override
    public void returnBack(HashMap<String, Object> info)
    {
        //the parameter will be ignored as there is only one available number for car
        availableNumber++;
    }

    @Override
    public boolean isInUse()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap getObjectData()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.putAll(super.getObjectData());   //putting the common attributes from the abstract class in the map
        fields.put("carType", carType);
        fields.put("carModel", carModel);
        fields.put("availableNumber", availableNumber);
        fields.put("rentPrice", rentPrice);
        fields.put("myAgency", myAgency);
        return fields;
    }

    public void setObjectData(HashMap fields)
    {
        //setting the attributes from parent class
        quantity = (Integer) fields.get("quantity");
        rating = (Double) fields.get("rating");
        reviews = (ArrayList<Review>) fields.get("reviews");
        //setting the concrete class attributess
        carType = (String) fields.get("carType");
        carModel = (String) fields.get("carModel");
        availableNumber = (Integer) fields.get("availableNumber");
        rentPrice = (Double) fields.get("rentPrice");
        myAgency = (CarAgency) fields.get("myAgency");
    }

}

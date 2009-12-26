/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.HashMap;

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
        carModel="";
        carType="";
        availableNumber=0;
        rentPrice=0.0;
        myAgency=new CarAgency();
    }

    public Car(String carModel, CarType carType, int availableNumber, double rentPrice, CarAgency myAgency)
    {
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
    public void reserve(HashMap<String, Integer> info)
    {
        //the parameter will be ignored as there is only one available number for car
        availableNumber--;
    }

    @Override
    public void returnBack(HashMap<String, Integer> info)
    {
        //the parameter will be ignored as there is only one available number for car
        availableNumber++;
    }

    @Override
    public boolean isInUse()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

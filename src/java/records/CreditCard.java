/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import reservationPortalSystem.Payment;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class CreditCard implements Payment
{

    private int cardNumber;
    private Date expireDate;
    private String billingAddress;

    public CreditCard()
    {
        cardNumber = 0;
        expireDate = new Date();
        billingAddress = "";


    }

    public CreditCard(int cardNumber, Date expireDate, String billingAddress)
    {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.billingAddress = billingAddress;
    }

    public String getBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public int getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public HashMap getIformation()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("cardNumber", cardNumber);
        fields.put("expireDate", expireDate);
        fields.put("billingAddress", billingAddress);
        return fields;

    }

    public void setInformation(HashMap fields)
    {
        cardNumber = (Integer) fields.get("cardNumber");
        expireDate = (Date) fields.get("expireDate");
        billingAddress = (String) fields.get("billingAddress");

    }
}

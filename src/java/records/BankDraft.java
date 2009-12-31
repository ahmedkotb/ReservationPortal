/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import reservationPortalSystem.Payment;
import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class BankDraft implements Payment
{

    private String bankName;
    private int accountNumber;
    private int routingNumber;

    public BankDraft()
    {
        bankName = "";
        accountNumber = 0;
        routingNumber = 0;
    }

    public BankDraft(String bankName, int accountNumber, int routingNumber)
    {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public int getRoutingNumber()
    {
        return routingNumber;
    }

    public void setRoutingNumber(int routingNumber)
    {
        this.routingNumber = routingNumber;
    }

    public HashMap getIformation()
    {
        HashMap<String, Object> fields = new HashMap<String, Object>();    //the hash map containig the fields of the object
        fields.put("accountNumber", accountNumber);
        fields.put("bankName", bankName);
        fields.put("routingNumber", routingNumber);
        return fields;
    }

    public void setInformation(HashMap fields)
    {
        accountNumber = (Integer) fields.get("accountNumber");
        bankName = (String) fields.get("bankName");
        routingNumber = (Integer) fields.get("routingNumber");
    }
}

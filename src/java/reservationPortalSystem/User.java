/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import java.util.Date;

/**
 *
 * @author ahmed
 */
public abstract class User
{
    protected String name;
    protected String userName;
    protected String password;
    protected String address;
    protected String email;
    protected String phoneNumber;
    protected Date lastLoginDate;
    protected boolean loggedIn;

    public User(){
        name = "";
        userName = "" ;
        password ="";
        address="";
        email="";
        phoneNumber="0";
        lastLoginDate=new Date();
        loggedIn=false;
    }

    public User(String name,String userName, String password, String address, String email, String phoneNumber){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        lastLoginDate=new Date();
        loggedIn=false;
    }



    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getLastLoginDate(){
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate){
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
}

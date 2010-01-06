/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Date;
import reservationPortalSystem.User;

/**
 * Reviews of customers on the reservations items
 * @author ahmed
 */
public class Review
{

    private String comment;
    private User user;
    private Date date;

    public Review(){
        user = null;
        comment="";
        date = new Date();
    }

    public Review(User user,String comment){
        this.comment = comment;
        this.user = user;
        date = new Date();
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

}

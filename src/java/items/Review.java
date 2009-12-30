/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import reservationPortalSystem.User;

/**
 * Reviews of customers on the reservations items
 * @author ahmed
 */
public class Review
{

    private String comment;
    private User user;


    public Review(){
        comment="";
    }

    public Review(String comment){
        this.comment = comment;
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
}

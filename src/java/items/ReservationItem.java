/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public abstract class ReservationItem
{

    protected Double rating;      //the customer rating for this item
    protected int quantity;     //the orgin amount of the item
    protected ArrayList<Review> reviews;    // a list of reviews

    public ReservationItem()
    {
        quantity=0;
        rating=0.0;
        reviews=new ArrayList<Review>();
    }

    public ReservationItem(int quantity)
    {
        this.quantity = quantity;            //added by admin
        rating=0.0;                         //added by customer
        reviews=new ArrayList<Review>();    //added by customer
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public ArrayList<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews)
    {
        this.reviews = reviews;
    }


    /**
     * used to decrement the number of availabe items
     * @param info the data wanted to determine which avialable number of items to be decremented
     */
    public abstract void reserve(HashMap info);

    /**
     * used to increment the number of availabe items
     */
    public abstract void returnBack(HashMap info);

    /**
     *check if the item is reserved by any customer or not so that an admin can remove this item
     * @return a flag to determine whether it is in use or not
     */
    public abstract boolean isInUse();

    /**
     * this method is used as the factory for the reservation item
     * @param type the type of the item to be created
     * @return the created item
     */
    public static ReservationItem createItem(String type)
    {
        return null;
    }

    /**
     * Add a new review to the item
     * @param review the review to be added
     */
    public void AddReview(Review review)
    {
        reviews.add(review);
    }
}

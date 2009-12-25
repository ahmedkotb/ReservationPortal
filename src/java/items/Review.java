/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 * Reviews of customers on the reservations items
 * @author ahmed
 */
public class Review
{

    private String comment;

    public Review()
    {
        comment="";
    }

    public Review(String comment)
    {
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}

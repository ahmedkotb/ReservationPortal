package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import javax.jdo.Query;
import reservationPortalSystem.ReservationPortalSystem;
import reservationPortalSystem.User;

/**
 * perform various validation on given fields
 *
 * @author Ahmed Kotb and Ahmed Mohsen :D
 *
 */
public class Validator
{


	/**
	 * Checks if the given mail is valid or not
	 *
	 * @param email
	 *            the member mail
	 * @return true if valid isbn , false if not
	 */
	public boolean isValidEmail(String email)
	{
		// check that it contains @ and dot
		if (!email.contains("@") || !email.contains("."))
			return false;
		// make sure the @ is before the dot
		if (email.lastIndexOf('@') > email.lastIndexOf('.'))
			return false;
		return true;
	}

	/**
	 * Checks if the given date is valid
	 *
	 * @param inDate
	 *            the given date
	 * @param format
	 *            the date format to check the given date with
	 * @return True if , valid False if not
	 */
	public boolean isValidDate(String inDate, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.US);
		sdf.setLenient(false);
		try
		{
			sdf.parse(inDate);
		}
		catch (ParseException e)
		{
			return false;
		}

		return true; // passed the test
	}

	/**
	 * Checks if the given phone number is valid or not
	 *
	 * @param phoneNum
	 *            the member phone number
	 * @return True if valid , false if not
	 */
	public boolean isValidPhoneNum(String phoneNum)
	{
		// according to the assumption a phone number is valid if it contains
		// numbers only
		try
		{
			Integer.parseInt(phoneNum);
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks if the age of the member is valid
	 *
	 * @param age
	 *            the member age
	 * @return True if valid , false if not
	 */
	public boolean isValidAge(String age)
	{
		// according to the assumption max age is 150 and min is 6
		int memAge;
		try
		{
			memAge = Integer.parseInt(age);
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		if (memAge > 150 || memAge < 6)
			return false;

		return true;
	}

	/**
	 * Checks if the given id is valid
	 *
	 * @param id
	 *            The id of the member
	 * @return True if valid , false if not
	 */
	public boolean isValidId(String id)
	{
		// according to the assumption a id is valid if it contains
		// numbers only
		int memId=0;
		try
		{
			memId=Integer.parseInt(id);
		}
		catch (NumberFormatException e)
		{
			return false;
		}

		if (memId<0)
			return false;
		return true;
	}

        /**
         * checks if the user name is unique or not
         * @param userName the userName
         * @return True if valid , false if not
         */
       public boolean isUniqueUserName(String userName)
       {
           Query query = ReservationPortalSystem.getInstance().getConnection().newQuery(User.class,"this.userName.trim().toLowerCase() == userName");
           query.declareParameters("String userName");
           Collection result=(Collection) query.execute(userName.trim().toLowerCase());
           //one or more user found having the same name
           if(result.size()>0)
               return false;
           return true;


       }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reservationPortalSystem;

import com.objectdb.Utilities;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.MD5HashGenerator;
import javax.jdo.*;
import com.objectdb.Utilities;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author ahmed
 */
public class RegisterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();

        String requestStr  = (String)request.getParameter("req");
        if (requestStr == null || requestStr.equals("customer")){
            //go to the normal customer register page
            //getServletContext().getRequestDispatcher("/registeradmin.jsp").forward(request, response);
        }else if (requestStr.equals("admin")){
            //go to the admin register page
            getServletContext().getRequestDispatcher("/registeradmin.jsp").forward(request, response);
        }else if (requestStr.equals("registerCustomer")){
            //register the customer
        }else if (requestStr.equals("registerAdmin")){
            //register the user information
            //should get all the user info and create an admin object and save it to database
            //getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);



//
            //com.objectdb.Enhancer.enhance("reservationPortalSystem.User , reservationPortalSystem.Admin , reservationPortalSystem.Customer");
//
//            User x = new Admin("same7", "teet", "toot", "teet", "@", "010", true, "good admin , worked in xyz for 3 days");
            User x = getUserFromRequest(request);
            //PersistenceManager   databaseConnector = Utilities.getPersistenceManager("/home/ahmed/Projects/ReservationPortal/database/" + "database.odb");
            //databaseConnector.currentTransaction().begin(); //start transiction
            //databaseConnector.makePersistent(x);
//           // x.getAddress();
            //databaseConnector.currentTransaction().commit();    //end transiction

//             PrintWriter p = (PrintWriter)response.getWriter();
//
//                Query query = databaseConnector.newQuery(User.class);
//		Collection result = (Collection)query.execute();
//				Iterator itr = result.iterator();
//
//				while (itr.hasNext()){
//
//				p.println(((User)itr.next()).getName());
//				}
//				query.close(result);


            ReservationPortalSystem.getInstance().save(x);

           
           // getServletContext().getRequestDispatcher("/login.jsp").forwarred(request, response);
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

    } 

    private User getUserFromRequest(HttpServletRequest request){
        String reqStr = (String)request.getParameter("req");
        if (reqStr.equals("registerAdmin")){
            Admin newAdmin = new Admin();
            newAdmin.setName((String)request.getParameter("name"));
            newAdmin.setUserName((String)request.getParameter("userName"));
            //newAdmin.setPassword(MD5HashGenerator.generateHash((String)request.getParameter("password")));
            newAdmin.setPassword("new password");
            newAdmin.setAddress((String)request.getParameter("address"));
            newAdmin.setEmail((String)request.getParameter("email"));
            newAdmin.setPhoneNumber((String)request.getParameter("phone"));
            newAdmin.setActivated(false);
            newAdmin.setQualifications((String)request.getParameter("qualifications"));
            return newAdmin;
        }else if (reqStr.equals("registerUser")){
            Customer newCustomer = new Customer();
            newCustomer.setName((String)request.getParameter("name"));
            newCustomer.setUserName((String)request.getParameter("userName"));
            //newCustomer.setPassword(MD5HashGenerator.generateHash((String)request.getParameter("password")));
            newCustomer.setPassword("new password");
            newCustomer.setAddress((String)request.getParameter("address"));
            newCustomer.setEmail((String)request.getParameter("email"));
            newCustomer.setPhoneNumber((String)request.getParameter("phone"));
            return newCustomer;
        }else{
            return null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

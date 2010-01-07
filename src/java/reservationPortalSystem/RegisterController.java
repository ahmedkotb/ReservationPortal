/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reservationPortalSystem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.MD5HashGenerator;
import utilities.Validator;

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
            getServletContext().getRequestDispatcher("/registercustomer.jsp").forward(request, response);
        }else if (requestStr.equals("admin")){
            //go to the admin register page
            getServletContext().getRequestDispatcher("/registeradmin.jsp").forward(request, response);
        }else if (requestStr.equals("registerCustomer")){
            //register the customer
            User x = getUserFromRequest(request);
            String validation = validateUserInfo(x);

            if (validation != null){
                //invalid user Data
                request.setAttribute("error", validation);
                getServletContext().getRequestDispatcher("/home.jsp?req=registerCustomer").forward(request, response);
                return;
            }

            ReservationPortalSystem.getInstance().register(x);
            getServletContext().getRequestDispatcher("/home.jsp?req=home").forward(request, response);
        }else if (requestStr.equals("registerAdmin")){
            //register the user information
            User x = getUserFromRequest(request);
            String validation = validateUserInfo(x);

            if (validation != null){
                //invalid user Data
                request.setAttribute("error", validation);
                getServletContext().getRequestDispatcher("/home.jsp?req=registerAdmin").forward(request, response);
                return;
            }

            ReservationPortalSystem.getInstance().register(x);
            getServletContext().getRequestDispatcher("/home.jsp?req=home").forward(request, response);
        }else{
            getServletContext().getRequestDispatcher("/home.jsp?req=home").forward(request, response);
        }

    } 



    /**
     *
     * @return null if no errors is found , else returns a string having the error message
     */
    private String validateUserInfo(User user){
        if (user == null)
            return "please make sure that passwords match and are atleast 6 characters long";

        if (user.name == null || user.name.equals(""))
            return "Name Field is Missing";

        if (user.name != null && user.name.length() < 5)
            return "make sure that the name is atleast 5 characters long";

        if (user.userName == null || user.userName.equals(""))
            return "User Name Field is Missing";

        if (user.userName == null || user.userName.length() < 5)
            return "User Name must be bigger than 5 characters";

        if (ReservationPortalSystem.getInstance().isUserExists(user.userName))
            return "User Name already exists please choose another one";

        if (user.address == null || user.address.length() == 0)
            return "address field is misssig";

        if (user.phoneNumber == null || user.phoneNumber.length() == 0)
            return "phone number field is misssig";

        Validator validator = new Validator();
        if (!validator.isValidEmail(user.email))
            return "please enter a valid email";

        if (user instanceof  Admin && (((Admin)user).getQualifications() == null || ((Admin)user).getQualifications().length() == 0))
            return "qualification field is misssig";

        return null;
    }
    private User getUserFromRequest(HttpServletRequest request){
        String reqStr = (String)request.getParameter("req");
        if (!((String)request.getParameter("password")).equals((String)request.getParameter("cpassword")) || ((String)request.getParameter("password")).length() < 6){
            return null;
        }
        if (reqStr.equals("registerAdmin")){
            Admin newAdmin = new Admin();
            newAdmin.setName((String)request.getParameter("name"));
            newAdmin.setUserName((String)request.getParameter("userName"));
            newAdmin.setPassword(MD5HashGenerator.generateHash((String)request.getParameter("password")));
            newAdmin.setAddress((String)request.getParameter("address"));
            newAdmin.setEmail((String)request.getParameter("email"));
            newAdmin.setPhoneNumber((String)request.getParameter("phone"));
            newAdmin.setActivated(false);
            newAdmin.setQualifications((String)request.getParameter("qualifications"));
            return newAdmin;
        }else if (reqStr.equals("registerCustomer")){
            Customer newCustomer = new Customer();
            //to register a new owner replace the previous line with this one
            //Owner newCustomer = new Owner();
            newCustomer.setName((String)request.getParameter("name"));
            newCustomer.setUserName((String)request.getParameter("userName"));
            newCustomer.setPassword(MD5HashGenerator.generateHash((String)request.getParameter("password")));
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

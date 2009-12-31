/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationPortalSystem;

import records.AdminReservationManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed Kotb
 */
public class LoginController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //TODO replace the output stream with a display message
        HttpSession session = request.getSession(true);

        User user;
        try {
            user = ReservationPortalSystem.getInstance().login((String) request.getParameter("userName"), (String) request.getParameter("password"));
        } catch (Exception ex) {
            if (ex.getMessage().equals("UserNotFoundException")) {
                out.println("wrong password or username");
            } else if (ex.getMessage().equals("NotActivatedException")) {
                out.print("you are not activated yet or have been deactivated by the owner of the portal");
            }
            return;
        }

        session.setAttribute("user", user);
        if (user instanceof Owner) {
            getServletContext().getRequestDispatcher("/owner/owner.jsp").forward(request, response);
        } else if (user instanceof Admin) {
            session.setAttribute("reservationManager", new AdminReservationManager((Admin)user));
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        } else {
            // TODO should create the customer manager and set it in the session
            out.print("RR" + user.userName + "    " + user.getEmail() + " " + user.getPhoneNumber());
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

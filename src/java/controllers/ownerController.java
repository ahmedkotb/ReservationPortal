/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservationPortalSystem.Owner;
import reservationPortalSystem.ReservationPortalSystem;
import reservationPortalSystem.User;

/**
 *
 * @author ahmed
 */
public class ownerController extends HttpServlet {
   
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

        if (request.getSession().getAttribute("user") == null || !((User)request.getSession().getAttribute("user") instanceof Owner)){
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String req = (String)request.getParameter("req");
        if (req == null){
            getServletContext().getRequestDispatcher("/owner/owner.jsp").forward(request, response);
        }else if (req.equals("allAdmins")) {
            request.getSession().setAttribute("mode", "allAdmins");
            request.getSession().setAttribute("result", ReservationPortalSystem.getInstance().getAllAdmins());
            getServletContext().getRequestDispatcher("/owner/owner.jsp").forward(request, response);
        }else if (req.equals("newAdmins")){
            request.getSession().setAttribute("mode", "newAdmins");
            request.getSession().setAttribute("result", ReservationPortalSystem.getInstance().getNewAdmins());
            getServletContext().getRequestDispatcher("/owner/owner.jsp").forward(request, response);
        }else if (req.equals("activate")) {
            String userName = (String)request.getParameter("userName");
            ReservationPortalSystem.getInstance().activateAdmin(userName);
            getServletContext().getRequestDispatcher("/owner").forward(request, response);
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

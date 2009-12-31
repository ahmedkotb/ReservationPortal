/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import items.CarAgency;
import items.Location;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservationPortalSystem.IAdminReservationItemManager;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class AdminController extends HttpServlet {
   
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
        String req = (String)request.getParameter("req");

        if (req == null){
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }else if (req.equals("addAgencyPage")){
            request.setAttribute("mode", "addAgencyPage");
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }else if (req.equals("addAgency")){
            addCarAgency(request);
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }else if(req.equals("addCarPage")){
            request.setAttribute("mode", "addCarPage");
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }
    } 


    private void addCarAgency(HttpServletRequest request){
        IAdminReservationItemManager manager= ReservationPortalSystem.getInstance().getItemManager();
        CarAgency agency = new CarAgency();
        agency.setName((String)request.getParameter("name"));
        agency.setDescription((String)request.getParameter("description"));
        int numberOfLocations = Integer.parseInt((String)request.getParameter("numberOfLocations"));
        Location newLocation;
        String[] locTokens;
        for (int i=1;i<=numberOfLocations;i++){
            newLocation = new Location();
            locTokens = ((String)request.getParameter("loc" + i)).split(",");
            newLocation.setCountry(locTokens[0].trim());
            newLocation.setCity(locTokens[1].trim());
            newLocation.setStreet(locTokens[2].trim());
            agency.addLocation(newLocation);
        }

        manager.addCarAgency(agency);
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import items.Location;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservationPortalSystem.ReservationPortalSystem;

/**
 *
 * @author ahmed
 */
public class customerController extends HttpServlet {
   
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
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }else if (req.equals("searchCarPage")){
            request.setAttribute("mode", "searchCarPage");
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }else if (req.equals("searchCar")){
            request.setAttribute("mode", "searchCar");
            request.setAttribute("result", ReservationPortalSystem.getInstance().getItemManager().search(getSearchParameters(request)));
            getServletContext().getRequestDispatcher("/customer/customer.jsp").forward(request, response);
        }
    } 


    private HashMap getSearchParameters(HttpServletRequest request){
        String req = (String)request.getParameter("req");
        HashMap<String,Object> info = new HashMap();
        if (req.equals("searchCar")){
           info.put("carModel", (String)request.getParameter("carModel"));
           info.put("carType", (String)request.getParameter("carType"));
           Location pickyupLocation = new Location();
           pickyupLocation.setCity((String)request.getParameter("pickupCity"));
           pickyupLocation.setStreet((String)request.getParameter("pickupStreet"));
           pickyupLocation.setCountry((String)request.getParameter("pickupCountry"));

           Location returnLocation = new Location();
           returnLocation.setCity((String)request.getParameter("returnCity"));
           returnLocation.setStreet((String)request.getParameter("returnStreet"));
           returnLocation.setCountry((String)request.getParameter("returnCountry"));
        }
        return info;
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
